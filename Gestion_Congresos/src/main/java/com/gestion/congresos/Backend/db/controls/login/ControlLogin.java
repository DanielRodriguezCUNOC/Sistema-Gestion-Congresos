package com.gestion.congresos.Backend.db.controls.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;

public class ControlLogin {

    private static final String FIND_USER_QUERY = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";

    /**
     * The function `userExist` checks if a user with a given username and password
     * exists in the database.
     * 
     * @param username The `userExist` method you provided seems to be checking if a
     *                 user with a specific
     *                 username and password exists in the database. However, it
     *                 looks like you didn't provide the full
     *                 details of the parameters. Could you please provide more
     *                 information about the `username` parameter
     *                 so that I can assist you
     * @param password It seems like you forgot to provide the value for the
     *                 `password` parameter in the
     *                 `userExist` method. Could you please provide the value for
     *                 the `password` parameter so that I can
     *                 assist you further with your code?
     * @return The `userExist` method returns a boolean value indicating whether a
     *         user with the provided
     *         username and password exists in the database. If a user with the
     *         given credentials is found in the
     *         database, it returns `true`, otherwise it returns `false`.
     */
    public boolean userExist(String username, String password) {
        Connection connection = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_USER_QUERY)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
