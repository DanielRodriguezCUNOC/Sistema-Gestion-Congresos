package com.gestion.congresos.mvc.controller.conference_admin;

import java.io.IOException;

import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.ImageFormatException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.UserAlreadyExistsException;
import com.gestion.congresos.Backend.handler.UserCreateHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVCreateGuestsSpeaker", value = "/SVCreateGuestsSpeaker")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 1, // 5MB
        maxRequestSize = 1024 * 1024 * 5) // 5MB
public class SVCreateGuestsSpeaker extends HttpServlet {

    private static final int ID_ROL_DEFAULT = 5;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserCreateHandler userCreate = new UserCreateHandler(request);

        try {
            boolean inserted = userCreate.createUser(ID_ROL_DEFAULT);
            if (inserted) {
                response.getWriter()
                        .write("{\"success\": true, \"message\": \"Ponente Invitado creado correctamente\"}");
            } else {

                response.getWriter()
                        .write("{\"success\": false, \"message\": \"No se pudo crear el Ponente Invitado\"}");
            }
        } catch (MissingDataException | UserAlreadyExistsException | ImageFormatException | DataBaseException e) {
            String msg = e.getMessage().replace("\"", "\\\"");
            response.getWriter().write("{\"success\": false, \"message\": \"" + msg + "\"}");

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/mvc/dashboard/ajax/conference-admin/create-guest-speaker.jsp");

    }

}
