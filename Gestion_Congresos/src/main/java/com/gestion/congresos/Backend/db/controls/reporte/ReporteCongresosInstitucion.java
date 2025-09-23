package com.gestion.congresos.Backend.db.controls.reporte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ReporteCongresosInstitucion {

    public List<List<Object>> obtenerCongresosPorInstitucion() throws DataBaseException {
        List<List<Object>> resultados = new ArrayList<>();

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String sql = "SELECT i.nombre_institucion, i.ubicacion, c.nombre_congreso, c.fecha_inicio, c.fecha_fin, c.precio, c.acepta_convocatoria, c.estado, c.cupo "
                +
                "FROM Congreso c " +
                "JOIN Institucion i ON c.id_institucion = i.id_institucion " +
                "ORDER BY i.nombre_institucion, c.fecha_inicio";

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                List<Object> fila = new ArrayList<>();
                fila.add(rs.getString("nombre_institucion"));
                fila.add(rs.getString("ubicacion"));
                fila.add(rs.getString("nombre_congreso"));
                fila.add(rs.getDate("fecha_inicio"));
                fila.add(rs.getDate("fecha_fin"));
                fila.add(rs.getBigDecimal("precio"));
                fila.add(rs.getBoolean("acepta_convocatoria"));
                fila.add(rs.getBoolean("estado"));
                fila.add(rs.getInt("cupo"));
                resultados.add(fila);
            }
            return resultados;

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener los congresos por institución", e);
        }

    }
}