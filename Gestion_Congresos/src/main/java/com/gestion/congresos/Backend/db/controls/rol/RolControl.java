package com.gestion.congresos.Backend.db.controls.rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;

public class RolControl {

    private static final String GET_ID_ROL = "SELECT id_rol FROM Rol WHERE rol = ?";
    private static final String GET_ALL_ROL = "SELECT * FROM Rol";

    public int getIdRol(String rol) {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(GET_ID_ROL)) {
            ps.setString(1, rol);
            try (ResultSet rs = ps.executeQuery(GET_ID_ROL)) {

                if (rs.next()) {
                    return rs.getInt("id_rol");
                }
                return -1;
            }

        } catch (Exception e) {
            return -1;
        }
    }

    public List<String> getAllRol() {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();
        List<String> roles = new java.util.ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(GET_ALL_ROL);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                roles.add(rs.getString("rol"));
            }
            return roles;

        } catch (Exception e) {
            return roles;
        }
    }
}
