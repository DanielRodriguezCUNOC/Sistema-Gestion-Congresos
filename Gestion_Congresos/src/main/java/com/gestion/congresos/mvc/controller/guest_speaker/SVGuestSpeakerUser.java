package com.gestion.congresos.mvc.controller.guest_speaker;

import java.io.IOException;

import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.handler.GuestSpeakerHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVGuestSpeakerUser", urlPatterns = { "/SVGuestSpeakerUser" })
public class SVGuestSpeakerUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            GuestSpeakerHandler guestSpeakerHandler = new GuestSpeakerHandler(request);

            UserModel guestSpeaker = guestSpeakerHandler.getGuestSpeaker();

            if (guestSpeaker == null) {
                request.setAttribute("guestSpeaker", guestSpeaker);
                request.getRequestDispatcher("mvc/error.jsp").forward(request, response);
                return;

            }

            request.setAttribute("guestSpeaker", guestSpeaker);
            request.getRequestDispatcher("mvc/dashboard/guest-speaker-dashboard.jsp").forward(request, response);

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
