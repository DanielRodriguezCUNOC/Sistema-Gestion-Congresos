package com.gestion.congresos.Backend.db.controls.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.db.models.InstitutionModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlSysAdmin {

    private static final String GET_CONFERENCE_ADMIN = "SELECT u.nombre, u.usuario, u.correo, u.identificacion_personal, u.telefono, u.organizacion, u.estado, r.rol AS rol "
            + "FROM Usuario u "
            + "JOIN Rol r ON u.id_rol = r.id_rol "
            + "WHERE r.id_rol = 2";

    private static final String INSERT_NEW_INSTITUTION = "INSERT INTO Institucion (nombre_institucion, ubicacion) VALUES (?, ?)";

    private static final String CHECK_INSTITUTION_EXISTS = "SELECT COUNT(*) FROM Institucion WHERE nombre_institucion = ?";

    private static final String GET_ALL_INSTITUTIONS = "SELECT nombre_institucion, ubicacion FROM Institucion";

    private static final String GET_ALL_USERS = "SELECT u.id_usuario, u.nombre, u.usuario, u.correo, " +
            "u.identificacion_personal, u.telefono, u.organizacion, " +
            "u.estado, r.rol AS tipo_rol " +
            "FROM Usuario u " +
            "JOIN Rol r ON u.id_rol = r.id_rol";

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
            e.printStackTrace();
            throw new DataBaseException("Error al verificar la existencia de la institución", e);
        }
    }

    public List<InstitutionModel> getAllInstitutions() throws DataBaseException {
        List<InstitutionModel> institutions = new ArrayList<>();
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        try (
                PreparedStatement ps = conn.prepareStatement(GET_ALL_INSTITUTIONS);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                InstitutionModel institution = new InstitutionModel();
                institution.setName_institution(rs.getString("nombre_institucion"));
                institution.setAddress_institution(rs.getString("ubicacion"));
                institutions.add(institution);
            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener las instituciones", e);
        }
        return institutions;
    }

    public List<String[]> getAllUsers() throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        List<String[]> users = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(GET_ALL_USERS)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    String[] userData = new String[9];
                    userData[0] = String.valueOf(rs.getInt("id_usuario"));
                    userData[1] = rs.getString("nombre");
                    userData[2] = rs.getString("usuario");
                    userData[3] = rs.getString("correo");
                    userData[4] = rs.getString("identificacion_personal");
                    userData[5] = rs.getString("telefono");
                    userData[6] = rs.getString("organizacion");
                    userData[7] = rs.getString("estado");
                    userData[8] = rs.getString("tipo_rol");

                    users.add(userData);
                }
            }
            return users;
        } catch (SQLException e) {
            throw new DataBaseException("Ha ocurrido un error al acceder a la base de datos.", e);
        }

    }

}
