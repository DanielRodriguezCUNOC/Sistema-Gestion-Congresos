package com.gestion.congresos.mvc.controller.admin;

import java.io.IOException;

import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.InstitutionAlredyExists;
import com.gestion.congresos.Backend.handler.admin.SysAdminHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVCreateInstitution", urlPatterns = { "/SVCreateInstitution" })
public class SVCreateInstitution extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // * Generamos el contenido a renderizar en el ajax */

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        SysAdminHandler sysAdminHandler = new SysAdminHandler(request);

        try {
            boolean inserted = sysAdminHandler.addInstitution();
            if (inserted) {
                response.getWriter()
                        .write("{\"success\": true, \"message\": \"Institución creada correctamente\"}");
            } else {

                response.getWriter()
                        .write("{\"success\": false, \"message\": \"No se pudo crear la institución\"}");
            }
        } catch (DataBaseException | InstitutionAlredyExists e) {
            String msg = e.getMessage().replace("\"", "\\\"");
            try {
                response.getWriter().write("{\"success\": false, \"message\": \"" + msg + "\"}");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
