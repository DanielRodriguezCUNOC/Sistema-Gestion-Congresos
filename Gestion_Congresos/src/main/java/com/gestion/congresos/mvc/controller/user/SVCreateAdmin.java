package com.gestion.congresos.mvc.controller.user;

import java.io.IOException;

import com.gestion.congresos.Backend.db.controls.rol.RolControl;
import com.gestion.congresos.Backend.exceptions.ImageFormatException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.UserAlreadyExistsException;
import com.gestion.congresos.Backend.handler.UserCreateHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVCreateAdmin", urlPatterns = { "/SVCreateAdmin" })
public class SVCreateAdmin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserCreateHandler userCreate = new UserCreateHandler(request);

        try {
            if (userCreate.createUser()) {
                if (request.getParameter("typeUser").equals("Administrador Congreso")) {
                    response.sendRedirect(request.getContextPath() + "/mvc/list/list-admin-conference.jsp");
                } else {

                    response.sendRedirect(request.getContextPath() + "/mvc/list/list-admin-system.jsp");
                }
            }
        } catch (MissingDataException m) {
            request.setAttribute("error", m.getMessage());
            request.getRequestDispatcher("/mvc/user/create-admin-conference.jsp").forward(request, response);
        } catch (UserAlreadyExistsException u) {
            request.setAttribute("error", u.getMessage());
            request.getRequestDispatcher("/mvc/user/create-admin-conference.jsp").forward(request, response);
        } catch (ImageFormatException i) {
            request.setAttribute("error", i.getMessage());
            request.getRequestDispatcher("/mvc/user/create-admin-conference.jsp").forward(request, response);

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RolControl rolControl = new RolControl();
        request.setAttribute("roles", rolControl.getAllRol());

        request.getRequestDispatcher("/mvc/user/create-admin-conference.jsp").forward(request, response);
    }
}
