package com.gestion.congresos.Backend.db.controls.participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlParticipantCongress {

    public List<String[]> getAllActiveCongresses() throws DataBaseException {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        List<String[]> congresses = new ArrayList<>();

        String sql = "SELECT c.id_congreso, c.nombre_congreso, c.fecha_inicio, c.fecha_fin, c.precio, "
                + "c.cupo, "
                + "(c.cupo - COUNT(i.id_inscripcion)) as cupos_disponibles, "
                + "i_inst.ubicacion, i_inst.nombre_institucion "
                + "FROM Congreso c "
                + "JOIN Institucion i_inst ON c.id_institucion = i_inst.id_institucion "
                + "LEFT JOIN Inscripcion i ON c.id_congreso = i.id_congreso "
                + "WHERE c.estado = true "
                + "GROUP BY c.id_congreso, c.nombre_congreso, c.fecha_inicio, c.fecha_fin, "
                + "c.precio, c.cupo, i_inst.ubicacion, i_inst.nombre_institucion";

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String[] congressData = new String[8];
                congressData[0] = String.valueOf(rs.getInt("id_congreso"));
                congressData[1] = rs.getString("nombre_congreso");
                congressData[2] = rs.getString("fecha_inicio");
                congressData[3] = rs.getString("fecha_fin");
                congressData[4] = String.valueOf(rs.getDouble("precio"));
                congressData[5] = String.valueOf(rs.getInt("cupos_disponibles"));
                congressData[6] = rs.getString("ubicacion");
                congressData[7] = rs.getString("nombre_institucion");

                congresses.add(congressData);
            }

            return congresses;

        } catch (Exception e) {
            throw new DataBaseException("Error al obtener los congresos activos", e);
        }
    }
}
