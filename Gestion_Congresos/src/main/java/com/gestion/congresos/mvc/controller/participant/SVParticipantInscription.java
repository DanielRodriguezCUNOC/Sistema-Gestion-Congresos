package com.gestion.congresos.mvc.controller.participant;

import java.io.IOException;

import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.UserAlreadyExistsException;
import com.gestion.congresos.Backend.handler.participant.ParticipantInscriptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVParticipantInscription", urlPatterns = { "/SVParticipantInscription" })
public class SVParticipantInscription extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ParticipantInscriptionHandler handler = new ParticipantInscriptionHandler(request);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String idParam = request.getParameter("idCongress");

        if (action == null || idParam == null) {
            response.getWriter().write("{\"success\": false, \"message\": \"Parámetros inválidos\"}");
            return;
        }

        try {
            boolean result = false;
            switch (action) {
                case "inscripcion":
                    result = handler.registerParticipantToCongress();
                    break;
                case "reservation":
                    result = handler.registerParticipantToWorkshop();
                    break;

                default:
                    break;
            }

            if (result) {
                response.getWriter()
                        .write("{\"success\": true, \"message\": \"Inscripción realizada correctamente\"}");
            } else {

                response.getWriter()
                        .write("{\"success\": false, \"message\": \"No se pudo realizar la inscripción\"}");
            }
        } catch (DataBaseException | UserAlreadyExistsException e) {

            response.getWriter()
                    .write("{\"success\": false, \"message\": \"Error: " + e.getMessage() + "\"}");
        }

    }

}
