package com.gestion.congresos.Backend.db.controls.congress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gestion.congresos.Backend.db.DBConnectionSingleton;
import com.gestion.congresos.Backend.exceptions.DataBaseException;

public class ControlCongressActivity {

    public List<String> getNamesCongresses() throws DataBaseException {
        Connection conn = DBConnectionSingleton.getInstance().getConnection();

        String sql = "SELECT nombre_congreso FROM Congreso";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            List<String> namesCongress = new ArrayList<>();

            while (rs.next()) {
                namesCongress.add(rs.getString("nombre_congreso"));
            }
            return namesCongress;

        } catch (SQLException e) {
            throw new DataBaseException("Ocurrió un error al obtener los nombres de los congresos", e);
        }
    }
}
