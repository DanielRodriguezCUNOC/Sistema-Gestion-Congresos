package com.gestion.congresos.mvc.controller.user;

import java.io.IOException;

import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;
import com.gestion.congresos.Backend.handler.participant.ParticipantHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SVParticipantUser", urlPatterns = { "/SVParticipantUser" })
public class SVParticipantUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        ParticipantHandler participantHandler = new ParticipantHandler();

        if (session == null || !participantHandler.isParticipant(request)) {
            response.sendRedirect("index.jsp");
            return;

        }

        try {

            UserModel user = participantHandler.getParticipant(request);

            if (user == null) {
                request.getSession().setAttribute("error", "Usuario no encontrado.");
                response.sendRedirect("mvc/error.jsp");
                return;
            }

            request.setAttribute("user", user);

            request.getRequestDispatcher("mvc/dashboard/participant-dashboard.jsp").forward(request, response);

        } catch (DataBaseException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect("mvc/error.jsp");

        } catch (UserNotFoundException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect("mvc/error.jsp");
        }

    }
}
