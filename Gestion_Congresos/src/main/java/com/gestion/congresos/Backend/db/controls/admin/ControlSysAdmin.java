package com.gestion.congresos.Backend.db.controls.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlSysAdmin {

    private static final String GET_CONFERENCE_ADMIN = "SELECT u.nombre, u.usuario, u.correo, u.identificacion_personal, u.telefono, u.organizacion, u.estado, r.rol AS rol "
            + "FROM Usuario u "
            + "JOIN Rol r ON u.id_rol = r.id_rol "
            + "WHERE r.id_rol = 2";

    public List<String[]> getAllConferenceAdmins() throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();
        List<String[]> conferenceAdmins = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(GET_CONFERENCE_ADMIN);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String[] adminData = new String[8];
                adminData[0] = rs.getString("nombre");
                adminData[1] = rs.getString("usuario");
                adminData[2] = rs.getString("correo");
                adminData[3] = rs.getString("identificacion_personal");
                adminData[4] = rs.getString("telefono");
                adminData[5] = rs.getString("organizacion");
                adminData[6] = rs.getString("estado");
                adminData[7] = rs.getString("rol");
                conferenceAdmins.add(adminData);
            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener los administradores de congreso", e);
        }
        return conferenceAdmins;
    }

}
