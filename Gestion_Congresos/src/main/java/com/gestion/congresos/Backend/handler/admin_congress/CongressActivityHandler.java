package com.gestion.congresos.Backend.handler.admin_congress;

import java.util.List;

import com.gestion.congresos.Backend.db.controls.congress.ControlCongressActivity;
import com.gestion.congresos.Backend.db.controls.room.ControlRoomActivity;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.validations.ValidatorData;

import jakarta.servlet.http.HttpServletRequest;

public class CongressActivityHandler {

    private HttpServletRequest request;
    private ValidatorData validator;

    public CongressActivityHandler(HttpServletRequest request) {
        this.request = request;
        this.validator = new ValidatorData();
    }

    public CongressActivityHandler() {
        this.validator = new ValidatorData();
    }

    public List<String> getNamesCongresses() throws DataBaseException {
        ControlCongressActivity controlCongressActivity = new ControlCongressActivity();
        return controlCongressActivity.getNamesCongresses();
    }

    public List<String> getRoomsByCongressName() throws MissingDataException, DataBaseException {

        ControlRoomActivity controlRoomActivity = new ControlRoomActivity();

        String nameCongress = request.getParameter("congress");

        if (nameCongress == null || nameCongress.isEmpty() || !validator.isValidString(nameCongress)) {
            throw new MissingDataException(nameCongress + " no es un nombre de congreso valido");
        }

        return controlRoomActivity.getRoomsByCongressName(nameCongress);

    }

}
