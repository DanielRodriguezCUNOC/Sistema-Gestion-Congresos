package com.gestion.congresos.mvc.controller.conference_admin;

import java.io.IOException;

import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.ObjectAlreadyExists;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;
import com.gestion.congresos.Backend.handler.admin_congress.ActivityHandler;
import com.gestion.congresos.Backend.handler.admin_congress.CongressActivityHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVCreateActivity", value = "/SVCreateActivity")
public class SVCreateActivity extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        ActivityHandler activityHandler = new ActivityHandler(request);
        try {
            boolean inserted = activityHandler.createActivity();
            if (inserted) {
                response.getWriter()
                        .write("{\"success\": true, \"message\": \"Actividad creada correctamente\"}");
            } else {

                response.getWriter()
                        .write("{\"success\": false, \"message\": \"No se pudo crear la Actividad\"}");
            }
        } catch (DataBaseException | MissingDataException | ObjectAlreadyExists | ObjectNotFoundException e) {
            String msg = e.getMessage().replace("\"", "\\\"");
            response.getWriter().write("{\"success\": false, \"message\": \"" + msg + "\"}");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        CongressActivityHandler institutionRoomHandler = new CongressActivityHandler();
        try {
            request.setAttribute("congresses", institutionRoomHandler.getNamesCongresses());

            request.getRequestDispatcher("/mvc/ajax/conference-admin/create-activity.jsp").forward(request,
                    response);
        } catch (DataBaseException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/mvc/error.jsp").forward(request, response);
            return;
        }

    }

}
