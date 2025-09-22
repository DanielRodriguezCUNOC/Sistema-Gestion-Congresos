package com.gestion.congresos.Backend.handler.admin;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.gestion.congresos.Backend.db.controls.admin.ControlSysAdmin;
import com.gestion.congresos.Backend.db.controls.admin.ControlSysAdminCRUD;
import com.gestion.congresos.Backend.db.controls.institution.ControlInstitution;
import com.gestion.congresos.Backend.db.controls.user.UserControl;
import com.gestion.congresos.Backend.db.models.InstitutionModel;
import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.ImageFormatException;
import com.gestion.congresos.Backend.exceptions.InstitutionAlredyExists;
import com.gestion.congresos.Backend.exceptions.MissingDataException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;
import com.gestion.congresos.Backend.validations.ValidatorData;
import com.gestion.congresos.utils.ImageConverter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class SysAdminHandler {

    private HttpServletRequest request;
    private ValidatorData validatorData;

    public SysAdminHandler(HttpServletRequest request) {
        this.request = request;
        this.validatorData = new ValidatorData();
    }

    // * Constructor sin parametros */
    public SysAdminHandler() {
        this.validatorData = new ValidatorData();
    }

    public UserModel getSysAdmin() throws DataBaseException, UserNotFoundException {

        UserControl userControl = new UserControl();

        // * Obtenemos el idAdmin del usuario loggeado de la sesion */
        Object idUserObj = request.getSession().getAttribute("idUser");

        if (idUserObj == null) {
            return null;
        }
        int idUser = (int) idUserObj;

        return userControl.getUserById(idUser);

    }

    public boolean addInstitution() throws DataBaseException, InstitutionAlredyExists {

        String name_institution = request.getParameter("name_institution");
        String address_institution = request.getParameter("address_institution");

        if (!validatorData.isValidOrganization(name_institution)) {
            throw new InstitutionAlredyExists("Nombre de institución inválido: " + name_institution);
        }
        if (!validatorData.isValidString(address_institution)) {
            throw new DataBaseException("Dirección de institución inválida.");
        }

        InstitutionModel institutionModel = new InstitutionModel(name_institution, address_institution);

        if (institutionModel.isValid()) {

            ControlInstitution controlInstitution = new ControlInstitution();

            if (!controlInstitution.existsInstitution(name_institution)) {
                return controlInstitution.insertInstitution(institutionModel);
            } else {
                throw new InstitutionAlredyExists(name_institution);
            }
        }
        return false;

    }

    public List<InstitutionModel> getAllInstitutions() throws DataBaseException {
        ControlSysAdmin controlSysAdmin = new ControlSysAdmin();
        return controlSysAdmin.getAllInstitutions();
    }

    public List<String[]> getAllAdmins() throws DataBaseException {
        ControlSysAdmin controlSysAdmin = new ControlSysAdmin();
        return controlSysAdmin.getAllAdmins();
    }

    public boolean deactivateAdmin(int targetUserId) throws DataBaseException {

        ControlSysAdminCRUD control = new ControlSysAdminCRUD();
        return control.deactivateUser(targetUserId);
    }

    public boolean activateAdmin(int targetUserId) throws DataBaseException {

        ControlSysAdminCRUD control = new ControlSysAdminCRUD();
        return control.activateUser(targetUserId);
    }

    public boolean editAdmin(int targetUserId)
            throws DataBaseException, UserNotFoundException, IOException, ServletException, ImageFormatException,
            MissingDataException {

        String user = getFormField(request, "user");
        String phone = getFormField(request, "phone");
        String organization = getFormField(request, "organization");
        Part photoPart = request.getPart("photo");
        byte[] photo = ImageConverter.convertImage(photoPart);

        if (!validatorData.isValidUsername(user)) {
            throw new MissingDataException("El nombre de usuario no es válido.");
        } else if (!validatorData.isValidPhone(phone)) {
            throw new MissingDataException("El número de teléfono no es válido.");
        } else if (!validatorData.isValidOrganization(organization)) {
            throw new MissingDataException("Los datos de la organización no son válidos.");
        }

        ControlSysAdminCRUD control = new ControlSysAdminCRUD();

        boolean result = control.updateAdmin(targetUserId, user, phone, organization, photo);
        if (!result) {
            throw new UserNotFoundException("No se encontró el usuario con ID: " + targetUserId);
        }
        return result;
    }

    public UserModel getAdminById() throws DataBaseException, UserNotFoundException {
        int idToEdit = Integer.parseInt(request.getParameter("idAdmin"));
        UserControl userControl = new UserControl();
        return userControl.getUserById(idToEdit);
    }

    private String getFormField(HttpServletRequest request, String fieldName) throws IOException, ServletException {
        Part part = request.getPart(fieldName);
        if (part != null) {
            return new String(part.getInputStream().readAllBytes(), StandardCharsets.UTF_8).trim();
        }
        return null;
    }

}
