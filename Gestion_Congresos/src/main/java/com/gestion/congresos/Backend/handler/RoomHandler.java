package com.gestion.congresos.Backend.handler;

import java.sql.SQLException;
import java.util.List;

import com.gestion.congresos.Backend.db.controls.admin.ControlConferenceAdmin;
import com.gestion.congresos.Backend.db.controls.room.ControlRoom;
import com.gestion.congresos.Backend.db.models.RoomModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.ObjectAlreadyExists;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;
import com.gestion.congresos.Backend.validations.ValidatorData;

import jakarta.servlet.http.HttpServletRequest;

public class RoomHandler {

    private HttpServletRequest request;

    private ValidatorData validatorData;

    public RoomHandler(HttpServletRequest request) {
        this.request = request;
        this.validatorData = new ValidatorData();
    }

    public RoomHandler() {
        this.validatorData = new ValidatorData();
    }

    public boolean createRoom()
            throws MissingDataException, DataBaseException, ObjectNotFoundException, ObjectAlreadyExists {

        String nameSalon = request.getParameter("nameSalon");
        String nameInstitution = request.getParameter("nameInstitution");
        String addressSalon = request.getParameter("addressSalon");
        String capacitySalonStr = request.getParameter("capacitySalon");

        if (!validatorData.isValidName(nameSalon)) {
            throw new MissingDataException("El nombre del salon no es valido");
        } else if (!validatorData.isValidName(nameInstitution)) {
            throw new MissingDataException("El nombre de la institucion no es valido");
        } else if (!validatorData.isValidName(addressSalon)) {
            throw new MissingDataException("La direccion del salon no es valida");
        }

        int capacitySalon;
        try {
            capacitySalon = Integer.parseInt(capacitySalonStr);
            if (capacitySalon <= 0) {
                throw new MissingDataException("La capacidad del salon no es valida");
            }
        } catch (NumberFormatException e) {
            throw new MissingDataException("La capacidad del salon no es valida");
        }

        ControlRoom controlRoom = new ControlRoom();

        ControlConferenceAdmin controlConferenceAdmin = new ControlConferenceAdmin();

        try {
            int idInstitution = controlConferenceAdmin.getIdInstitutionByName(nameInstitution);

            if (idInstitution < 0) {
                throw new ObjectNotFoundException("La institucion no existe en la base de datos");
            }

            RoomModel roomModel = new RoomModel(idInstitution, nameSalon, addressSalon, capacitySalon);

            if (!roomModel.isValid()) {
                throw new MissingDataException("No se han proporcionado todos los datos requeridos para el salon.");

            }
            if (controlRoom.existsRoom(nameSalon, idInstitution, addressSalon)) {
                throw new ObjectAlreadyExists("El salon ya existe en la base de datos");
            }
            return controlRoom.insertRoom(roomModel);

        } catch (SQLException e) {
            throw new DataBaseException("Error al insertar el salon en la base de datos", e);
        }
    }

    public List<String[]> getAllRooms() throws DataBaseException {
        ControlRoom controlRoom = new ControlRoom();
        try {
            return controlRoom.getAllRooms();
        } catch (SQLException e) {
            throw new DataBaseException("Error al obtener los salones de la base de datos", e);
        }
    }

}
