package com.gestion.congresos.mvc.controller.user;

import java.io.IOException;

import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;
import com.gestion.congresos.Backend.handler.SysAdminHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SVSystemAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            SysAdminHandler sysAdminHandler = new SysAdminHandler();

            UserModel sysAdmin = sysAdminHandler.getSysAdmin(request);

            if (sysAdmin == null) {
                request.setAttribute("sysAdmin", sysAdmin);
                request.getRequestDispatcher("mvc/error.jsp").forward(request, response);

            }

            request.setAttribute("sysAdmin", sysAdmin);
            request.getRequestDispatcher("mvc/dashboard/sysadmin-dashboard.jsp").forward(request, response);

        } catch (DataBaseException e) {
            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("mvc/error.jsp").forward(request, response);
        } catch (UserNotFoundException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("mvc/error.jsp").forward(request, response);
        }
    }
}
