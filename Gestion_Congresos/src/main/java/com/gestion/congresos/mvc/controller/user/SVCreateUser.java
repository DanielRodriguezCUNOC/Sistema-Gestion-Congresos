package com.gestion.congresos.mvc.controller.user;

import java.io.IOException;

import com.gestion.congresos.db.controls.user.UserControl;
import com.gestion.congresos.db.models.UserModel;
import com.gestion.congresos.utils.ImageConverter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(name = "SVCreateUser", urlPatterns = { "/SVCreateUser" })
public class SVCreateUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("idRol").isBlank()) {
            int idRol = 4;
            String name = request.getParameter("name");
            String user = request.getParameter("user");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String ID = request.getParameter("ID");
            String phone = request.getParameter("phone");
            Part photoPart = request.getPart("photo");
            byte[] photo = ImageConverter.convertImage(photoPart);
            String organization = request.getParameter("organization");
            String state = request.getParameter("state");

            UserModel newUser = new UserModel(idRol, name, user, password, email, ID, phone, photo, organization,
                    state);

            if (newUser.isValide(newUser)) {

                UserControl userControl = new UserControl();

                if (userControl.validateUser(user)) {
                    request.setAttribute("error", "El nombre de usuario ya existe. Por favor, elija otro.");
                    request.getRequestDispatcher("/mvc/user/createUser.jsp").forward(request, response);
                } else {
                    try {
                        if (userControl.insertUser(newUser)) {
                            response.sendRedirect(request.getContextPath() + "/mvc/user/listUsers.jsp");
                        } else {
                            request.setAttribute("error", "Error al crear el usuario. Por favor, intente de nuevo.");
                            request.getRequestDispatcher("/mvc/user/createUser.jsp").forward(request, response);
                        }
                    } catch (Exception e) {
                        request.setAttribute("error", "Error al crear el usuario: " + e.getMessage());
                        request.getRequestDispatcher("/mvc/user/createUser.jsp").forward(request, response);
                    }

                }

            } else {

                request.setAttribute("error", "Datos inválidos. Por favor, complete todos los campos.");
                request.getRequestDispatcher("/mvc/user/createUser.jsp").forward(request, response);
            }

        }

    }
}
