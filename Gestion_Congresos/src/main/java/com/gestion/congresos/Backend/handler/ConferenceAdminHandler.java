package com.gestion.congresos.Backend.handler;

import java.sql.SQLException;
import java.util.List;

import com.gestion.congresos.Backend.db.controls.admin.ControlConferenceAdmin;
import com.gestion.congresos.Backend.db.controls.user.UserControl;
import com.gestion.congresos.Backend.db.models.ActivityModel;
import com.gestion.congresos.Backend.db.models.CongressModel;
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

        int idUser = Integer.parseInt(request.getParameter("idUser"));

        UserControl userControl = new UserControl();
        UserModel conferenceAdmin = userControl.getUserById(idUser);

        if (conferenceAdmin == null || conferenceAdmin.getIdRol() != 3) {
            throw new UserNotFoundException("El administrador de conferencia con ID " + idUser + " no existe.");
        }
        return conferenceAdmin;
    }

    public List<String[]> getAllGuestsSpeakers() throws DataBaseException {
        ControlConferenceAdmin controlConferenceAdmin = new ControlConferenceAdmin();
        try {
            return controlConferenceAdmin.getAllGuestsSpeakers();
        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener los ponentes invitados de la base de datos", e);
        }
    }

    public List<CongressModel> getAllCongresses() throws DataBaseException {
        ControlConferenceAdmin controlConferenceAdmin = new ControlConferenceAdmin();
        return controlConferenceAdmin.getAllCongress();
    }

    public List<ActivityModel> getAllActivities() throws DataBaseException {
        ControlConferenceAdmin controlConferenceAdmin = new ControlConferenceAdmin();
        return controlConferenceAdmin.getAllActivities();
    }

    public boolean convertStringToBoolean(String value) {
        return value != null && (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("si"));
    }

}
