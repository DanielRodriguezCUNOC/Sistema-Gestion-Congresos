package com.gestion.congresos.Backend.db.controls.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;

public class UserControl {

    private static final String FINDBY_USERNAME = "SELECT * FROM Usuario WHERE usuario = ?";
    private static final String INSERT_USER = "INSERT INTO Usuario (id_rol, nombre, usuario, password, correo, identificacion_personal, telefono, fotografia, organizacion, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_EMAIL_BY_USERNAME = "SELECT correo FROM Usuario WHERE usuario = ?";
    private static final String GET_ID_USER_BY_USERNAME = "SELECT id_usuario FROM Usuario WHERE usuario = ?";
    private static final String GET_ID_ROL_BY_ID_USER = "SELECT id_rol FROM Usuario WHERE id_usuario = ?";
    private static final String GET_USER_BY_ID = "SELECT * FROM Usuario WHERE id_usuario = ?";

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

    /**
     * The function `getEmailByUsername` retrieves the email associated with a given
     * username from a
     * database, throwing exceptions for user not found or database errors.
     * 
     * @param username The method `getEmailByUsername` takes a `username` as a
     *                 parameter and retrieves the
     *                 email associated with that username from a database. If the
     *                 email is found, it is returned. If the
     *                 email is not found, a `UserNotFoundException` is thrown. If
     *                 there is an error accessing the
     *                 database,
     * @return The method is returning the email associated with the provided
     *         username. If the username is
     *         found in the database, the email address is returned. If the username
     *         is not found, a
     *         UserNotFoundException is thrown with the message "No se encontró un
     *         correo relacionado con el
     *         nombre de usuario." If there is a database error, a DataBaseException
     *         is thrown with the message
     *         "Ha ocurrido un error, intent
     */
    public String getEmailByUsername(String username) throws UserNotFoundException, DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(GET_EMAIL_BY_USERNAME)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("correo");
                } else {
                    throw new UserNotFoundException("No se encontró un correo relacionado con el nombre de usuario.");
                }
            }

        } catch (SQLException e) {
            throw new DataBaseException("Ha ocurrido un error, intentelo de nuevo", e);
        }
    }

    /**
     * The function `getUserIdByUsername` retrieves the user ID based on the
     * provided username from a
     * database, handling exceptions for database access and user not found
     * scenarios.
     * 
     * @param username The `getUserIdByUsername` method takes a `username` parameter
     *                 as input, which is
     *                 used to query the database for the corresponding user ID. If
     *                 a user with the provided username is
     *                 found in the database, the method returns the user ID. If no
     *                 user is found with the given username,
     *                 a
     * @return The method is returning the user ID (id_usuario) corresponding to the
     *         provided username. If
     *         a user with the given username is found in the database, the method
     *         returns the user ID. If no user
     *         is found with the provided username, a UserNotFoundException is
     *         thrown.
     */
    public int getUserIdByUsername(String username) throws DataBaseException, UserNotFoundException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(GET_ID_USER_BY_USERNAME)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_usuario");
                } else {
                    throw new UserNotFoundException("No se encontró un usuario con el nombre proporcionado.");
                }
            }

        } catch (SQLException e) {
            throw new DataBaseException("Ha ocurrido un error al acceder a la base de datos.", e);
        }
    }

    public int getUserRolIdByIdUser(int userId) throws DataBaseException, UserNotFoundException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(GET_ID_ROL_BY_ID_USER)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_rol");
                } else {
                    throw new UserNotFoundException("No se encontró el rol para el usuario con ID." + userId);
                }
            }

        } catch (SQLException e) {
            throw new DataBaseException("Ha ocurrido un error al acceder a la base de datos.", e);
        }
    }

    public UserModel getUserById(int userId) throws DataBaseException, UserNotFoundException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(GET_USER_BY_ID)) {

            ps.setInt(1, userId);
            ps.executeQuery();

            try (ResultSet rs = ps.getResultSet()) {
                if (rs.next()) {
                    UserModel user = new UserModel();
                    user.setIdUser(rs.getInt("id_usuario"));
                    user.setIdRol(rs.getInt("id_rol"));
                    user.setName(rs.getString("nombre"));
                    user.setUser(rs.getString("usuario"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("correo"));
                    user.setID(rs.getString("identificacion_personal"));
                    user.setPhone(rs.getString("telefono"));
                    user.setPhoto(rs.getBytes("fotografia"));
                    user.setOrganization(rs.getString("organizacion"));
                    user.setState(rs.getString("estado"));
                    return user;
                } else {
                    throw new UserNotFoundException("No se encontró un usuario con el ID proporcionado.");
                }
            }

        } catch (SQLException e) {
            throw new DataBaseException("Ha ocurrido un error al acceder a la base de datos.", e);
        }
    }
}
