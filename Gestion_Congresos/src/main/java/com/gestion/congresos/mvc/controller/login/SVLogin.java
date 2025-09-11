package com.gestion.congresos.mvc.controller.login;

import java.io.IOException;

import com.gestion.congresos.Backend.db.controls.login.ControlLogin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVLogin", urlPatterns = { "/SVLogin" })
public class SVLogin extends HttpServlet {

    /**
     * The above function is a Java servlet method for handling HTTP POST requests.
     * 
     * @param request  The `HttpServletRequest` object represents the request that a
     *                 client makes to a
     *                 servlet. It contains information about the request such as
     *                 parameters, headers, and cookies.
     * @param response The `response` parameter in the `doPost` method of a servlet
     *                 represents the HTTP
     *                 response that will be sent back to the client. You can use
     *                 this parameter to set response headers,
     *                 write data to the response body, and control the response
     *                 status code.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtenemos los datos del formulario
        String user = request.getParameter("user");
        String password = request.getParameter("password");

        ControlLogin controlLogin = new ControlLogin();

        if (controlLogin.userExist(user, password)) {
            // Si el usuario es válido, redirigimos al dashboard de usuario
            response.sendRedirect(request.getContextPath() + "/mvc/dashboard/userDashboard.jsp");
        } else {

            request.setAttribute("error", "Usuario o contraseña incorrectos");

            request.getRequestDispatcher("/mvc/login/login.jsp").forward(request, response);
        }

    }

    /**
     * The `doGet` method forwards the request to the login.jsp page for MVC login
     * functionality.
     * 
     * @param request  The `HttpServletRequest` object represents the request that a
     *                 client makes to a
     *                 servlet. It contains information about the client's request
     *                 such as parameters, headers, and
     *                 cookies.
     * @param response The `response` parameter in the `doGet` method of a servlet
     *                 represents the HTTP
     *                 response that will be sent back to the client. It allows you
     *                 to set response headers, write
     *                 content to the response body, and control the response status
     *                 code. In the provided code snippet,
     *                 the `response`
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/mvc/login/login.jsp").forward(request, response);
    }

}
