package com.gestion.congresos.mvc.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.gestion.congresos.Backend.exceptions.ImageFormatException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.UserAlreadyExistsException;
import com.gestion.congresos.Backend.handler.UserCreateHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This Java class named SVCreateUser is a servlet that handles creating users
 * and includes multipart
 * configuration for file uploads.
 */
@WebServlet(name = "SVCreateUser", urlPatterns = { "/SVCreateUser" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // ! 1 MB
        maxFileSize = 1024 * 1024 * 5, // ! 10 MB
        maxRequestSize = 1024 * 1024 * 5 // ! 15 MB
)
public class SVCreateUser extends HttpServlet {

    /**
     * This Java function handles the creation of a user, displaying success or
     * error messages based on
     * the outcome, and redirects to a specific JSP page.
     * 
     * @param request  The `request` parameter in the `doPost` method of a servlet
     *                 represents the client's
     *                 request to the server. It contains information such as
     *                 parameters, headers, and cookies sent by
     *                 the client. In the provided code snippet, the
     *                 `HttpServletRequest` object `request` is used to
     *                 handle the user
     * @param response The `response` parameter in the `doPost` method of a servlet
     *                 represents the
     *                 response that the servlet sends back to the client. It is an
     *                 object of the `HttpServletResponse`
     *                 class, which provides methods for setting the response
     *                 status, headers, and content.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserCreateHandler userCreateHandler = new UserCreateHandler(request);

        try {
            boolean inserted = userCreateHandler.createUser();

            if (inserted) {
                request.setAttribute("success", "Usuario creado correctamente");

            } else {
                request.setAttribute("error", "No se ha podido crear el usuario");
                recoveryDataFromForm(request);

            }

        } catch (MissingDataException e) {
            request.setAttribute("error", e.getMessage());
            recoveryDataFromForm(request);

        } catch (UserAlreadyExistsException e) {
            request.setAttribute("error", e.getMessage());
            recoveryDataFromForm(request);
        } catch (ImageFormatException e) {
            request.setAttribute("error", e.getMessage());
            recoveryDataFromForm(request);
        }

        request.getRequestDispatcher("/mvc/user/create-user.jsp").forward(request, response);
    }

    /**
     * The `doGet` method forwards the request to the "create-user.jsp" page for
     * user creation in a
     * Java servlet.
     * 
     * @param request  The `HttpServletRequest` object represents the request that a
     *                 client makes to a
     *                 servlet. It contains information about the request such as
     *                 parameters, headers, and cookies.
     * @param response The `response` parameter in the `doGet` method of a servlet
     *                 represents the HTTP
     *                 response that will be sent back to the client. It allows you
     *                 to set response headers, write
     *                 content to the response body, and control the response status
     *                 code. In this specific code
     *                 snippet, the `response`
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/mvc/user/create-user.jsp").forward(request, response);
    }

    private void recoveryDataFromForm(HttpServletRequest request) {
        Map<String, String> dataForm = new HashMap<>();

        request.setAttribute("savedName", request.getParameter("name"));
        request.setAttribute("savedUser", request.getParameter("user"));
        request.setAttribute("savedEmail", request.getParameter("email"));
        request.setAttribute("savedID", request.getParameter("ID"));
        request.setAttribute("savedPhone", request.getParameter("phone"));
        request.setAttribute("savedOrganization", request.getParameter("organization"));

        String[] fields = { "name", "user", "email", "ID", "phone", "organization" };

        for (String field : fields) {
            String value = request.getParameter(field);
            if (value != null) {
                dataForm.put(field, value);
            }

        }
        request.setAttribute("formData", dataForm);
    }
}
