package com.gestion.congresos.Backend.db.controls.activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.db.models.ActivityModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;

public class ControlActivityCRUD {

    private static final String GET_ACTIVITY_BY_ID = "SELECT * FROM Actividad WHERE idActividad = ?;";

    public ActivityModel getGetActivityById(int idActivity) throws DataBaseException, ObjectNotFoundException {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(GET_ACTIVITY_BY_ID)) {

            ps.setInt(1, idActivity);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    ActivityModel activity = new ActivityModel();

                    activity.setIdActividad(rs.getInt("id_actividad"));
                    activity.setIdSalon(rs.getInt("id_salon"));
                    activity.setIdCongreso(rs.getInt("id_congreso"));
                    activity.setIdTipoActividad(rs.getInt("id_tipo_actividad"));
                    activity.setNombreActividad(rs.getString("nombre_actividad"));
                    activity.setFecha(rs.getDate("fecha").toLocalDate());
                    activity.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                    activity.setHoraFin(rs.getTime("hora_fin").toLocalTime());
                    activity.setDescripcion(rs.getString("descripcion"));
                    activity.setCupoTaller(rs.getInt("cupoTaller"));
                    return activity;
                } else {
                    throw new ObjectNotFoundException("No se encontró la actividad con ID: " + idActivity);
                }
            }

        } catch (SQLException e) {
            throw new DataBaseException("Ha ocurrido un error al acceder a la base de datos.", e);
        }

    }

    public boolean deleteActivity(int idActivity) throws DataBaseException {
        String DELETE_ACTIVITY_SQL = "DELETE FROM Actividad WHERE id_actividad = ?";

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(DELETE_ACTIVITY_SQL)) {

            ps.setInt(1, idActivity);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new DataBaseException("Error al eliminar la actividad con ID: " + idActivity, e);
        }
    }

    public boolean updateActivity(ActivityModel activity) throws DataBaseException {
        String UPDATE_ACTIVITY_SQL = """
                UPDATE Actividad
                SET id_salon = ?,
                    id_congreso = ?,
                    id_tipo_actividad = ?,
                    nombre_actividad = ?,
                    fecha = ?,
                    hora_inicio = ?,
                    hora_fin = ?,
                    descripcion = ?,
                    cupoTaller = ?
                WHERE id_actividad = ?
                """;

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(UPDATE_ACTIVITY_SQL)) {

            ps.setInt(1, activity.getIdSalon());
            ps.setInt(2, activity.getIdCongreso());
            ps.setInt(3, activity.getIdTipoActividad());
            ps.setString(4, activity.getNombreActividad());
            ps.setObject(5, activity.getFecha());
            ps.setObject(6, activity.getHoraInicio());
            ps.setObject(7, activity.getHoraFin());
            ps.setString(8, activity.getDescripcion());
            if (activity.getCupoTaller() != null) {
                ps.setInt(9, activity.getCupoTaller());
            } else {
                ps.setNull(9, java.sql.Types.INTEGER);
            }
            ps.setInt(10, activity.getIdActividad());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new DataBaseException("Error al actualizar la actividad con ID: " + activity.getIdActividad(), e);
        }
    }

}
