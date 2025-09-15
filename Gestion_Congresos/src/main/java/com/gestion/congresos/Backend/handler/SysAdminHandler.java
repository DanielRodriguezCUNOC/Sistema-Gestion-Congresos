package com.gestion.congresos.Backend.handler;

import com.gestion.congresos.Backend.db.controls.user.UserControl;
import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

public class SysAdminHandler {

    public UserModel getSysAdmin(HttpServletRequest request) throws DataBaseException, UserNotFoundException {

        UserControl userControl = new UserControl();

        // * Obtenemos el id del usuario loggeado de la sesion */
        Object idUserObj = request.getSession().getAttribute("idUser");

        if (idUserObj == null) {
            return null;
        }
        int idUser = (int) idUserObj;

        return userControl.getUserById(idUser);

    }
}
