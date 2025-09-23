package com.gestion.congresos.Backend.db.controls.participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlParticipantInscription {

    public boolean registerParticipantToCongress(int idUsuario, int congressId) throws DataBaseException {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String sql = "INSERT INTO Inscripcion (id_usuario, id_congreso) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, congressId);

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new DataBaseException("Error al registrar la inscripción del participante al congreso", e);
        }
    }

    public boolean isParticipantRegisteredInCongress(int idUsuario, int congressId) throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String sql = "SELECT COUNT(*) FROM Inscripcion WHERE id_usuario = ? AND id_congreso = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, congressId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

            return false;

        } catch (SQLException e) {
            throw new DataBaseException("Error al verificar la inscripción del participante al congreso", e);
        }
    }

    public boolean isParticipantRegisteredInActivity(int idUsuario, int activityId) throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String sql = "SELECT COUNT(*) FROM InscripcionActividad WHERE id_usuario = ? AND id_actividad = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, activityId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

            return false;

        } catch (SQLException e) {
            throw new DataBaseException("Error al verificar la inscripción del participante a la actividad", e);
        }
    }

    public Set<Integer> getRegisteredWorkshopsByParticipant(int idUser) {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String sql = "SELECT id_actividad FROM Reserva_Taller WHERE id_usuario = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUser);

            try (ResultSet rs = ps.executeQuery()) {
                Set<Integer> workshops = new java.util.HashSet<>();
                while (rs.next()) {
                    workshops.add(rs.getInt("id_actividad"));
                }
                return workshops;
            }

        } catch (SQLException e) {
            return java.util.Collections.emptySet();
        }

    }

}
