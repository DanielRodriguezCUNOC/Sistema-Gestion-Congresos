package com.gestion.congresos.mvc.controller.login;

import java.io.IOException;

import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;
import com.gestion.congresos.Backend.handler.LoginHandler;

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

        LoginHandler loginHandler = new LoginHandler(request);

        try {
            if (loginHandler.autenticateUser()) {

                int idUser = loginHandler.getUserId();
                // * Enviamos el id del usuario loggeado */
                request.getSession().setAttribute("idUser", idUser);
                response.sendRedirect("SVUserDashboard");
            } else {
                request.getSession().setAttribute("error", "Usuario o contraseña incorrectos.");
            }
        } catch (MissingDataException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect("mvc/login/login.jsp");
        } catch (UserNotFoundException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect("mvc/login/login.jsp");
        } catch (DataBaseException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect("mvc/login/login.jsp");
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
