package com.gestion.congresos.Backend.db.controls.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlSysAdminCRUD {

    public boolean updateAdmin(int userId, String username, String phone,
            String organization, byte[] photo)
            throws DataBaseException {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String sql = "UPDATE Usuario SET usuario = ?, telefono = ?, organizacion = ?, photo = ? WHERE id_usuario = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, phone);
            ps.setString(3, organization);
            ps.setBytes(4, photo);
            ps.setInt(5, userId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DataBaseException("Error al actualizar el administrador", e);
        }
    }

    public boolean activateUser(int userId) throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();
        String sql = "UPDATE Usuario SET estado = 'ACTIVO' WHERE id_usuario = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DataBaseException("Error al activar el administrador", e);
        }
    }

    public boolean deactivateUser(int userId) throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();
        String sql = """
                UPDATE Usuario
                SET estado = 'INACTIVO'
                WHERE id_usuario = ?
                  AND NOT (
                      rol = 'ADMIN'
                      AND estado = 'ACTIVO'
                      AND (
                          (SELECT COUNT(*)
                           FROM Usuario
                           WHERE rol = 'ADMIN' AND estado = 'ACTIVO') <= 1
                      )
                  )
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new DataBaseException("No se puede desactivar: debe existir al menos un administrador activo.");
            }

            return true;
        } catch (SQLException e) {
            throw new DataBaseException("Error al desactivar el usuario", e);
        }
    }

}
