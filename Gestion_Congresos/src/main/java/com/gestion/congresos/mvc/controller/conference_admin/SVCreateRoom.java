package com.gestion.congresos.mvc.controller.conference_admin;

import java.io.IOException;

import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.ObjectAlreadyExists;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;
import com.gestion.congresos.Backend.handler.admin_congress.RoomHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVCreateRoom", value = "/SVCreateRoom")
public class SVCreateRoom extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        RoomHandler roomHandler = new RoomHandler(request);

        try {
            boolean inserted = roomHandler.createRoom();
            if (inserted) {
                response.getWriter()
                        .write("{\"success\": true, \"message\": \"Salón creado correctamente\"}");
            } else {

                response.getWriter()
                        .write("{\"success\": false, \"message\": \"No se pudo crear el Salón\"}");
            }
        } catch (DataBaseException | MissingDataException | ObjectAlreadyExists | ObjectNotFoundException e) {
            String msg = e.getMessage().replace("\"", "\\\"");
            response.getWriter().write("{\"success\": false, \"message\": \"" + msg + "\"}");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("/mvc/ajax/conference-admin/create-room.jsp").forward(request,
                response);

    }

}
