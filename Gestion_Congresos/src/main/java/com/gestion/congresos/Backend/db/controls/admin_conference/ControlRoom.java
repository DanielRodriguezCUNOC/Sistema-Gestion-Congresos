package com.gestion.congresos.Backend.db.controls.admin_conference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.db.models.RoomModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlRoom {

    private static final String INSERT_NEW_ROOM = "INSERT INTO Salon (" +
            "id_institucion, nombre_salon, ubicacion, capacidad" +
            ") VALUES (?, ?, ?, ?)";

    public boolean insertRoom(RoomModel salon)
            throws SQLException, DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(INSERT_NEW_ROOM)) {

            conn.setAutoCommit(false);

            ps.setInt(1, salon.getIdInstitucion());
            ps.setString(2, salon.getNombreSalon());
            ps.setString(3, salon.getDireccion());
            ps.setInt(4, salon.getCapacidad());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al insertar el salon", e);
        }
    }

    public int getIdRoomByName(String nameRoom, int idCongress) throws DataBaseException {
        String query = "SELECT s.id_salon FROM Salon s " +
                "JOIN Congreso c ON s.id_congreso = c.id_congreso " +
                "WHERE s.nombre_salon = ? AND c.id_congreso = ?";

        try (Connection conn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nameRoom);
            ps.setInt(2, idCongress);
            var rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_salon");
            } else {
                return -1;
            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener el id del salon", e);
        }
    }

    public boolean existsRoom(String nameRoom, int idInstitucion, String ubicacion) throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String query = "SELECT COUNT(*) AS count FROM Salon " +
                "WHERE nombre_salon = ? AND id_institucion = ? AND ubicacion = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nameRoom);
            ps.setInt(2, idInstitucion);
            ps.setString(3, ubicacion);

            var rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
            return false;

        } catch (SQLException e) {
            throw new DataBaseException("Error al verificar la existencia del salón", e);
        }
    }

    public List<String[]> getAllRooms() throws SQLException {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String query = "SELECT s.id_salon, s.nombre_salon, i.nombre_institucion, s.ubicacion, s.capacidad " +
                "FROM Salon s " +
                "JOIN Institucion i ON s.id_institucion = i.id_institucion";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            var rs = ps.executeQuery();
            List<String[]> rooms = new java.util.ArrayList<>();

            while (rs.next()) {
                String[] room = new String[5];
                room[0] = String.valueOf(rs.getInt("id_salon"));
                room[1] = rs.getString("nombre_salon");
                room[2] = rs.getString("nombre_institucion");
                room[3] = rs.getString("ubicacion");
                room[4] = String.valueOf(rs.getInt("capacidad"));
                rooms.add(room);
            }
            return rooms;

        } catch (SQLException e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

}
