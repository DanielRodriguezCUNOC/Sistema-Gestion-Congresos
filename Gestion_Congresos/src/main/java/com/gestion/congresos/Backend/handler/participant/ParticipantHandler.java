package com.gestion.congresos.Backend.handler.participant;

import java.util.List;

import com.gestion.congresos.Backend.db.controls.user.UserControl;
import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

public class ParticipantHandler {

    public UserModel getParticipant(HttpServletRequest request) throws DataBaseException, UserNotFoundException {

        UserControl userControl = new UserControl();

        // * Obtenemos el id del usuario loggeado de la sesion */
        Object idUserObj = request.getSession().getAttribute("idUser");

        if (idUserObj == null) {
            return null;
        }
        int idUser = (int) idUserObj;

        return userControl.getUserById(idUser);

    }

    public boolean isParticipant(HttpServletRequest request) {
        Object idRolObj = request.getSession().getAttribute("idRol");

        if (idRolObj == null) {
            return false;
        }

        String idRolStr = idRolObj.toString();
        return "1".equals(idRolStr) || "2".equals(idRolStr) || "3".equals(idRolStr) || "4".equals(idRolStr)
                || "5".equals(idRolStr);
    }

    public List<String[]> getAllActiveCongresses() {

    }
}
