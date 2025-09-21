package com.gestion.congresos.Backend.handler;

import java.sql.Date;

import com.gestion.congresos.Backend.db.controls.congress.ControlCongress;
import com.gestion.congresos.Backend.db.controls.congress.ControlCongressCRUD;
import com.gestion.congresos.Backend.db.models.CongressModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;
import com.gestion.congresos.Backend.validations.ValidatorData;

import jakarta.servlet.http.HttpServletRequest;

public class CongressCRUDHandler {

    private HttpServletRequest request;
    private ValidatorData validator;

    public CongressCRUDHandler(HttpServletRequest request) {
        this.request = request;
        this.validator = new ValidatorData();
    }

    public CongressCRUDHandler() {
        this.validator = new ValidatorData();
    }

    public CongressModel getCongressById() throws DataBaseException, ObjectNotFoundException {

        int idToEdit = Integer.parseInt(request.getParameter("idCongress"));

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

            if (!isValidString(nombre) || !isValidString(descripcion)) {
                return false;
            }

            if (!validator.isValidDate(fechaInicio) || !validator.isValidDate(fechaFin)) {
                return false;
            }

            Date sqlFechaInicio = Date.valueOf(fechaInicio);
            Date sqlFechaFin = Date.valueOf(fechaFin);

            ControlCongressCRUD control = new ControlCongressCRUD();

            return control.editCongress(idCongress, nombre, descripcion, sqlFechaInicio, sqlFechaFin, costo,
                    aceptaConvocatoria);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error al actualizar el congreso en la base de datos.");
        }
    }

    private boolean isValidString(String value) {
        if (value == null) {
            return false;
        }
        String trimmed = value.trim();
        if (trimmed.isBlank()) {
            return false;
        }
        return validator.isValidString(trimmed);
    }
}
