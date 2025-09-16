package com.gestion.congresos.mvc.controller.admin;

import java.io.IOException;
import java.util.List;

import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.handler.SysAdminHandler;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVListConferenceAdmin", urlPatterns = { "/SVListConferenceAdmin" })
public class SVListConferenceAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            SysAdminHandler sysAdminHandler = new SysAdminHandler();
            List<String[]> conferenceAdmins = sysAdminHandler.getAllConferenceAdmins();

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < conferenceAdmins.size(); i++) {
                String[] admin = conferenceAdmins.get(i);
                sb.append("{")
                        .append("\"nombre\":\"").append(admin[0]).append("\",")
                        .append("\"usuario\":\"").append(admin[1]).append("\",")
                        .append("\"correo\":\"").append(admin[2]).append("\",")
                        .append("\"identificacion\":\"").append(admin[3]).append("\",")
                        .append("\"telefono\":\"").append(admin[4]).append("\",")
                        .append("\"organizacion\":\"").append(admin[5]).append("\",")
                        .append("\"estado\":\"").append(admin[6]).append("\",")
                        .append("\"rol\":\"").append(admin[7]).append("\"")
                        .append("}");
                if (i < conferenceAdmins.size() - 1)
                    sb.append(",");
            }
            sb.append("]");
            response.getWriter().write(sb.toString());

        } catch (DataBaseException e) {
            response.getWriter().write("[]");
            e.printStackTrace();
        }
    }

}
