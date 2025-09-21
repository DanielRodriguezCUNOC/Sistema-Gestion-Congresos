package com.gestion.congresos.Backend.db.controls.activity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.db.models.ActivityModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlActivity {
    private static final String INSERT_NEW_ACTIVITY = "INSERT INTO Actividad (" +
            "id_salon, id_congreso, id_tipo_actividad, nombre_actividad, fecha, hora_inicio, hora_fin, descripcion, cupo_taller"
            +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public boolean insertActivity(ActivityModel activity) throws SQLException, DataBaseException {
        try (Connection conn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(INSERT_NEW_ACTIVITY)) {

            conn.setAutoCommit(false);

            ps.setInt(1, activity.getIdSalon());
            ps.setInt(2, activity.getIdCongreso());
            ps.setInt(3, activity.getIdTipoActividad());
            ps.setString(4, activity.getNombreActividad());
            ps.setDate(5, Date.valueOf(activity.getFecha()));
            ps.setTime(6, Time.valueOf(activity.getHoraInicio()));
            ps.setTime(7, Time.valueOf(activity.getHoraFin()));
            ps.setString(8, activity.getDescripcion());
            if (activity.getCupoTaller() != null) {
                ps.setInt(9, activity.getCupoTaller());
            } else {
                ps.setNull(9, Types.INTEGER);

            }

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al insertar la actividad", e);
        }
    }

    public boolean existsActivityByName(String nameActivity) throws DataBaseException {
        String query = "SELECT COUNT(*) AS count FROM Actividad WHERE nombre_actividad = ?";
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nameActivity);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al verificar la existencia de la actividad", e);
        }
    }

}
