package com.gestion.congresos.mvc.controller.conference_admin;

import java.io.IOException;

import com.gestion.congresos.Backend.db.models.CongressModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;
import com.gestion.congresos.Backend.handler.admin_congress.CongressCRUDHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVCongressCRUD", urlPatterns = { "/SVCongressCRUD" })
public class SVCongressCRUD extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("loadEditForm".equals(action)) {
            try {
                CongressCRUDHandler handler = new CongressCRUDHandler(request);

                CongressModel congress = handler.getCongressById();

                request.setAttribute("congress", congress);

                request.getRequestDispatcher("mvc/ajax/conference-admin/edit-congress.jsp").forward(request, response);

            } catch (DataBaseException | ObjectNotFoundException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("mvc/error.jsp").forward(request, response);

            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CongressCRUDHandler handler = new CongressCRUDHandler(request);

        String action = request.getParameter("action");

        String idParam = request.getParameter("congressId");

        if (action == null || idParam == null) {
            response.getWriter().write("{\"success\": false, \"message\": \"Parámetros inválidos\"}");
            return;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            int idCongress = Integer.parseInt(idParam);

            switch (action) {
                case "edit":

                    boolean updated = handler.editCongress(idCongress);
                    if (updated) {

                        response.getWriter()
                                .write("{\"success\": true, \"message\": \"Congreso editado exitosamente.\"}");
                    } else {
                        response.getWriter()
                                .write("{\"success\": false, \"message\": \"Error al editar el congreso.\"}");
                    }
                    break;
                default:
                    response.getWriter().write("{\"success\": false, \"message\": \"Acción no válida.\"}");
            }
        } catch (DataBaseException e) {
            response.getWriter().write("{\"success\": false, \"message\": \"Error: " + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }
}
