package com.gestion.congresos.mvc.controller.admin;

import java.io.IOException;
import com.gestion.congresos.Backend.handler.SysAdminHandler;
import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;

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
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
            } catch (DataBaseException | UserNotFoundException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inesperado: " + e.getMessage());
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
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

        if (action == null || idParam == null) {
            response.getWriter().write("{\"success\": false, \"message\": \"Parámetros inválidos\"}");
            return;
        }

        try {
            int targetUserId = Integer.parseInt(idParam);
            boolean result = false;

            switch (action) {
                case "edit":
                    String name = request.getParameter("name");
                    String user = request.getParameter("user");
                    String phone = request.getParameter("phone");
                    String organization = request.getParameter("organization");

                    result = handler.editAdmin(targetUserId, name, user, phone, organization);
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

        } catch (NumberFormatException e) {
            response.getWriter().write("{\"success\": false, \"message\": \"El ID debe ser numérico\"}");
        } catch (DataBaseException e) {
            response.getWriter()
                    .write("{\"success\": false, \"message\": \"Error en la base de datos: " + e.getMessage() + "\"}");
        } catch (Exception e) {
            response.getWriter()
                    .write("{\"success\": false, \"message\": \"Error inesperado: " + e.getMessage() + "\"}");
        }
    }
}
