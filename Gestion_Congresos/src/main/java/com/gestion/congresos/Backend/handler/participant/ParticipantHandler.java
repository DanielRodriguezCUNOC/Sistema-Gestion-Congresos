package com.gestion.congresos.Backend.handler.participant;

import java.util.List;

import com.gestion.congresos.Backend.db.controls.participant.ControlParticipantCongress;
import com.gestion.congresos.Backend.db.controls.user.UserControl;
import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

public class ParticipantHandler {

    private HttpServletRequest request;

    public ParticipantHandler(HttpServletRequest request) {
        this.request = request;
    }

    public UserModel getParticipant() throws DataBaseException, UserNotFoundException {

        UserControl userControl = new UserControl();

        int idUser = getIdUserFromSession();

        return userControl.getUserById(idUser);

    }

    public boolean isParticipant() {
        Object idRolObj = request.getSession().getAttribute("idRol");

        if (idRolObj == null) {
            return false;
        }

        String idRolStr = idRolObj.toString();
        return "1".equals(idRolStr) || "2".equals(idRolStr) || "3".equals(idRolStr) || "4".equals(idRolStr)
                || "5".equals(idRolStr);
    }

    public List<String[]> getAllActiveCongresses() throws DataBaseException {
        ControlParticipantCongress control = new ControlParticipantCongress();
        return control.getAllActiveCongresses();
    }

    private int getIdUserFromSession() throws DataBaseException, UserNotFoundException {
        Object idUserObj = request.getSession().getAttribute("idUser");

        if (idUserObj == null) {
            throw new DataBaseException("No se encontró el ID de usuario en la sesión.");
        }

        try {
            return (int) idUserObj;
        } catch (ClassCastException e) {
            throw new DataBaseException("El ID de usuario en la sesión no es válido.");
        }
    }
}
