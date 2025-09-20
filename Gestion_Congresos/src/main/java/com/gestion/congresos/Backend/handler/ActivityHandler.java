package com.gestion.congresos.Backend.handler;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import com.gestion.congresos.Backend.db.controls.activity.ControlActivity;
import com.gestion.congresos.Backend.db.controls.admin.ControlConferenceAdmin;
import com.gestion.congresos.Backend.db.controls.congress.ControlCongress;
import com.gestion.congresos.Backend.db.controls.room.ControlRoom;
import com.gestion.congresos.Backend.db.models.ActivityModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.ObjectAlreadyExists;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;
import com.gestion.congresos.Backend.validations.ValidatorData;

import jakarta.servlet.http.HttpServletRequest;

public class ActivityHandler {

    private HttpServletRequest request;

    private ValidatorData validatorData;

    public ActivityHandler(HttpServletRequest request) {
        this.request = request;
        this.validatorData = new ValidatorData();
    }

    public ActivityHandler() {
        this.validatorData = new ValidatorData();
    }

    public boolean createActivity()
            throws DataBaseException, MissingDataException, ObjectAlreadyExists, ObjectNotFoundException {

        ControlConferenceAdmin controlConferenceAdmin = new ControlConferenceAdmin();

        ControlActivity controlActivity = new ControlActivity();

        ControlCongress controlCongress = new ControlCongress();

        ControlRoom controlRoom = new ControlRoom();

        try {
            String nameActivity = request.getParameter("nameActivity");
            String nameCongress = request.getParameter("nameCongress");
            String nameRoom = request.getParameter("nameRoom");
            String typeActivity = request.getParameter("typeActivity");

            String dateActivity = request.getParameter("dateActivity");

            String timeStarting = request.getParameter("timeStarting");

            String timeEnding = request.getParameter("timeEnding");

            String descriptionActivity = request.getParameter("descriptionActivity");

            int cupoTaller = 0;
            try {
                cupoTaller = Integer.parseInt(request.getParameter("cupoTaller"));
                if (!validatorData.isValidQuota(cupoTaller)) {
                    throw new MissingDataException("El cupo del taller no es valido");
                }
            } catch (NumberFormatException e) {
                throw new MissingDataException("El cupo del taller no es valido");
            }

            if (!validatorData.isValidName(nameActivity)) {
                throw new MissingDataException("El nombre de la actividad no es valido");
            } else if (!validatorData.isValidName(nameCongress)) {
                throw new MissingDataException("El nombre del congreso no es valido");
            } else if (!validatorData.isValidName(nameRoom)) {
                throw new MissingDataException("El nombre del salon no es valido");
            } else if (!validatorData.isValidName(typeActivity)) {
                throw new MissingDataException("El tipo de actividad no es valido");
            } else if (!validatorData.isValidDate(dateActivity)) {
                throw new MissingDataException("La fecha de la actividad no es valida");
            } else if (!validatorData.isValidTime(timeStarting) || !validatorData.isValidTime(timeEnding)) {
                throw new MissingDataException("Las horas de la actividad no son validas");
            } else if (!validatorData.isValidString(descriptionActivity)) {
                throw new MissingDataException("La descripcion de la actividad no es valida");
            }

            int idCongress = controlCongress.getIdCongressByName(nameCongress);

            int idRoom = controlRoom.getIdRoomByNameAndCongress(nameRoom, idCongress);

            if (idRoom < 0) {
                throw new ObjectNotFoundException("El salon no existe en la base de datos");
            }

            int idTypeActivity = controlConferenceAdmin.getIdTypeActivityByName(typeActivity);

            if (idTypeActivity < 0) {
                throw new ObjectNotFoundException("El tipo de actividad no existe en la base de datos");
            }

            if (controlActivity.existsActivityByName(nameActivity)) {
                throw new ObjectAlreadyExists("La actividad ya existe en la base de datos");
            }

            LocalDate dateActivitySQL = LocalDate.parse(dateActivity);
            LocalTime timeStartingSQL = LocalTime.parse(timeStarting);
            LocalTime timeEndingSQL = LocalTime.parse(timeEnding);

            ActivityModel activityModel = new ActivityModel(idRoom, idCongress, idTypeActivity, nameActivity,
                    dateActivitySQL, timeStartingSQL, timeEndingSQL, descriptionActivity, cupoTaller);

            return controlActivity.insertActivity(activityModel);

        } catch (SQLException e) {
            throw new DataBaseException("Error al insertar la actividad en la base de datos", e);
        }
    }

}
