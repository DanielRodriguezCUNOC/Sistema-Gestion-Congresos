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

@WebServlet(name = "SVListAdmin", urlPatterns = { "/SVListAdmin" })
public class SVListAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            SysAdminHandler sysAdminHandler = new SysAdminHandler(request);

            List<String[]> admins = sysAdminHandler.getAllAdmins();

            request.setAttribute("admins", admins);

            request.getRequestDispatcher("/mvc/sysadmin/list-admins.jsp").forward(request, response);
        } catch (DataBaseException e) {

            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/mvc/error.jsp").forward(request, response);
        }

    }
}
