package com.gestion.congresos.mvc.controller.admin;

import java.io.IOException;
import java.util.List;

import com.gestion.congresos.Backend.db.models.InstitutionModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.handler.SysAdminHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVListInstitution", urlPatterns = { "/SVListInstitution" })
public class SVListInstitution extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("DEBUG >>> Accediendo a SVListInstitution");
        try {
            SysAdminHandler sysAdminHandler = new SysAdminHandler();

            List<InstitutionModel> institutions = sysAdminHandler.getAllInstitutions();
            System.out.println("DEBUG >>> Institutions size: " + institutions.size());

            request.setAttribute("institutions", institutions);

            request.getRequestDispatcher("/mvc/sysadmin/list-institutions.jsp").forward(request, response);

        } catch (DataBaseException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/mvc/error.jsp").forward(request, response);
        }
    }
}
