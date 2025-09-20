package com.gestion.congresos.Backend.db.controls.room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.db.models.RoomModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;

public class ControlRoomCRUD {

    public boolean editRoom(int idRoom, String nameInstitution, String nameRoom, int capacity, String address)
            throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String sql = "UPDATE Salon SET nombre_institucion = ?, nombre_salon = ?, capacidad = ?, ubicacion = ? WHERE id_salon = ?";
        try {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, nameInstitution);
                ps.setString(2, nameRoom);
                ps.setInt(3, capacity);
                ps.setString(4, address);
                ps.setInt(5, idRoom);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;

                }

            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al editar el salón", e);
        }
    }

    public RoomModel getRoomById(int idRoom) throws DataBaseException, ObjectNotFoundException {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();
        String sql = "SELECT * FROM Salon WHERE id_salon = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idRoom);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    RoomModel room = new RoomModel();

                    room.setIdSalon(rs.getInt("id_salon"));

                    room.setNombreSalon(rs.getString("nombre_salon"));

                    room.setIdInstitucion(rs.getInt("id_institucion"));

                    room.setCapacidad(rs.getInt("capacidad"));

                    room.setDireccion(rs.getString("ubicacion"));

                    return room;

                } else {
                    throw new ObjectNotFoundException("No se encontró el salón con ID: " + idRoom);
                }

            }
        } catch (SQLException e) {
            throw new DataBaseException("Ha ocurrido un error al acceder a la base de datos.", e);
        }
    }

    public boolean deleteRoom(int idRoom) throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();
        String sql = "DELETE FROM Salon WHERE id_salon = ?";

        try {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, idRoom);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al eliminar el salón", e);
        }
    }
}
