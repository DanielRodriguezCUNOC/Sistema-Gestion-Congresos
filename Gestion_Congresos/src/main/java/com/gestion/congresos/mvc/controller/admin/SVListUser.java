package com.gestion.congresos.mvc.controller.admin;

import java.io.IOException;
import java.util.List;

import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.handler.SysAdminHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVListUser", urlPatterns = { "/SVListUser" })
public class SVListUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            SysAdminHandler sysAdminHandler = new SysAdminHandler(request);

            List<String[]> users = sysAdminHandler.getAllUsers();

            request.setAttribute("users", users);

            request.getRequestDispatcher("/mvc/sysadmin/list-users.jsp").forward(request, response);
        } catch (DataBaseException e) {

            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/mvc/error.jsp").forward(request, response);
        }

    }
}
