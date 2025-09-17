package com.gestion.congresos.mvc.controller.user;

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

@WebServlet(name = "SVCreateConferenceAdmin", urlPatterns = { "/SVCreateConferenceAdmin" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 5, // 5MB
        maxRequestSize = 1024 * 1024 * 5) // 5MB
public class SVCreateConferenceAdmin extends HttpServlet {

    private static final int ID_ROL_DEFAULT = 2; // * Representa un usuario de tipo admin de conferencia */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        UserCreateHandler userCreate = new UserCreateHandler(request);

        try {
            boolean inserted = userCreate.createUser(ID_ROL_DEFAULT);
            if (inserted) {
                response.getWriter()
                        .write("{\"success\": true, \"message\": \"Administrador de Congreso creado correctamente\"}");
            } else {

                response.getWriter()
                        .write("{\"success\": false, \"message\": \"No se pudo crear el Administrador de Congreso\"}");
            }
        } catch (MissingDataException | UserAlreadyExistsException | ImageFormatException | DataBaseException e) {
            String msg = e.getMessage().replace("\"", "\\\"");
            response.getWriter().write("{\"success\": false, \"message\": \"" + msg + "\"}");

        }

    }

}
