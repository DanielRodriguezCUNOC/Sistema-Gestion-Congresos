package com.gestion.congresos.Backend.handler.admin_congress;

import com.gestion.congresos.Backend.db.controls.institution.ControlInstitution;
import com.gestion.congresos.Backend.db.controls.room.ControlRoomCRUD;
import com.gestion.congresos.Backend.db.models.RoomModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;
import com.gestion.congresos.Backend.validations.ValidatorData;

import jakarta.servlet.http.HttpServletRequest;

public class RoomCRUDHandler {

    private HttpServletRequest request;
    private ValidatorData validator;

    public RoomCRUDHandler(HttpServletRequest request) {
        this.request = request;
        this.validator = new ValidatorData();
    }

    public RoomCRUDHandler() {
    }

    public boolean editRoom() throws MissingDataException, DataBaseException {

        ControlRoomCRUD controlRoomCRUD = new ControlRoomCRUD();
        ControlInstitution controlInstitution = new ControlInstitution();

        int capacity;
        int idRoom;
        int idInstitution;

        String nameInstitution = request.getParameter("nameInstitution");

        String capacityStr = request.getParameter("capacity");

        String nameRoom = request.getParameter("nameRoom");

        String address = request.getParameter("address");

        if (request.getParameter("idRoom") == null || request.getParameter("idRoom").isBlank()) {
            throw new MissingDataException("ID del salón inválido");
        } else if (!isValidString(address)) {
            throw new MissingDataException("ubicación inválida");
        } else if (!isValidString(nameRoom)) {
            throw new MissingDataException("Nombre del salón inválido");
        } else if (!isValidString(nameInstitution)) {
            throw new MissingDataException("Nombre de la institución inválido");
        } else if (capacityStr == null || capacityStr.isBlank()) {
            throw new MissingDataException("Capacidad inválida");
        }

        try {
            capacity = Integer.parseInt(capacityStr);
            idRoom = Integer.parseInt(request.getParameter("idRoom"));
        } catch (NumberFormatException e) {
            throw new MissingDataException("Datos numéricos inválidos");
        }
        if (capacity <= 0) {
            throw new MissingDataException("La capacidad debe ser un número positivo");
        }

        if (!controlInstitution.existsInstitution(nameInstitution)) {
            throw new MissingDataException("La institución no existe");
        }
        idInstitution = controlInstitution.getIdInstitutionByName(nameInstitution);
        if (idInstitution == -1) {
            throw new DataBaseException("Error al obtener la institución");
        }

        return controlRoomCRUD.editRoom(idRoom, idInstitution, nameRoom, capacity, address);

    }

    public RoomModel getRoomById() throws DataBaseException, MissingDataException, ObjectNotFoundException {

        int idRoom;
        ControlRoomCRUD controlRoomCRUD = new ControlRoomCRUD();
        System.out.println("Getting room by ID " + request.getParameter("idRoom"));

        try {
            if (request.getParameter("idRoom") == null || request.getParameter("idRoom").isBlank()) {

                throw new MissingDataException("ID del salón inválido");
            }
            idRoom = Integer.parseInt(request.getParameter("idRoom"));

        } catch (NumberFormatException e) {
            throw new MissingDataException("ID del salón inválido");
        }

        RoomModel room = controlRoomCRUD.getRoomById(idRoom);

        return room;

    }

    public boolean deleteRoom() throws MissingDataException, DataBaseException {

        ControlRoomCRUD controlRoomCRUD = new ControlRoomCRUD();

        int idRoom;

        if (request.getParameter("idRoom") == null || request.getParameter("idRoom").isBlank()) {
            throw new MissingDataException("ID del salón inválido");
        }

        try {
            idRoom = Integer.parseInt(request.getParameter("idRoom"));
        } catch (NumberFormatException e) {
            throw new MissingDataException("ID del salón inválido");
        }

        return controlRoomCRUD.deleteRoom(idRoom);

    }

    private boolean isValidString(String value) {
        return value != null && !value.isBlank() && validator.isValidString(value);
    }

}
