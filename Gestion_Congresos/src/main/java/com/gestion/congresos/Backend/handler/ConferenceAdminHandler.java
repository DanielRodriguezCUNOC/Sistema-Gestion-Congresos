package com.gestion.congresos.Backend.handler;

import java.util.List;

import com.gestion.congresos.Backend.db.controls.admin.ControlConferenceAdmin;
import com.gestion.congresos.Backend.db.controls.user.UserControl;
import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

public class ConferenceAdminHandler {

    private HttpServletRequest request;

    public ConferenceAdminHandler(HttpServletRequest request) {
        this.request = request;

    }

    public ConferenceAdminHandler() {

    }

    public UserModel getConferenceAdminById() throws DataBaseException, UserNotFoundException {

        // * Obtenemos el id del usuario loggeado de la sesion */
        Object idUserObj = request.getSession().getAttribute("idUser");

        if (idUserObj == null) {
            return null;
        }

        int idUser = (int) idUserObj;

        UserControl userControl = new UserControl();

        UserModel conferenceAdmin = userControl.getUserById(idUser);

        if (conferenceAdmin == null || conferenceAdmin.getIdRol() != 2) {
            throw new UserNotFoundException("El administrador de conferencia con ID " + idUser + " no existe.");
        }
        return conferenceAdmin;
    }

    public List<String[]> getAllGuestsSpeakers() throws DataBaseException {
        ControlConferenceAdmin controlConferenceAdmin = new ControlConferenceAdmin();
        return controlConferenceAdmin.getAllGuestsSpeakers();
    }

    public List<String[]> getAllCongresses() throws DataBaseException {
        ControlConferenceAdmin controlConferenceAdmin = new ControlConferenceAdmin();
        return controlConferenceAdmin.getAllCongress();
    }

    public List<String[]> getAllActivities() throws DataBaseException {
        ControlConferenceAdmin controlConferenceAdmin = new ControlConferenceAdmin();
        return controlConferenceAdmin.getAllActivities();
    }

    public boolean convertStringToBoolean(String value) {
        return value != null && (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("si"));
    }

}
