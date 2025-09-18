package com.gestion.congresos.Backend.handler;

import java.sql.SQLException;
import java.util.List;

import com.gestion.congresos.Backend.db.controls.admin.ControlConferenceAdmin;
import com.gestion.congresos.Backend.db.models.ActivityModel;
import com.gestion.congresos.Backend.db.models.CongressModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.validations.ValidatorData;

import jakarta.servlet.http.HttpServletRequest;

public class ConferenceAdminHandler {

    private HttpServletRequest request;

    private ValidatorData validatorData;

    public ConferenceAdminHandler(HttpServletRequest request) {
        this.request = request;
        this.validatorData = new ValidatorData();
    }

    public ConferenceAdminHandler() {
        this.validatorData = new ValidatorData();
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
