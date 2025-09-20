package com.gestion.congresos.mvc.controller.conference_admin;

import java.io.IOException;

import com.gestion.congresos.Backend.db.models.ActivityModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;
import com.gestion.congresos.Backend.handler.ActivityCRUDHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVActivityCRUD", urlPatterns = { "/SVActivityCRUD" })
public class SVActivityCRUD extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("loadEditForm".equals(action)) {
            try {
                ActivityCRUDHandler handler = new ActivityCRUDHandler(request);
                ActivityModel activity = handler.getActivityById();

                request.setAttribute("activity", activity);
                request.getRequestDispatcher("/mvc/ajax/conference-admin/edit-activity.jsp").forward(request, response);
            } catch (DataBaseException | ObjectNotFoundException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("mvc/error.jsp").forward(request, response);

            }
        } else {
            request.setAttribute("error", "Acción no válida");
            request.getRequestDispatcher("mvc/error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ActivityCRUDHandler handler = new ActivityCRUDHandler(request);

        String action = request.getParameter("action");

        String idParam = request.getParameter("activityId");

        if (action == null || idParam == null) {
            response.getWriter().write("{\"success\": false, \"message\": \"Parámetros inválidos\"}");
            return;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            int idActivity = Integer.parseInt(idParam);

            switch (action) {
                case "edit":
                    boolean updated = handler.editActivity(idActivity);
                    if (updated) {
                        response.getWriter()
                                .write("{\"success\": true, \"message\": \"Actividad actualizada correctamente.\"}");
                    } else {
                        response.getWriter()
                                .write("{\"success\": false, \"message\": \"No se pudo actualizar la actividad.\"}");
                    }
                    break;
                case "delete":
                    boolean deleted = handler.deleteActivity(idActivity);
                    if (deleted) {
                        response.getWriter()
                                .write("{\"success\": true, \"message\": \"Actividad eliminada correctamente.\"}");
                    } else {
                        response.getWriter()
                                .write("{\"success\": false, \"message\": \"No se pudo eliminar la actividad.\"}");
                    }
                    break;
                default:
                    response.getWriter().write("{\"success\": false, \"message\": \"Acción no válida.\"}");
            }
        } catch (DataBaseException | ObjectNotFoundException e) {
            response.getWriter().write("{\"success\": false, \"message\": \"Error: " + e.getMessage() + "\"}");
            e.printStackTrace();
        }

    }

}
