package com.gestion.congresos.Backend.handler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import com.gestion.congresos.Backend.db.Encryption;
import com.gestion.congresos.Backend.db.controls.rol.RolControl;
import com.gestion.congresos.Backend.db.controls.user.UserControl;
import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.UserAlreadyExistsException;
import com.gestion.congresos.utils.ImageConverter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class UserCreateHandler {

    private HttpServletRequest request;

    public UserCreateHandler(HttpServletRequest request) {
        this.request = request;
    }

    public boolean createUser() throws MissingDataException, UserAlreadyExistsException {

        String typeUser;
        try {

            typeUser = getFormField(request, "typeUser");
        } catch (IOException | ServletException e) {
            return false;
        }
        int idRol = getIdRol(typeUser);

        switch (idRol) {
            case 1:

                return createAdmin(idRol);

            case 2:
                return createAdmin(idRol);

            default:
                return false;
        }
    }

    public boolean createParticipantUser() throws MissingDataException, UserAlreadyExistsException {
        int idRol = 4;

        try {

            String name = getFormField(request, "name");
            String user = getFormField(request, "user");
            String passwordRaw = getFormField(request, "password");
            String password = Encryption.encryptPassword(passwordRaw);
            String email = getFormField(request, "email");
            String ID = getFormField(request, "ID");
            String phone = getFormField(request, "phone");
            String organization = getFormField(request, "organization");

            System.out.println("Valores recibidos:");
            System.out.println("name=" + name);
            System.out.println("user=" + user);
            System.out.println("password=" + passwordRaw);
            System.out.println("email=" + email);
            System.out.println("ID=" + ID);
            System.out.println("phone=" + phone);
            System.out.println("organization=" + organization);
            System.out.println("idRol=" + idRol);

            // * Obtener foto
            Part photoPart = request.getPart("photo");
            byte[] photo = ImageConverter.convertImage(photoPart);

            // * Crear objeto usuario
            UserModel newUser = new UserModel(idRol, name, user, password, email, ID, phone, photo, organization,
                    "ACTIVO");

            // * Validar datos
            if (!newUser.isValide(newUser)) {
                throw new MissingDataException("No se han proporcionado todos los datos requeridos.");
            }

            UserControl userControl = new UserControl();

            // * Verificamos que no hay un usuario con el mismo nombre de usuario */
            if (userControl.validateUser(newUser.getUser())) {
                throw new UserAlreadyExistsException("El nombre de usuario ya existe. Por favor, elija otro.");
            }

            try {
                boolean inserted = userControl.insertUser(newUser);
                System.out.println("Usuario insertado: " + inserted);
                return inserted;

            } catch (java.sql.SQLException e) {
                e.printStackTrace();
                return false;
            }

        } catch (IOException | ServletException e) {
            return false;
        }
    }

    public boolean createAdmin(int idRol) throws MissingDataException, UserAlreadyExistsException {

        try {

            String name = getFormField(request, "name");
            String user = getFormField(request, "user");
            String passwordRaw = getFormField(request, "password");
            String password = Encryption.encryptPassword(passwordRaw);
            String email = getFormField(request, "email");
            String ID = getFormField(request, "ID");
            String phone = getFormField(request, "phone");
            String organization = getFormField(request, "organization");

            // Obtener foto
            Part photoPart = request.getPart("photo");
            byte[] photo = ImageConverter.convertImage(photoPart);

            // Crear objeto usuario
            UserModel newUser = new UserModel(idRol, name, user, password, email, ID, phone, photo, organization,
                    "ACTIVO");

            // Validar datos
            if (!newUser.isValide(newUser)) {
                throw new MissingDataException("No se han proporcionado todos los datos requeridos.");
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
            e.printStackTrace();
            return false;
        }

    }

    private int getIdRol(String rol) {
        RolControl rolControl = new RolControl();

        return rolControl.getIdRol(rol);
    }

    private String getFormField(HttpServletRequest request, String fieldName) throws IOException, ServletException {
        Part part = request.getPart(fieldName);
        if (part != null) {
            return new String(part.getInputStream().readAllBytes(), StandardCharsets.UTF_8).trim();
        }
        return null;
    }

}
