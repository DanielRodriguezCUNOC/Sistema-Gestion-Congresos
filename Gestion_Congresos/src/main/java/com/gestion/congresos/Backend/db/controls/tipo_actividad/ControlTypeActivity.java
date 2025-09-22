package com.gestion.congresos.Backend.db.controls.tipo_actividad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;

public class ControlTypeActivity {

    public int getIdTypeActivityByName(String tipoActividad) throws DataBaseException, ObjectNotFoundException {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String query = "SELECT id_tipo_actividad FROM Tipo_Actividad WHERE tipo_actividad = ?";

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

    public int getIdTypeActivityByNameActivity(String nombreActividad)
            throws DataBaseException, ObjectNotFoundException {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String query = "SELECT ta.id_tipo_actividad " +
                "FROM Tipo_Actividad ta " +
                "JOIN Actividad a ON ta.id_tipo_actividad = a.id_tipo_actividad " +
                "WHERE a.nombre_actividad = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nombreActividad);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_tipo_actividad");
                } else {
                    throw new ObjectNotFoundException(
                            "Tipo de actividad no encontrado para la actividad: " + nombreActividad);
                }

            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener el tipo de actividad por nombre de actividad", e);
        }
    }
}
