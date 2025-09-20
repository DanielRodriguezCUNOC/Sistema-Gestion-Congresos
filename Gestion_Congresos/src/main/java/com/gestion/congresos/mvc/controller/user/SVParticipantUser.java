package com.gestion.congresos.mvc.controller.user;

import java.io.IOException;

import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;
import com.gestion.congresos.Backend.handler.ParticipantHandler;

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
        if (session == null || session.getAttribute("idUser") == null
                || !"4".equals(session.getAttribute("rolId").toString())
                || !"5".equals(session.getAttribute("rolId").toString())) {
            response.sendRedirect("index.jsp");
            return;

        }

        try {
            ParticipantHandler participantHandler = new ParticipantHandler();

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
