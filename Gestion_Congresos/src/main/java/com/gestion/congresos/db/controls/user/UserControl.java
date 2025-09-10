package com.gestion.congresos.db.controls.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gestion.congresos.db.DBConnectionSingleton;
import com.gestion.congresos.db.models.UserModel;

public class UserControl {

    private static final String FINDBY_USERNAME = "SELECT * FROM users WHERE user = ?";
    private static final String INSERT_USER = "INSERT INTO users (idRol, name, user, password, email, ID, phone, photo, organization, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public UserControl() {

    }

    public boolean insertUser(UserModel user) throws SQLException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(INSERT_USER)) {

            conn.setAutoCommit(false);
            ps.setInt(1, user.getIdRol());
            ps.setString(2, user.getName());
            ps.setString(3, user.getUser());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getID());
            ps.setString(7, user.getPhone());
            ps.setBytes(8, user.getPhoto());
            ps.setString(9, user.getOrganization());
            ps.setString(10, user.getState());

            conn.commit();
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            conn.rollback();
            return false;

        }

    }

    public boolean validateUser(String username) {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(FINDBY_USERNAME)) {

            ps.setString(1, username);
            ps.executeQuery();

            try (ResultSet rs = ps.getResultSet()) {
                // retornamos true si existe un registro que coincida con el usuario
                return rs.next();
            }

        } catch (Exception e) {
            return false;
        }

    }
}
