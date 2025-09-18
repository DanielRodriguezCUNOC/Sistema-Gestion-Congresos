package com.gestion.congresos.Backend.db.controls.admin_conference;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.db.models.CongressModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlCongress {

    private static final String INSERT_NEW_CONGRESS = "INSERT INTO Congreso (" +
            "id_institucion, nombre_congreso, fecha_inicio, fecha_fin, precio, acepta_convocatoria, estado" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?)";

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
        String query = "SELECT COUNT(*) AS count FROM Congreso WHERE nombre_congreso = ?";

        try (Connection conn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

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

    public int getIdCongressByName(String nameCongress) throws DataBaseException {
        String query = "SELECT id_congreso FROM Congreso WHERE nombre_congreso = ?";

        try (Connection conn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nameCongress);
            var rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_congreso");
            } else {
                return -1;
            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener el id del congreso", e);
        }
    }

}
