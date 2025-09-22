package com.gestion.congresos.mvc.controller.participant;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVListCongressForParticipant", urlPatterns = { "/SVListCongressForParticipant" })
public class SVListCongressForParticipant extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

        } catch (Exception e) {
            // TODO: handle exception
        }
        // Lógica para manejar la solicitud GET
    }

}
