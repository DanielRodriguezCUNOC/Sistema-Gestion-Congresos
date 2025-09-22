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

@WebServlet(name = "SVAdminCRUD", urlPatterns = { "/SVAdminCRUD" })
public class SVAdminCRUD extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("loadEditForm".equals(action)) {
            try {

                SysAdminHandler handler = new SysAdminHandler(request);
                UserModel admin = handler.getAdminById();

                request.setAttribute("admin", admin);
                request.getRequestDispatcher("/mvc/ajax/admin/edit-admins.jsp")
                        .forward(request, response);
                return;
            } catch (DataBaseException | UserNotFoundException | NumberFormatException e) {
                request.setAttribute("errorMessage", "Error al cargar el administrador: " + e.getMessage());
                request.getRequestDispatcher("/mvc/error.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("errorMessage", "Error inesperado: " + e.getMessage());
                request.getRequestDispatcher("/mvc/error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Acción no válida");
            request.getRequestDispatcher("/mvc/error.jsp").forward(request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SysAdminHandler handler = new SysAdminHandler(request);

        String action = request.getParameter("action");
        String idParam = request.getParameter("id");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (action == null || action.isBlank() || idParam.isBlank() || idParam == null) {
            response.getWriter().write("{\"success\": false, \"message\": \"Parámetros inválidos\"}");
            return;
        }

        try {
            int targetUserId = Integer.parseInt(idParam);
            boolean result = false;

            switch (action) {
                case "edit":

                    result = handler.editAdmin(targetUserId);
                    break;

                case "activate":
                    result = handler.activateAdmin(targetUserId);
                    break;

                case "deactivate":
                    result = handler.deactivateAdmin(targetUserId);
                    break;

                default:
                    response.getWriter().write("{\"success\": false, \"message\": \"Acción no válida\"}");
                    return;
            }

            if (result) {
                response.getWriter().write("{\"success\": true, \"message\": \"Acción realizada correctamente\"}");
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"No se pudo completar la acción\"}");
            }

        } catch (NumberFormatException | DataBaseException e) {
            response.getWriter().write("{\"success\": false, \"message\": \"El ID debe ser numérico\"}");
        } catch (Exception e) {
            response.getWriter()
                    .write("{\"success\": false, \"message\": \"Error inesperado: " + e.getMessage() + "\"}");
        }
    }
}
