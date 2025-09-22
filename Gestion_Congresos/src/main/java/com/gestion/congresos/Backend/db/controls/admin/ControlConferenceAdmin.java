package com.gestion.congresos.Backend.db.controls.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlConferenceAdmin {

    private static final String GET_ALL_GUESTS_SPEAKERS = "SELECT u.nombre, u.correo, u.telefono, u.identificacion_personal, "
            + "u.organizacion, u.estado, r.rol AS tipo_rol "
            + "FROM Usuario u JOIN Rol r ON u.id_rol = r.id_rol "
            + "WHERE r.rol = 'Ponente Invitado'";

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

    public List<String[]> getAllCongress() throws DataBaseException {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String query = "SELECT c.id_congreso, i.nombre_institucion, i.ubicacion, " +
                "c.nombre_congreso, c.fecha_inicio, c.fecha_fin, c.precio, " +
                "c.acepta_convocatoria, c.estado, c.cupo " +
                "FROM Congreso c " +
                "JOIN Institucion i ON c.id_institucion = i.id_institucion";

        try (PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            List<String[]> congressList = new ArrayList<>();

            while (rs.next()) {
                String[] data = new String[10];
                data[0] = String.valueOf(rs.getInt("id_congreso"));
                data[1] = rs.getString("nombre_institucion");
                data[2] = rs.getString("ubicacion");
                data[3] = rs.getString("nombre_congreso");
                data[4] = rs.getString("fecha_inicio");
                data[5] = rs.getString("fecha_fin");
                data[6] = rs.getString("precio");
                data[7] = String.valueOf(rs.getBoolean("acepta_convocatoria"));
                data[8] = String.valueOf(rs.getBoolean("estado"));
                data[9] = String.valueOf(rs.getInt("cupo"));

                congressList.add(data);
            }

            return congressList;

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener los congresos", e);
        }
    }

    public List<String[]> getAllActivities() throws DataBaseException {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String query = "SELECT a.id_actividad, a.id_salon, a.id_congreso, a.id_tipo_actividad, " +
                "a.nombre_actividad, a.fecha, a.hora_inicio, a.hora_fin, a.descripcion, a.cupo_taller, " +
                "s.nombre_salon, s.ubicacion AS ubicacion_salon, " +
                "c.nombre_congreso " +
                "FROM Actividad a " +
                "JOIN Salon s ON a.id_salon = s.id_salon " +
                "JOIN Congreso c ON a.id_congreso = c.id_congreso";

        List<String[]> activityList = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String[] data = new String[13];
                data[0] = String.valueOf(rs.getInt("id_actividad"));
                data[1] = String.valueOf(rs.getInt("id_salon"));
                data[2] = String.valueOf(rs.getInt("id_congreso"));
                data[3] = String.valueOf(rs.getInt("id_tipo_actividad"));
                data[4] = rs.getString("nombre_actividad");
                data[5] = rs.getString("fecha");
                data[6] = rs.getString("hora_inicio");
                data[7] = rs.getString("hora_fin");
                data[8] = rs.getString("descripcion");
                data[9] = String.valueOf(rs.getInt("cupo_taller"));
                data[10] = rs.getString("nombre_salon");
                data[11] = rs.getString("ubicacion_salon");
                data[12] = rs.getString("nombre_congreso");

                activityList.add(data);
            }
            return activityList;

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener las actividades", e);
        }
    }

}
