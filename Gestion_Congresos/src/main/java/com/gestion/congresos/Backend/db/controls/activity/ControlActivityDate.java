package com.gestion.congresos.Backend.db.controls.activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlActivityDate {

    public boolean isDateRoomAvailable(int idSalon, String fecha, String horaInicio, String horaFin)
            throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();
        String query = "SELECT COUNT(*) FROM Actividad WHERE id_salon = ? AND fecha = ? AND (hora_inicio < ? AND hora_fin > ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idSalon);
            ps.setString(2, fecha);
            ps.setString(3, horaFin);
            ps.setString(4, horaInicio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new DataBaseException("Error al verificar la disponibilidad de la fecha y hora", e);
        }
    }

    public boolean isDateActivityAvailable(int idActivity, String fecha, String horaInicio, String horaFin)
            throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();
        String query = "SELECT COUNT(*) FROM Actividad WHERE id_actividad = ? AND (hora_inicio < ? AND hora_fin > ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idActivity);
            ps.setString(2, horaFin);
            ps.setString(3, horaInicio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new DataBaseException("Error al verificar la validez de la fecha y hora", e);
        }
    }

}
