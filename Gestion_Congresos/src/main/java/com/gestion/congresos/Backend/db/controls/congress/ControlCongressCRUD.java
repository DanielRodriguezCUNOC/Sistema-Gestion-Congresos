package com.gestion.congresos.Backend.db.controls.congress;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlCongressCRUD {

    public boolean editCongress(int idCongress, String nombre, String descripcion, Date fechaInicio,
            Date fechaFin,
            String costo, boolean aceptaConvocatoria) throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE Congreso SET nombre_congreso = ?, descripcion = ?, fecha_inicio = ?, fecha_fin = ?, costo = ?, acepta_convocatoria = ? WHERE id_congreso = ?")) {

            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setDate(3, fechaInicio);
            ps.setDate(4, fechaFin);
            ps.setString(5, costo);
            ps.setBoolean(6, aceptaConvocatoria);
            ps.setInt(7, idCongress);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new DataBaseException("Error al actualizar el congreso con ID: " + idCongress, e);

        }

    }
}
