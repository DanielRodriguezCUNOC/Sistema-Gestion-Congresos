package com.gestion.congresos.mvc.controller.user;

import java.io.IOException;

import com.gestion.congresos.Backend.db.controls.rol.RolControl;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
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

    private static final int ID_ROL_DEFAULT = 3; // * Representa un usuario de tipo admin de sistema */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserCreateHandler userCreate = new UserCreateHandler(request);

        try {
            boolean inserted = userCreate.createUser(ID_ROL_DEFAULT);
            if (inserted) {
                request.setAttribute("success", "Administrador de sistema creado correctamente");
            } else {

                response.sendRedirect(request.getContextPath() + "/mvc/list/list-admin-system.jsp");
            }
        } catch (MissingDataException m) {
            request.setAttribute("error", m.getMessage());
            request.getRequestDispatcher("/mvc/user/create-admin-conference.jsp").forward(request, response);
        } catch (UserAlreadyExistsException | DataBaseException u) {
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
