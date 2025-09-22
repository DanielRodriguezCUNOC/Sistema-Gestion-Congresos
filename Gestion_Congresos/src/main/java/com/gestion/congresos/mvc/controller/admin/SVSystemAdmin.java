package com.gestion.congresos.mvc.controller.admin;

import java.io.IOException;

import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;
import com.gestion.congresos.Backend.handler.admin.SysAdminHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SVSystemAdmin", urlPatterns = { "/SVSystemAdmin" })
public class SVSystemAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("idUser") == null
                || !"1".equals(session.getAttribute("idRol").toString())) {
            response.sendRedirect("index.jsp");
            return;

        }

        try {
            SysAdminHandler sysAdminHandler = new SysAdminHandler(request);

            UserModel sysAdmin = sysAdminHandler.getSysAdmin();

            if (sysAdmin == null) {
                request.setAttribute("sysAdmin", sysAdmin);
                request.getRequestDispatcher("mvc/error.jsp").forward(request, response);
                return;
            }

            request.setAttribute("sysAdmin", sysAdmin);
            request.getRequestDispatcher("mvc/dashboard/sysadmin-dashboard.jsp").forward(request, response);

        } catch (DataBaseException e) {
            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("mvc/error.jsp").forward(request, response);
            return;
        } catch (UserNotFoundException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("mvc/error.jsp").forward(request, response);
            return;
        }
    }
}
