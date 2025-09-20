package com.gestion.congresos.Backend.handler;

import java.time.LocalDate;
import java.time.LocalTime;

import com.gestion.congresos.Backend.db.controls.activity.ControlActivityCRUD;
import com.gestion.congresos.Backend.db.controls.congress.ControlCongress;
import com.gestion.congresos.Backend.db.controls.room.ControlRoom;
import com.gestion.congresos.Backend.db.controls.tipo_actividad.ControlTipoActividad;
import com.gestion.congresos.Backend.db.models.ActivityModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;
import com.gestion.congresos.Backend.validations.ValidatorData;

import jakarta.servlet.http.HttpServletRequest;

public class ActivityCRUDHandler {

    private HttpServletRequest request;
    private ValidatorData validator;

    public ActivityCRUDHandler(HttpServletRequest request) {
        this.request = request;
        this.validator = new ValidatorData();
    }

    public ActivityCRUDHandler() {
        this.validator = new ValidatorData();
    }

    public ActivityModel getActivityById() throws DataBaseException, ObjectNotFoundException {
        String param = request.getParameter("activityId");
        if (param == null || param.isBlank()) {
            throw new IllegalArgumentException("El parámetro activityId es obligatorio");
        }

        int idToEdit;
        try {
            idToEdit = Integer.parseInt(param);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El parámetro activityId debe ser un número válido", e);
        }

        ControlActivityCRUD control = new ControlActivityCRUD();
        return control.getGetActivityById(idToEdit);

    }

    public boolean editActivity(int idActivity) throws DataBaseException, ObjectNotFoundException {

        String nombreSalon = request.getParameter("nombre_salon");

        String nombreCongreso = request.getParameter("nombre_congreso");

        String tipoActividad = request.getParameter("tipo_actividad");

        String nombreActividad = request.getParameter("nombre_actividad");

        String fecha = request.getParameter("fecha");

        String horaInicio = request.getParameter("hora_inicio");

        String horaFin = request.getParameter("hora_fin");

        String descripcion = request.getParameter("descripcion");

        int cupoTaller = Integer.parseInt(request.getParameter("cupo_taller"));

        try {
            int idSalon = getIdRoomByName(nombreSalon);
            int idCongreso = getIdCongressByName(nombreCongreso);
            int idTipoActividad = getIdTipeActivityByName(tipoActividad);

            if (tipoActividad.equalsIgnoreCase("TALLER") && cupoTaller <= 0) {
                throw new MissingDataException("Cupo del taller inválido");
            } else if (!isValidString(descripcion) || !isValidString(nombreActividad)
                    || !isValidString(fecha) || !isValidString(horaInicio) || !isValidString(horaFin)) {
                throw new MissingDataException("Datos inválidos para la actividad");

            }

            ActivityModel activity = new ActivityModel(idSalon, idCongreso, idTipoActividad, nombreActividad,
                    LocalDate.parse(fecha), LocalTime.parse(horaInicio),
                    LocalTime.parse(horaFin), descripcion, cupoTaller);

            activity.setIdActividad(idActivity);

            if (!activity.isValid()) {
                throw new MissingDataException("Datos inválidos para la actividad");
            }

            ControlActivityCRUD control = new ControlActivityCRUD();
            return control.updateActivity(activity);

        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
            throw new DataBaseException("Error al obtener IDs relacionados", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error inesperado al editar la actividad", e);
        }

    }

    public boolean deleteActivity(int idActivity) throws DataBaseException {
        ControlActivityCRUD control = new ControlActivityCRUD();
        return control.deleteActivity(idActivity);
    }

    private int getIdRoomByName(String nombreSalon) throws DataBaseException, ObjectNotFoundException {
        ControlRoom control = new ControlRoom();
        return control.getIdRoomByName(nombreSalon);
    }

    private int getIdCongressByName(String nombreCongreso) throws DataBaseException, ObjectNotFoundException {

        ControlCongress control = new ControlCongress();
        return control.getIdCongressByName(nombreCongreso);
    }

    private int getIdTipeActivityByName(String tipoActividad) throws DataBaseException, ObjectNotFoundException {
        ControlTipoActividad control = new ControlTipoActividad();
        return control.getIdTipeActivityByName(tipoActividad);
    }

    private boolean isValidString(String value) {
        return value == null || value.isBlank() || !validator.isValidString(value);
    }

}
