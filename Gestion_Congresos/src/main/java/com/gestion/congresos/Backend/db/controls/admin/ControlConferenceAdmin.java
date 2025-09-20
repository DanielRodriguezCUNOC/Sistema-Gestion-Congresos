package com.gestion.congresos.Backend.db.controls.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.db.models.ActivityModel;
import com.gestion.congresos.Backend.db.models.CongressModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlConferenceAdmin {

    private static final String GET_ID_INSTITUTION_BY_NAME = "SELECT id_institucion FROM Institucion WHERE nombre_institucion = ?";

    private static final String GET_ALL_GUESTS_SPEAKERS = "SELECT u.nombre, u.correo, u.telefono, u.identificacion_personal, "
            + "u.organizacion, u.estado, r.rol AS tipo_rol "
            + "FROM Usuario u JOIN Rol r ON u.id_rol = r.id_rol "
            + "WHERE r.rol = 'Ponente Invitado'";

    public int getIdInstitutionByName(String nameInstitution) throws DataBaseException {

        try (Connection conn = DBConnectionSingleton.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(GET_ID_INSTITUTION_BY_NAME)) {

            ps.setString(1, nameInstitution);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_institucion");
                } else {
                    return -1;
                }
            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener el id de la institución", e);
        }
    }

    public int getIdTypeActivityByName(String nameTypeActivity) throws DataBaseException {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String query = "SELECT id_tipo_actividad FROM Tipo_Actividad WHERE tipo_actividad = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nameTypeActivity);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_tipo_actividad");
                } else {
                    return -1;
                }
            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener el id del tipo de actividad", e);
        }
    }

    public List<String[]> getAllGuestsSpeakers() throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(GET_ALL_GUESTS_SPEAKERS);
                ResultSet rs = ps.executeQuery()) {

            List<String[]> guestsSpeakers = new ArrayList<>();

            while (rs.next()) {
                String[] guestData = new String[7];
                guestData[0] = rs.getString("nombre");
                guestData[1] = rs.getString("correo");
                guestData[2] = rs.getString("telefono");
                guestData[3] = rs.getString("identificacion_personal");
                guestData[4] = rs.getString("organizacion");
                guestData[5] = rs.getString("estado");
                guestData[6] = rs.getString("tipo_rol");
                guestsSpeakers.add(guestData);
            }

            return guestsSpeakers;

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener los ponentes invitados", e);
        }
    }

    public List<CongressModel> getAllCongress() throws DataBaseException {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String query = "SELECT * FROM Congreso";

        try (PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            List<CongressModel> congressList = new ArrayList<>();

            while (rs.next()) {
                CongressModel congress = new CongressModel();
                congress.setIdCongreso(rs.getInt("id_congreso"));
                congress.setIdInstitucion(rs.getInt("id_institucion"));
                congress.setNombreCongreso(rs.getString("nombre_congreso"));
                congress.setFechaInicio(rs.getDate("fecha_inicio"));
                congress.setFechaFin(rs.getDate("fecha_fin"));
                congress.setPrecio(rs.getBigDecimal("precio"));
                congress.setAceptaConvocatoria(rs.getBoolean("acepta_convocatoria"));
                congress.setEstado(rs.getBoolean("estado"));
                congressList.add(congress);
            }

            return congressList;

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener los congresos", e);
        }
    }

    public List<ActivityModel> getAllActivities() throws DataBaseException {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String query = "SELECT * FROM Actividad";

        List<ActivityModel> activityList = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ActivityModel activity = new ActivityModel();
                activity.setIdActividad(rs.getInt("id_actividad"));
                activity.setIdCongreso(rs.getInt("id_congreso"));
                activity.setIdTipoActividad(rs.getInt("id_tipo_actividad"));
                activity.setNombreActividad(rs.getString("nombre_actividad"));
                activity.setDescripcion(rs.getString("descripcion"));

                java.sql.Date fechaSql = rs.getDate("fecha");
                activity.setFecha(fechaSql != null ? fechaSql.toLocalDate() : null);

                activity.setHoraInicio(
                        rs.getTime("hora_inicio") != null ? rs.getTime("hora_inicio").toLocalTime() : null);

                activity.setHoraFin(
                        rs.getTime("hora_fin") != null ? rs.getTime("hora_fin").toLocalTime() : null);

                activity.setCupoTaller(rs.getInt("cupo_taller"));

                activityList.add(activity);
            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener las actividades", e);
        }
        return activityList;
    }
}