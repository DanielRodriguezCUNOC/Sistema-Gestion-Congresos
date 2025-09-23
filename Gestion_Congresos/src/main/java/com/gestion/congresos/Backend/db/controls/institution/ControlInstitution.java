package com.gestion.congresos.Backend.db.controls.institution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.db.models.InstitutionModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlInstitution {

    private static final String INSERT_NEW_INSTITUTION = "INSERT INTO Institucion (nombre_institucion, ubicacion) VALUES (?, ?)";

    private static final String CHECK_INSTITUTION_EXISTS = "SELECT COUNT(*) FROM Institucion WHERE nombre_institucion = ?";

    public boolean insertInstitution(InstitutionModel institution) throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();
        try {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(INSERT_NEW_INSTITUTION)) {
                ps.setString(1, institution.getName_institution());
                ps.setString(2, institution.getAddress_institution());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al insertar la institucion", e);
        }
    }

    public boolean existsInstitution(String name_institution) throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();
        try (
                PreparedStatement ps = conn.prepareStatement(CHECK_INSTITUTION_EXISTS)) {

            ps.setString(1, name_institution);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
            return false;

        } catch (SQLException e) {
            throw new DataBaseException("Error al verificar la existencia de la institución", e);
        }
    }

    public int getIdInstitutionByName(String name_institution) throws DataBaseException {
        String query = "SELECT id_institucion FROM Institucion WHERE nombre_institucion = ?";
        Connection conn = DBConnectionSingleton.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name_institution);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_institucion");
                } else {
                    return -1;
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener el ID de la institución", e);
        }
    }

}
