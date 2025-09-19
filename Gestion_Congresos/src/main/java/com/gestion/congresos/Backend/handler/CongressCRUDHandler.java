package com.gestion.congresos.Backend.handler;

import com.gestion.congresos.Backend.db.controls.admin_conference.ControlCongress;
import com.gestion.congresos.Backend.db.controls.admin_conference.ControlCongressCRUD;
import com.gestion.congresos.Backend.db.models.CongressModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

public class CongressCRUDHandler {

    private HttpServletRequest request;

    public CongressCRUDHandler(HttpServletRequest request) {
        this.request = request;
    }

    public CongressCRUDHandler() {
    }

    public CongressModel getCongressById() throws DataBaseException, ObjectNotFoundException {

        int idToEdit = Integer.parseInt(request.getParameter("congressId"));

        ControlCongress control = new ControlCongress();

        try {
            return control.getCongressById(idToEdit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean editCongress(int idCongress) throws DataBaseException {

        String nombre = request.getParameter("nombre");

        String descripcion = request.getParameter("descripcion");

        String fechaInicio = request.getParameter("fecha_inicio");

        String fechaFin = request.getParameter("fecha_fin");

        String costo = request.getParameter("costo");

        boolean aceptaConvocatoria = Boolean.parseBoolean(request.getParameter("acepta_convocatoria"));

        try {

            ControlCongressCRUD control = new ControlCongressCRUD();

            return control.editCongress(idCongress, nombre, descripcion, fechaInicio, fechaFin, costo,
                    aceptaConvocatoria);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error al actualizar el congreso en la base de datos.");
        }
    }
}
