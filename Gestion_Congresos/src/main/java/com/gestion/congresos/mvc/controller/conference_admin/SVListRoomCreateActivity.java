/**
 * This Java servlet class retrieves a list of rooms for creating activities in a conference and sends
 * it as a JSON response.
 */
package com.gestion.congresos.mvc.controller.conference_admin;

import java.io.IOException;
import java.util.List;

import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.handler.admin_congress.CongressActivityHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVListRoomCreateActivity", urlPatterns = "/SVListRoomCreateActivity")
public class SVListRoomCreateActivity extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            CongressActivityHandler institutionRoomHandler = new CongressActivityHandler(request);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            List<String> rooms = institutionRoomHandler.getRoomsByCongressName();

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < rooms.size(); i++) {
                sb.append("\"").append(rooms.get(i)).append("\"");
                if (i < rooms.size() - 1)
                    sb.append(",");
            }
            sb.append("]");
            response.getWriter().write(sb.toString());

        } catch (MissingDataException | DataBaseException e) {
            String msg = e.getMessage().replace("\"", "\\\"");
            response.getWriter().write("{\"success\": false, \"message\": \"" + msg + "\"}");
        }
    }
}
