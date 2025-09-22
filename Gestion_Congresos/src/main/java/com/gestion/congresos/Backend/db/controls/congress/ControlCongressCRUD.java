package com.gestion.congresos.Backend.db.controls.congress;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlCongressCRUD {

    public boolean editCongress(int idCongress, String nombre, Date fechaInicio,
            Date fechaFin,
            String costo, boolean aceptaConvocatoria, boolean estado) throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String sql = "UPDATE Congreso SET nombre_congreso = ?, fecha_inicio = ?, fecha_fin = ?, precio = ?, acepta_convocatoria = ?, estado = ? WHERE id_congreso = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setDate(2, fechaInicio);
            ps.setDate(3, fechaFin);
            ps.setString(4, costo);
            ps.setBoolean(5, aceptaConvocatoria);
            ps.setBoolean(6, estado);
            ps.setInt(7, idCongress);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new DataBaseException("Error al actualizar el congreso con ID: " + idCongress, e);

        }

    }
}
