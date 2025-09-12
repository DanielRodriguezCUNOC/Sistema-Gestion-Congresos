package com.gestion.congresos.Backend.handler;

import com.gestion.congresos.Backend.db.Encryption;
import com.gestion.congresos.Backend.db.controls.login.ControlLogin;
import com.gestion.congresos.Backend.db.controls.user.UserControl;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;
import com.gestion.congresos.Backend.validations.ValidatorData;

import jakarta.servlet.http.HttpServletRequest;

public class LoginHandler {

    private ValidatorData validator;
    private ControlLogin controlLogin;
    private UserControl userControl;
    private HttpServletRequest request;

    public LoginHandler(HttpServletRequest request) {
        this.request = request;
        this.validator = new ValidatorData();
        this.controlLogin = new ControlLogin();
        this.userControl = new UserControl();
    }

    public boolean autenticateUser() throws MissingDataException, DataBaseException, UserNotFoundException {

        String user = request.getParameter("user");
        String password = request.getParameter("password");

        if (!validator.isValidUsername(user) && !validator.isValidPassword(password)) {
            throw new MissingDataException("Faltan datos obligatorios.");
        }
        return checkInDatabase(user, password);

    }

    private boolean checkInDatabase(String user, String password) throws DataBaseException, UserNotFoundException {

        String email = userControl.getEmailByUsername(user);

        String passwordSalt = Encryption.encryptPassword(password, email);

        return controlLogin.userExist(user, passwordSalt);
    }

    public int getUserId() throws DataBaseException, UserNotFoundException {
        return userControl.getUserIdByUsername(request.getParameter("user"));
    }

}
