package com.gestion.congresos.Backend.db.controls.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.db.models.UserModel;

public class UserControl {

    private static final String FINDBY_USERNAME = "SELECT * FROM Usuario WHERE usuario = ?";
    private static final String INSERT_USER = "INSERT INTO Usuario (id_rol, nombre, usuario, password, correo, identificacion_personal, telefono, fotografia, organizacion, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public UserControl() {

    }

    /**
     * The `insertUser` function inserts a new user into a database using a prepared
     * statement and
     * handles SQLExceptions by rolling back changes and returning false.
     * 
     * @param user The `insertUser` method you provided is responsible for inserting
     *             a new user into a
     *             database. It takes a `UserModel` object as a parameter, which
     *             contains various attributes such as
     *             idRol, name, user, password, email, ID, phone, photo,
     *             organization, and state.
     * @return The method `insertUser` returns a boolean value indicating whether
     *         the user insertion was
     *         successful or not. It returns `true` if the number of rows affected
     *         by the SQL update operation
     *         is greater than 0, indicating that the user was successfully inserted
     *         into the database.
     *         Otherwise, it returns `false` if an exception occurs during the
     *         insertion process or if no rows
     *         were affected.
     */
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
            int rowsAffected = ps.executeUpdate();
            conn.commit();
            return rowsAffected > 0;
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
            return false;

        }

    }

    /**
     * The function `validateUser` checks if a user with a given username exists in
     * the database and
     * returns true if a matching record is found.
     * 
     * @param username The `validateUser` method you provided seems to be checking
     *                 if a user with a
     *                 specific username exists in the database. The `username`
     *                 parameter is the username that you want
     *                 to validate or check for existence in the database.
     * @return The method `validateUser` is returning a boolean value. It returns
     *         `true` if there is a
     *         record in the database that matches the provided username, and it
     *         returns `false` if there is an
     *         exception caught during the process or if no matching record is
     *         found.
     */
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
