package com.gestion.congresos.Backend.handler;

import com.gestion.congresos.Backend.db.controls.admin_conference.ControlActivityCRUD;
import com.gestion.congresos.Backend.db.controls.admin_conference.ControlCongress;
import com.gestion.congresos.Backend.db.controls.admin_conference.ControlRoom;
import com.gestion.congresos.Backend.db.controls.tipo_actividad.ControlTipoActividad;
import com.gestion.congresos.Backend.db.models.ActivityModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

public class ActivityCRUDHandler {

    private HttpServletRequest request;

    public ActivityCRUDHandler(HttpServletRequest request) {
        this.request = request;
    }

    public ActivityCRUDHandler() {
    }

    public ActivityModel getActivityById() throws DataBaseException, ObjectNotFoundException {
        int idToEdit = Integer.parseInt(request.getParameter("activityId"));

        ControlActivityCRUD control = new ControlActivityCRUD();

        try {
            return control.getGetActivityById(idToEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

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

            ActivityModel activity = new ActivityModel(idSalon, idCongreso, idTipoActividad, nombreActividad,
                    java.time.LocalDate.parse(fecha), java.time.LocalTime.parse(horaInicio),
                    java.time.LocalTime.parse(horaFin), descripcion, cupoTaller);

            activity.setIdActividad(idActivity);

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

}
