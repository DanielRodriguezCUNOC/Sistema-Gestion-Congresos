package com.gestion.congresos.Backend.handler;

import com.gestion.congresos.Backend.db.controls.user.UserControl;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

public class UserDashboardHandler {

    HttpServletRequest request;
    UserControl userControl;

    public UserDashboardHandler(HttpServletRequest request) {
        this.request = request;
        userControl = new UserControl();
    }

    public int getUserRolId() throws DataBaseException, UserNotFoundException {
        int idUser = (int) request.getSession().getAttribute("idUser");
        return userControl.getUserRolIdByIdUser(idUser);
    }

}
