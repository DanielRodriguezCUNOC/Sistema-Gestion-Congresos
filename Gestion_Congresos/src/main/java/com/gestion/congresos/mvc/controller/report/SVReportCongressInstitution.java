package com.gestion.congresos.mvc.controller.report;

import java.io.IOException;

import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.handler.reports.ReportCongressHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVReportCongressInstitution", urlPatterns = { "/SVReportCongressInstitution" })
public class SVReportCongressInstitution extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            ReportCongressHandler handler = new ReportCongressHandler();
            boolean exito = handler.generateReportCongressInstitution();

            if (exito) {
                request.getRequestDispatcher("/mvc/report/success.jsp").forward(request, response);

            } else {
                request.getRequestDispatcher("/mvc/error.jsp").forward(request, response);

            }
        } catch (DataBaseException e) {
            request.getRequestDispatcher("/mvc/error.jsp").forward(request, response);
        }

    }

}
