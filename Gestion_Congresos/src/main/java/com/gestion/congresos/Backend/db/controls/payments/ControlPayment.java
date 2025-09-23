package com.gestion.congresos.Backend.db.controls.payments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlPayment {

    public boolean insertPayment(int idUser, int idCongreso) throws DataBaseException {

        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String getInscripcionSql = "SELECT id_inscripcion FROM Inscripcion " +
                "WHERE id_usuario = ? AND id_congreso = ?";

        String getPrecioSql = "SELECT precio FROM Congreso WHERE id_congreso = ?";

        String insertPagoSql = "INSERT INTO Pago (id_inscripcion, id_usuario, fecha_pago, monto_pagado) " +
                "VALUES (?, ?, NOW(), ?)";

        try {

            int idInscripcion = -1;
            try (PreparedStatement ps = conn.prepareStatement(getInscripcionSql)) {
                ps.setInt(1, idUser);
                ps.setInt(2, idCongreso);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        idInscripcion = rs.getInt("id_inscripcion");
                    } else {
                        throw new DataBaseException("El usuario no está inscrito en este congreso");
                    }
                }
            }

            double monto = 0.0;
            try (PreparedStatement ps = conn.prepareStatement(getPrecioSql)) {
                ps.setInt(1, idCongreso);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        monto = rs.getDouble("precio");
                    } else {
                        throw new DataBaseException("No se encontró el precio del congreso");
                    }
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(insertPagoSql)) {
                ps.setInt(1, idInscripcion);
                ps.setInt(2, idUser);
                ps.setDouble(3, monto);

                int rowsAffected = ps.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al registrar el pago", e);
        }

    }
}
