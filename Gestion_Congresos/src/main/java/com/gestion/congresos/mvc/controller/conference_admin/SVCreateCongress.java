package com.gestion.congresos.mvc.controller.conference_admin;

import java.io.IOException;

import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.ObjectAlreadyExists;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;
import com.gestion.congresos.Backend.handler.CongressHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVCreateCongress", value = "/SVCreateCongress")

public class SVCreateCongress extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        CongressHandler congressHandler = new CongressHandler(request);

        try {
            boolean inserted = congressHandler.createCongress();
            if (inserted) {
                response.getWriter()
                        .write("{\"success\": true, \"message\": \"Congreso creado correctamente\"}");
            } else {

                response.getWriter()
                        .write("{\"success\": false, \"message\": \"No se pudo crear el Congreso\"}");
            }
        } catch (DataBaseException | MissingDataException | ObjectAlreadyExists | ObjectNotFoundException ex) {
            String msg = ex.getMessage().replace("\"", "\\\"");
            response.getWriter().write("{\"success\": false, \"message\": \"" + msg + "\"}");

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.getRequestDispatcher("/mvc/ajax/conference-admin/create-congress.jsp");
    }

}
