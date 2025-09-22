package com.gestion.congresos.Backend.db.controls.participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlParticipantCongress {

    public List<String[]> getActiveCongress() throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        List<String[]> congresses = new ArrayList<>();

        String sql = "SELECT c.id_congreso, c.nombre_congreso, i.nombre_institucion, c.fecha_inicio, c.fecha_fin, c.cupo"
                +
                "FROM Congreso c" +
                "JOIN Institucion i ON c.id_institucion = i.id_institucion" +
                "WHERE c.estado = true";

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String[] congressData = new String[6];
                congressData[0] = String.valueOf(rs.getInt("id_congreso"));
                congressData[1] = rs.getString("nombre_congreso");
                congressData[2] = rs.getString("nombre_institucion");
                congressData[3] = rs.getString("fecha_inicio");
                congressData[4] = rs.getString("fecha_fin");
                congressData[5] = String.valueOf(rs.getInt("cupo"));

                congresses.add(congressData);
            }

            return congresses;

        } catch (Exception e) {
            throw new DataBaseException("Error al obtener los congresos activos", e);
        }
    }
}
