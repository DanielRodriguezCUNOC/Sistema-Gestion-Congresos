package com.gestion.congresos.mvc.controller.user;

import java.io.IOException;

import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.UserAlreadyExistsException;
import com.gestion.congresos.Backend.handler.UserCreateHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVCreateUser", urlPatterns = { "/SVCreateUser" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // ! 1 MB
        maxFileSize = 1024 * 1024 * 10, // ! 10 MB
        maxRequestSize = 1024 * 1024 * 15 // ! 15 MB
)
public class SVCreateUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserCreateHandler userCreateHandler = new UserCreateHandler(request);

        try {
            boolean inserted = userCreateHandler.createParticipantUser();

            if (inserted) {
                request.getSession().setAttribute("success", "Usuario registrado exitosamente.");
            } else {
                request.getSession().setAttribute("error", "Error al registrar el usuario.");
            }

        } catch (MissingDataException e) {

            request.getSession().setAttribute("error", e.getMessage());

        } catch (UserAlreadyExistsException e) {

            request.getSession().setAttribute("error", e.getMessage());
        } catch (Exception e) {

            request.getSession().setAttribute("error", "Ocurrió un error: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/mvc/user/create-user.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/mvc/user/create-user.jsp").forward(request, response);
    }
}
