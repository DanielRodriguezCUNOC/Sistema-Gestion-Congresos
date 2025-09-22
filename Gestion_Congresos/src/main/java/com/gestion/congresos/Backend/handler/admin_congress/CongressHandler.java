package com.gestion.congresos.Backend.handler.admin_congress;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import com.gestion.congresos.Backend.db.controls.congress.ControlCongress;
import com.gestion.congresos.Backend.db.controls.institution.ControlInstitution;
import com.gestion.congresos.Backend.db.models.CongressModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.ObjectAlreadyExists;
import com.gestion.congresos.Backend.exceptions.ObjectNotFoundException;
import com.gestion.congresos.Backend.validations.ValidatorData;

import jakarta.servlet.http.HttpServletRequest;

public class CongressHandler {

    private HttpServletRequest request;

    private ValidatorData validatorData;

    public CongressHandler(HttpServletRequest request) {
        this.request = request;
        this.validatorData = new ValidatorData();
    }

    public CongressHandler() {
        this.validatorData = new ValidatorData();
    }

    public boolean createCongress()
            throws DataBaseException, MissingDataException, ObjectNotFoundException, ObjectAlreadyExists {

        ControlInstitution controlInstitution = new ControlInstitution();

        ControlCongress controlCongress = new ControlCongress();

        try {
            String nameCongress = request.getParameter("nameCongress");
            String nameInstitution = request.getParameter("nameInstitution");

            String dateInitializing = request.getParameter("dateInitializing");

            String dateEnding = request.getParameter("dateEnding");

            BigDecimal priceCongress = new BigDecimal(request.getParameter("priceCongress"));

            boolean acceptConvocations = convertStringToBoolean(request.getParameter("acceptConvocations"));

            boolean statusCongress = convertStringToBoolean(request.getParameter("statusCongress"));

            int quota = 0;

            try {
                quota = Integer.parseInt(request.getParameter("quota"));

                if (quota < 0 && !validatorData.isValidQuota(quota)) {
                    throw new MissingDataException("El quota no es valido");

                }
            } catch (NumberFormatException e) {
                throw new MissingDataException("El quota no es valido");
            }

            if (!validatorData.isValidName(nameCongress)) {
                throw new MissingDataException("El nombre del congreso no es valido");
            } else if (!validatorData.isValidQuantity(priceCongress)) {
                throw new MissingDataException("El precio del congreso no es valido");

            } else if (!validatorData.isValidDate(dateEnding) && !validatorData.isValidDate(dateInitializing)) {
                throw new MissingDataException("Las fechas del congreso no son validas");
            } else if (!validatorData.isValidName(nameInstitution)) {
                throw new MissingDataException("El nombre de la institucion no es valido");
            }

            int idInstitution = controlInstitution.getIdInstitutionByName(nameInstitution);

            if (idInstitution < 0) {
                throw new ObjectNotFoundException("La institucion no existe en la base de datos");
            }

            Date dateInitializingSQL = Date.valueOf(dateInitializing);
            Date dateEndingSQL = Date.valueOf(dateEnding);

            CongressModel congressModel = new CongressModel(idInstitution, nameCongress, dateInitializingSQL,
                    dateEndingSQL, priceCongress, acceptConvocations, statusCongress, quota);
            if (controlCongress.existsCongressByName(nameCongress)) {
                throw new ObjectAlreadyExists("El congreso ya existe en la base de datos");
            }
            return controlCongress.insertCongress(congressModel);

        } catch (SQLException e) {
            throw new DataBaseException("Error al insertar el congreso en la base de datos", e);
        }

    }

    public boolean convertStringToBoolean(String value) {
        return value != null && (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("on"));
    }

}
