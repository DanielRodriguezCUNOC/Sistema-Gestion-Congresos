package com.gestion.congresos.Backend.db.controls.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;

public class ControlLogin {

    private static final String FIND_USER_QUERY = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";

    public boolean userExist(String username, String password) {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_USER_QUERY)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                // retornamos true si existe un registro que coincida con el usuario y la
                // contraseña
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
