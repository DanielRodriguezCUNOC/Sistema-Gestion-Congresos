package com.gestion.congresos.Backend.handler.admin_congress;

import java.time.LocalDate;
import java.time.LocalTime;

import com.gestion.congresos.Backend.db.controls.activity.ControlActivityCRUD;
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
        String param = request.getParameter("idActivity");
        if (param == null || param.isBlank()) {
            throw new IllegalArgumentException("El parámetro idActivity es obligatorio");
        }

        int idToEdit;
        try {
            idToEdit = Integer.parseInt(param);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El parámetro idActivity debe ser un número válido", e);
        }

        ControlActivityCRUD control = new ControlActivityCRUD();
        return control.getActivityById(idToEdit);

    }

    public boolean editActivity() throws DataBaseException, MissingDataException {

        // Debug: imprimir parámetros recibidos
        System.out.println("[DEBUG ActivityCRUDHandler] Iniciando editActivity");
        System.out.println("[DEBUG ActivityCRUDHandler] Parámetros recibidos:");
        request.getParameterMap().forEach((k, v) -> System.out.println("  " + k + "=" + java.util.Arrays.toString(v)));

        int idActivity = Integer.parseInt(request.getParameter("idActivity"));
        int idTipoActividad = Integer.parseInt(request.getParameter("idTipoActividad"));
        int idSalon = Integer.parseInt(request.getParameter("idSalon"));
        int idCongreso = Integer.parseInt(request.getParameter("idCongreso"));

        String nombreActividad = request.getParameter("nombre_actividad");
        String fecha = request.getParameter("fecha");
        String horaInicio = request.getParameter("hora_inicio");
        String horaFin = request.getParameter("hora_fin");
        String descripcion = request.getParameter("descripcion");

        Integer cupoTaller = null;
        String cupoTallerParam = request.getParameter("cupo_taller");
        if (cupoTallerParam != null && !cupoTallerParam.isBlank() && idTipoActividad == 2) {
            try {
                cupoTaller = Integer.parseInt(cupoTallerParam);
            } catch (NumberFormatException e) {
                System.out.println("[DEBUG ActivityCRUDHandler] cupo_taller inválido: " + cupoTallerParam);
                throw new MissingDataException("Cupo de taller inválido");
            }
        }

        System.out.println("[DEBUG ActivityCRUDHandler] Valores parseados: idActivity=" + idActivity
                + ", idTipoActividad=" + idTipoActividad + ", idSalon=" + idSalon + ", idCongreso=" + idCongreso);
        System.out.println("[DEBUG ActivityCRUDHandler] nombreActividad='" + nombreActividad + "', fecha='" + fecha
                + "', horaInicio='" + horaInicio + "', horaFin='" + horaFin + "', descripcion='" + descripcion
                + "', cupoTaller='" + cupoTaller + "'");

        if (!isValidString(nombreActividad) || !validator.isValidDate(fecha) ||
                !validator.isValidTime(horaInicio) || !validator.isValidTime(horaFin) || !isValidString(descripcion)) {
            System.out.println("[DEBUG ActivityCRUDHandler] Validación básica falló");
            throw new MissingDataException("Datos inválidos para la actividad");
        }

        ActivityModel activity = new ActivityModel(
                idSalon, idCongreso,
                idTipoActividad,
                nombreActividad,
                LocalDate.parse(fecha),
                LocalTime.parse(horaInicio),
                LocalTime.parse(horaFin),
                descripcion,
                cupoTaller);

        activity.setIdActividad(idActivity);

        if (!activity.isValid()) {
            System.out.println("[DEBUG ActivityCRUDHandler] activity.isValid() == false");
            throw new MissingDataException("Datos inválidos para la actividad");
        }

        ControlActivityCRUD control = new ControlActivityCRUD();
        try {
            return control.updateActivity(activity);
        } catch (Exception e) {
            throw new DataBaseException("Error inesperado al editar la actividad", e);
        }
    }

    public boolean deleteActivity(int idActivity) throws DataBaseException {
        ControlActivityCRUD control = new ControlActivityCRUD();
        return control.deleteActivity(idActivity);
    }

    private boolean isValidString(String value) {
        return value != null && !value.isBlank() && validator.isValidString(value);
    }

}
