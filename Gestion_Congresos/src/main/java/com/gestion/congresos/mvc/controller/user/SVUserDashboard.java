package com.gestion.congresos.mvc.controller.user;

import java.io.IOException;

import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;
import com.gestion.congresos.Backend.handler.UserDashboardHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVUserDashboard", urlPatterns = { "/SVUserDashboard" })
public class SVUserDashboard extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDashboardHandler userDashboardHandler = new UserDashboardHandler(request);
        try {
            int rolUser = userDashboardHandler.getUserRolId();
            switch (rolUser) {
                case 1:
                    request.getSession().setAttribute("rolUser", "rolUser");
                    response.sendRedirect("mvc/admin/adminSistemaDashboard.jsp");
                    break;
                case 2:
                    request.getSession().setAttribute("rolUser", "rolUser");
                    response.sendRedirect("mvc/admin/adminCongresoDashboard.jsp");
                    break;
                case 3:
                    request.getSession().setAttribute("rolUser", "rolUser");
                    response.sendRedirect("mvc/user/userComiteCientificoDashboard.jsp");

                    break;
                case 4:
                    request.getSession().setAttribute("rolUser", "rolUser");
                    response.sendRedirect("mvc/user/userDashboard.jsp");
                    break;

                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } catch (DataBaseException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect("mvc/login/login.jsp");
        } catch (UserNotFoundException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect("mvc/login/login.jsp");
        }
    }
}
