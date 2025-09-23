package com.gestion.congresos.mvc.controller.participant;

import java.io.IOException;
import java.util.List;

import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.handler.participant.ParticipantActivityHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVListWorkshopForParticipant", urlPatterns = { "/SVListWorkshopForParticipant" })
public class SVListWorkshopForParticipant extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            ParticipantActivityHandler handler = new ParticipantActivityHandler(request);

            List<String[]> activities = handler.getWorkshopsByCongress();

            request.setAttribute("listaTalleres", activities);

            request.getRequestDispatcher("/mvc/ajax/participant/select-activity.jsp").forward(request, response);

        } catch (DataBaseException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/mvc/error.jsp").forward(request, response);
        }

    }

}
