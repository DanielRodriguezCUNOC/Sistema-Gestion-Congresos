package com.gestion.congresos.mvc.controller.conference_admin;

import java.io.IOException;
import java.util.List;

import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.handler.admin_congress.RoomHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVListRoom", value = "/SVListRoom")
public class SVListRoom extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            RoomHandler roomHandler = new RoomHandler();

            List<String[]> rooms = roomHandler.getAllRooms();

            request.setAttribute("rooms", rooms);
            request.getRequestDispatcher("/mvc/ajax/conference-admin/list-rooms.jsp")
                    .forward(request, response);

        } catch (DataBaseException e) {
            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/mvc/error.jsp").forward(request, response);
        }

    }

}
