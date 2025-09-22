package com.gestion.congresos.Backend.db.controls.room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlRoomActivity {

    public boolean isRoomAvailable(int idRoom, String dateActivity, String timeStarting, String timeEnding)
            throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String query = "SELECT COUNT(*) AS total FROM Actividad " +
                "WHERE id_salon = ? AND fecha = ? " +
                "AND (hora_inicio < ? AND hora_fin > ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idRoom);
            ps.setString(2, dateActivity);
            ps.setString(3, timeEnding);
            ps.setString(4, timeStarting);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("total");
                return total == 0;
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al verificar la disponibilidad del salon", e);
        }
    }

    public List<String> getRoomsByCongressName(String nameCongress) throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String query = "SELECT s.nombre_salon FROM Salon s " +
                "JOIN Congreso c ON s.id_institucion = c.id_institucion " +
                "WHERE c.nombre_congreso = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nameCongress);

            ResultSet rs = ps.executeQuery();
            List<String> rooms = new ArrayList<>();
            while (rs.next()) {
                rooms.add(rs.getString("nombre_salon"));
            }
            return rooms;
        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener los salones por nombre de congreso", e);
        }
    }

}
