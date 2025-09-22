package com.gestion.congresos.mvc.controller.conference_admin;

import java.io.IOException;

import com.gestion.congresos.Backend.db.models.RoomModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;
import com.gestion.congresos.Backend.handler.admin_congress.RoomCRUDHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVRoomCRUD", value = "/SVRoomCRUD")
public class SVRoomCRUD extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RoomCRUDHandler roomCRUDHandler = new RoomCRUDHandler(request);

        String action = request.getParameter("action");
        String idParam = request.getParameter("idRoom");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (action == null || idParam == null) {
            response.getWriter().write("{\"success\": false, \"message\": \"Parámetros inválidos\"}");
            return;
        }

        try {
            boolean result = false;
            switch (action) {
                case "edit":
                    result = roomCRUDHandler.editRoom();
                    break;
                case "delete":
                    result = roomCRUDHandler.deleteRoom();
                    break;

                default:
                    break;
            }

            if (result) {
                response.getWriter()
                        .write("{\"success\": true, \"message\": \"Salón editado correctamente\"}");
            } else {

                response.getWriter()
                        .write("{\"success\": false, \"message\": \"No se pudo editar el Salón\"}");
            }

        } catch (DataBaseException | MissingDataException e) {
            String msg = e.getMessage().replace("\"", "\\\"");
            response.getWriter().write("{\"success\": false, \"message\": \"" + msg + "\"}");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("loadEditForm".equals(action)) {
            try {
                System.out.println("Loading edit form for room ID: " + request.getParameter("idRoom"));
                RoomCRUDHandler handler = new RoomCRUDHandler(request);
                RoomModel room = handler.getRoomById();

                request.setAttribute("room", room);
                request.getRequestDispatcher("/mvc/ajax/conference-admin/edit-room.jsp").forward(request, response);
            } catch (DataBaseException | ObjectNotFoundException | MissingDataException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("mvc/error.jsp").forward(request, response);

            }
        } else {
            request.setAttribute("error", "Acción no válida");
            request.getRequestDispatcher("mvc/error.jsp").forward(request, response);
        }
    }
}
