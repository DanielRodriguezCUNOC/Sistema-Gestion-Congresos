package com.gestion.congresos.Backend.db.controls.congress;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.db.models.CongressModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;

public class ControlCongress {

    private static final String INSERT_NEW_CONGRESS = "INSERT INTO Congreso (" +
            "id_institucion, nombre_congreso, fecha_inicio, fecha_fin, precio, acepta_convocatoria, estado, cupo" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public boolean insertCongress(CongressModel congress) throws SQLException, DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(INSERT_NEW_CONGRESS)) {

            conn.setAutoCommit(false);

            ps.setInt(1, congress.getIdInstitucion());
            ps.setString(2, congress.getNombreCongreso());
            ps.setDate(3, Date.valueOf(congress.getFechaInicio().toLocalDate()));
            ps.setDate(4, Date.valueOf(congress.getFechaFin().toLocalDate()));
            ps.setDouble(5, congress.getPrecio().doubleValue());
            ps.setBoolean(6, congress.getAceptaConvocatoria());
            ps.setBoolean(7, congress.getEstado());
            ps.setInt(8, congress.getCupo());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (SQLException e) {
            conn.rollback();
            throw new DataBaseException("Error al insertar el congreso: ", e);
        }

    }

    public boolean existsCongressByName(String nameCongress) throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String query = "SELECT COUNT(*) AS count FROM Congreso WHERE nombre_congreso = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nameCongress);
            var rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al verificar la existencia del congreso", e);
        }
    }

    public int getIdCongressByName(String nameCongress) throws DataBaseException, ObjectNotFoundException {
        String query = "SELECT id_congreso FROM Congreso WHERE nombre_congreso = ?";

        try (Connection conn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nameCongress);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt("id_congreso");
                } else {
                    throw new ObjectNotFoundException("Congreso no encontrado: " + nameCongress);
                }

            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener el id del congreso", e);
        }
    }

    public CongressModel getCongressById(int idCongress) throws DataBaseException, ObjectNotFoundException {
        String query = "SELECT * FROM Congreso WHERE id_congreso = ?";

        try (Connection conn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idCongress);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    CongressModel congress = new CongressModel();
                    congress.setIdCongreso(rs.getInt("id_congreso"));
                    congress.setIdInstitucion(rs.getInt("id_institucion"));
                    congress.setNombreCongreso(rs.getString("nombre_congreso"));

                    congress.setFechaInicio(rs.getDate("fecha_inicio"));

                    congress.setFechaFin(rs.getDate("fecha_fin"));

                    congress.setPrecio(rs.getBigDecimal("precio"));
                    congress.setAceptaConvocatoria(rs.getBoolean("acepta_convocatoria"));
                    congress.setEstado(rs.getBoolean("estado"));
                    congress.setCupo(rs.getInt("cupo"));
                    return congress;
                } else {
                    throw new ObjectNotFoundException("Congreso no encontrado con ID: " + idCongress);
                }

            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener el congreso por ID", e);
        }
    }

}
