package com.gestion.congresos.mvc.controller.conference_admin;

import java.io.IOException;
import java.util.List;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.handler.ConferenceAdminHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVListActivity", value = "/SVListActivity")
public class SVListActivity extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            ConferenceAdminHandler conferenceAdminHandler = new ConferenceAdminHandler();

            List<String[]> activities = conferenceAdminHandler.getAllActivities();

            request.setAttribute("activities", activities);

            request.getRequestDispatcher("/mvc/ajax/conference-admin/list-activities.jsp").forward(request,
                    response);

        } catch (DataBaseException e) {
            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/mvc/error.jsp").forward(request, response);
        }

    }

}
