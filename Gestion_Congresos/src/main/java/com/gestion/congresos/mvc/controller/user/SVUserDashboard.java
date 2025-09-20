package com.gestion.congresos.mvc.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SVUserDashboard", urlPatterns = { "/SVUserDashboard" })
public class SVUserDashboard extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        int idRol;

        if (session == null || session.getAttribute("idUser") == null || session.getAttribute("idRol") == null) {
            response.sendRedirect(request.getContextPath() + "/mvc/login/login.jsp");
            return;
        }

        try {
            idRol = Integer.parseInt(String.valueOf(session.getAttribute("idRol")));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/mvc/login/login.jsp");
            return;
        }

        try {
            switch (idRol) {
                case 1:
                    request.getSession().setAttribute("idUser", request.getSession().getAttribute("idUser"));
                    response.sendRedirect(request.getContextPath() + "/SVSystemAdmin");
                    break;
                case 2:

                    request.getSession().setAttribute("idUser", request.getSession().getAttribute("idUser"));
                    response.sendRedirect(request.getContextPath() + "/SVConferenceAdmin");
                    break;
                case 3:

                    request.getSession().setAttribute("idUser", request.getSession().getAttribute("idUser"));
                    response.sendRedirect(request.getContextPath() + "/SVCientificCommittee");

                    break;
                case 4 | 5:
                    request.getSession().setAttribute("idUser", request.getSession().getAttribute("idUser"));
                    response.sendRedirect(request.getContextPath() + "/SVParticipantUser");
                    break;

                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("mvc/error.jsp").forward(request, response);
            return;
        }
    }
}
