package com.gestion.congresos.Backend.db.controls.tipo_actividad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;

public class ControlTipoActividad {

    public int getIdTipeActivityByName(String tipoActividad) throws DataBaseException, ObjectNotFoundException {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String query = "SELECT id_tipo_actividad FROM Tipo_Actividad WHERE nombre_tipo_actividad = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, tipoActividad);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_tipo_actividad");
                } else {
                    throw new ObjectNotFoundException("Tipo de actividad no encontrado: " + tipoActividad);
                }

            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener el tipo de actividad", e);
        }
    }
}
