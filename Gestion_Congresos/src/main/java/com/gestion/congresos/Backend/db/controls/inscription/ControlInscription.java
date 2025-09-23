package com.gestion.congresos.Backend.db.controls.inscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;

public class ControlInscription {

    public List<String[]> getInscriptionsToPayByParticipant(int idUser) {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String sql = "SELECT DISTINCT c.id_congreso, c.nombre_congreso, " +
                "c.fecha_inicio, c.fecha_fin, c.precio " +
                "FROM Congreso c " +
                "JOIN Inscripcion i ON c.id_congreso = i.id_congreso " +
                "WHERE i.id_usuario = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUser);

            try (ResultSet rs = ps.executeQuery()) {
                List<String[]> congresses = new ArrayList<>();

                while (rs.next()) {
                    String[] congressData = new String[5];
                    congressData[0] = String.valueOf(rs.getInt("id_congreso"));
                    congressData[1] = rs.getString("nombre_congreso");
                    congressData[2] = rs.getString("fecha_inicio");
                    congressData[3] = rs.getString("fecha_fin");
                    congressData[4] = rs.getString("precio");
                    congresses.add(congressData);
                }

                return congresses;

            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
