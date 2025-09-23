package com.gestion.congresos.Backend.db.controls.participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlParticipantActivity {

    public boolean registerParticipantToWorkshop(int idUser, int idActivity) throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String sql = "INSERT INTO Reserva_Taller (id_usuario, id_activida, fecha_reserva) VALUES (?, ?, NOW())";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUser);
            ps.setInt(2, idActivity);

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new DataBaseException("Error al registrar el participante en el taller", e);
        }

    }

    public boolean isAlreadyRegisteredInWorkshop(int idUser, int idActivity) throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String sql = "SELECT COUNT(*) FROM Reserva_Taller WHERE id_usuario = ? AND id_actividad = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUser);
            ps.setInt(2, idActivity);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

            return false;

        } catch (SQLException e) {
            throw new DataBaseException("Error al verificar la inscripción del participante en el taller", e);
        }
    }

    public List<String[]> getWorkshopsByInscritCongress(int idCongress, int idUser) throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String sql = "SELECT a.id_actividad, a.nombre_actividad, a.descripcion, a.fecha, a.hora_inicio, a.hora_fin, "
                + "CASE WHEN ta.tipo_actividad = 'Taller' THEN a.cupo_taller ELSE NULL END as cupo, "
                + "CASE WHEN ta.tipo_actividad = 'Taller' THEN (a.cupo_taller - COUNT(rt.id_reserva)) ELSE NULL END AS cupos_disponibles, "
                + "i.ubicacion, i.nombre_institucion, ta.tipo_actividad "
                + "FROM Actividad a "
                + "JOIN Congreso c ON a.id_congreso = c.id_congreso "
                + "JOIN Institucion i ON c.id_institucion = i.id_institucion "
                + "JOIN Tipo_Actividad ta ON a.id_tipo_actividad = ta.id_tipo_actividad "
                + "JOIN Inscripcion ins ON c.id_congreso = ins.id_congreso AND ins.id_usuario = ? "
                + "LEFT JOIN Reserva_Taller rt ON a.id_actividad = rt.id_actividad "
                + "WHERE c.id_congreso = ? AND a.estado = true "
                + "GROUP BY a.id_actividad, a.nombre_actividad, a.descripcion, a.fecha, "
                + "a.hora_inicio, a.hora_fin, a.cupo_taller, i.ubicacion, i.nombre_institucion, ta.tipo_actividad";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUser);
            ps.setInt(2, idCongress);

            try (ResultSet rs = ps.executeQuery()) {

                List<String[]> workshops = new java.util.ArrayList<>();

                while (rs.next()) {
                    String[] workshopData = new String[10];
                    workshopData[0] = String.valueOf(rs.getInt("id_actividad"));
                    workshopData[1] = rs.getString("nombre_actividad");
                    workshopData[2] = rs.getString("descripcion");
                    workshopData[3] = rs.getString("fecha");
                    workshopData[4] = rs.getString("hora_inicio");
                    workshopData[5] = rs.getString("hora_fin");
                    workshopData[6] = String.valueOf(rs.getInt("cupo"));
                    workshopData[7] = String.valueOf(rs.getInt("cupos_disponibles"));
                    workshopData[8] = rs.getString("ubicacion");
                    workshopData[9] = rs.getString("nombre_institucion");

                    workshops.add(workshopData);
                }

                return workshops;
            }

        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener los talleres del congreso", e);
        }

    }
}
