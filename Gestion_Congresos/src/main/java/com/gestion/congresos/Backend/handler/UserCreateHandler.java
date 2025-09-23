package com.gestion.congresos.Backend.handler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import com.gestion.congresos.Backend.db.Encryption;
import com.gestion.congresos.Backend.db.controls.user.UserControl;
import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.ImageFormatException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.UserAlreadyExistsException;
import com.gestion.congresos.Backend.validations.ValidatorData;
import com.gestion.congresos.utils.ImageConverter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

/**
 * The UserCreateHandler class initializes with a HttpServletRequest object and
 * a ValidatorData object.
 */
public class UserCreateHandler {

    private static final int ID_ROL_DEFAULT = 4; // * Representa un usuario de tipo participante */

    private ValidatorData validatorData;
    private HttpServletRequest request;

    public UserCreateHandler(HttpServletRequest request) {
        this.request = request;
        this.validatorData = new ValidatorData();
    }

    /**
     * The `createUser` function in Java handles the creation of a user with a
     * specified role, defaulting
     * to "Participante" if no role is provided.
     * 
     * @return The method `createUser()` is returning a boolean value.
     */
    public boolean createUser(int idRol)
            throws MissingDataException, UserAlreadyExistsException, ImageFormatException, DataBaseException {

        if (idRol <= 0) {
            idRol = ID_ROL_DEFAULT;
            return createUserWithRole(idRol);
        }
        return createUserWithRole(idRol);
    }

    /**
     * The function `createUserWithRole` creates a new user with a specified role
     * based on form data,
     * performing validations and encryption, and handles exceptions related to
     * missing data and existing
     * users.
     * 
     * @param idRol The `idRol` parameter in the `createUserWithRole` method
     *              represents the role ID that
     *              will be assigned to the new user being created. This ID
     *              typically corresponds to a specific role or
     *              permission level within the system. By passing this parameter,
     *              you are specifying the role that the
     *              new user will
     * @return The method `createUserWithRole` is returning a boolean value. It
     *         returns `true` if the user
     *         creation process is successful and the user is inserted into the
     *         database, otherwise it returns
     *         `false` if there are any exceptions thrown during the process or if
     *         the user already exists.
     */

    private boolean createUserWithRole(int idRol)
            throws MissingDataException, UserAlreadyExistsException, ImageFormatException, DataBaseException {
        try {
            String name = getFormField(request, "name");
            String user = getFormField(request, "user");
            String passwordRaw = getFormField(request, "password");
            if (!validatorData.isValidPassword(passwordRaw)) {
                throw new MissingDataException(
                        "La contraseña no es válida. Debe tener al menos 8 caracteres, incluyendo una letra mayúscula, una letra minúscula y un número.");

            }

            String email = getFormField(request, "email");
            String password = Encryption.encryptPassword(passwordRaw, email);
            String ID = getFormField(request, "ID");
            String phone = getFormField(request, "phone");
            String organization = getFormField(request, "organization");
            Part photoPart = request.getPart("photo");
            byte[] photo = ImageConverter.convertImage(photoPart);

            UserModel newUser = new UserModel(
                    idRol, name, user, password, email, ID, phone, photo, organization, "ACTIVO");

            if (!newUser.isValide(newUser)) {
                throw new MissingDataException("No se han proporcionado todos los datos requeridos.");
            }

            if (!validatorData.isValidName(name)) {
                throw new MissingDataException("El nombre no es válido.");
            }
            if (!validatorData.isValidUsername(user)) {
                throw new MissingDataException("El nombre de usuario no es válido.");
            }
            if (!validatorData.isValidEmail(email)) {
                throw new MissingDataException("El correo electrónico no es válido.");
            }
            if (!validatorData.isValidID(ID)) {
                throw new MissingDataException("La identificación no es válida.");
            }
            if (!validatorData.isValidPhone(phone)) {
                throw new MissingDataException("El número de teléfono no es válido.");
            }
            if (!validatorData.isValidOrganization(organization)) {
                throw new MissingDataException("Los datos de la organización no son válidos.");
            }

            UserControl userControl = new UserControl();
            if (userControl.validateUser(newUser.getUser())) {
                throw new UserAlreadyExistsException("El nombre de usuario ya existe. Por favor, elija otro.");
            }

            try {
                return userControl.insertUser(newUser);
            } catch (SQLException e) {
                return false;
            }

        } catch (IOException | ServletException e) {
            return false;
        }
    }

    /**
     * The function `getFormField` retrieves and trims the content of a form field
     * from an HTTP request in
     * Java.
     * 
     * @param request   HttpServletRequest request: Represents the request that a
     *                  client sends to a servlet.
     *                  It contains information about the client request such as
     *                  parameters, headers, and cookies.
     * @param fieldName The `fieldName` parameter in the `getFormField` method is a
     *                  `String` that
     *                  represents the name of the form field whose value needs to
     *                  be retrieved from the
     *                  `HttpServletRequest` object.
     * @return The method `getFormField` returns a `String` value representing the
     *         content of the form
     *         field specified by the `fieldName` parameter in the
     *         `HttpServletRequest` object. If the form field
     *         exists and has content, the method returns the content as a trimmed
     *         `String`. If the form field does
     *         not exist or has no content, the method returns `null`.
     */
    private String getFormField(HttpServletRequest request, String fieldName) throws IOException, ServletException {
        Part part = request.getPart(fieldName);
        if (part != null) {
            return new String(part.getInputStream().readAllBytes(), StandardCharsets.UTF_8).trim();
        }
        return null;
    }
}
