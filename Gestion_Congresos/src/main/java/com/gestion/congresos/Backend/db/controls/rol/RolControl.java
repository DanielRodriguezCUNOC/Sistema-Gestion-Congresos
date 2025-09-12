package com.gestion.congresos.Backend.db.controls.rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;

/**
 * The `RolControl` class contains methods to retrieve the ID of a role and all
 * roles from a database
 * table.
 */
public class RolControl {

    private static final String GET_ID_ROL = "SELECT id_rol FROM Rol WHERE rol = ?";
    private static final String GET_ALL_ROL = "SELECT * FROM Rol";

    /**
     * This Java function retrieves the ID of a role from a database based on the
     * role name provided.
     * 
     * @param rol The `getIdRol` method takes a `String` parameter named `rol`,
     *            which represents the role
     *            for which you want to retrieve the corresponding ID from the
     *            database. The method then connects to
     *            the database, executes a query to retrieve the ID of the specified
     *            role, and returns the ID as an
     *            integer
     * @return The `getIdRol` method returns an integer value, which is the ID of
     *         the specified role. If
     *         the role is found in the database, the method returns the ID of that
     *         role. If the role is not found
     *         or an exception occurs during the database operation, the method
     *         returns -1.
     */

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

    /**
     * The function getAllRol retrieves a list of roles from a database using a
     * prepared statement and
     * returns the list of roles.
     * 
     * @return The method is returning a List of Strings containing all the roles
     *         retrieved from the
     *         database. If an exception occurs during the database operation, it
     *         will return an empty list.
     */
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
