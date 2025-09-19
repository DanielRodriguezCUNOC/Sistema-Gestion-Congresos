package com.gestion.congresos.mvc.controller.conference_admin;

import java.io.IOException;

import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;
import com.gestion.congresos.Backend.handler.ConferenceAdminHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVConferenceAdmin", urlPatterns = { "/SVConferenceAdmin" })
public class SVConferenceAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            ConferenceAdminHandler conferenceAdminHandler = new ConferenceAdminHandler(request);

            UserModel conferenceAdmin = conferenceAdminHandler.getConferenceAdminById();

            if (conferenceAdmin == null) {
                request.setAttribute("conferenceAdmin", conferenceAdmin);
                request.getRequestDispatcher("mvc/error.jsp").forward(request, response);
                return;
            }

            request.setAttribute("conferenceAdmin", conferenceAdmin);
            request.getRequestDispatcher("mvc/dashboard/conference-admin-dashboard.jsp").forward(request, response);

        } catch (DataBaseException e) {
            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("mvc/error.jsp").forward(request, response);
            return;
        } catch (UserNotFoundException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("mvc/error.jsp").forward(request, response);
            return;
        }
    }

}
