package com.gestion.congresos.Backend.db.controls.payments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlPaymentCongress {

    public boolean alreadyUserPaymentCongress(int idUser, int idInscription) {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String sql = "SELECT COUNT(*) FROM Pago WHERE id_usuario = ? AND id_inscription = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUser);
            ps.setInt(2, idInscription);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }

    public List<Integer> getCongressIdsPaidByUser(int idUser) throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();
        List<Integer> congressIds = new ArrayList<>();

        String sql = "SELECT DISTINCT c.id_congreso " +
                "FROM Congreso c " +
                "JOIN Inscripcion i ON c.id_congreso = i.id_congreso " +
                "JOIN Pago p ON i.id_inscripcion = p.id_inscripcion " +
                "WHERE p.id_usuario = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUser);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    congressIds.add(rs.getInt("id_congreso"));
                }
            }
            return congressIds;
        } catch (Exception e) {
            throw new DataBaseException("Error al obtener IDs de congresos pagados del usuario", e);
        }
    }

}
