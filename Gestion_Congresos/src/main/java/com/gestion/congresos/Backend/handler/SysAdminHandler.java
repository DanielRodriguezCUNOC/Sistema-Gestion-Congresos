package com.gestion.congresos.Backend.handler;

import java.util.List;

import com.gestion.congresos.Backend.db.controls.admin.ControlSysAdmin;
import com.gestion.congresos.Backend.db.controls.user.UserControl;
import com.gestion.congresos.Backend.db.models.InstitutionModel;
import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.InstitutionAlredyExists;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

public class SysAdminHandler {

    private HttpServletRequest request;

    public SysAdminHandler(HttpServletRequest request) {
        this.request = request;
    }

    // * Constructor sin parametros */
    public SysAdminHandler() {
    }

    public UserModel getSysAdmin() throws DataBaseException, UserNotFoundException {

        UserControl userControl = new UserControl();

        // * Obtenemos el id del usuario loggeado de la sesion */
        Object idUserObj = request.getSession().getAttribute("idUser");

        if (idUserObj == null) {
            return null;
        }
        int idUser = (int) idUserObj;

        return userControl.getUserById(idUser);

    }

    public List<String[]> getAllConferenceAdmins() throws DataBaseException {
        ControlSysAdmin controlSysAdmin = new ControlSysAdmin();
        return controlSysAdmin.getAllConferenceAdmins();
    }

    public boolean addInstitution() throws DataBaseException, InstitutionAlredyExists {

        String name_institution = request.getParameter("name_institution");
        String address_institution = request.getParameter("address_institution");

        InstitutionModel institutionModel = new InstitutionModel(name_institution, address_institution);

        if (institutionModel.isValid()) {
            ControlSysAdmin controlSysAdmin = new ControlSysAdmin();

            if (!controlSysAdmin.existsInstitution(name_institution)) {
                return controlSysAdmin.insertInstitution(institutionModel);
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

        ControlSysAdmin control = new ControlSysAdmin();
        return control.deactivateUser(targetUserId);
    }

    public boolean activateAdmin(int targetUserId) throws DataBaseException {

        ControlSysAdmin control = new ControlSysAdmin();
        return control.activateUser(targetUserId);
    }

    public boolean editAdmin(int targetUserId, String name, String user, String phone,
            String organization) throws DataBaseException {
        ControlSysAdmin control = new ControlSysAdmin();
        System.out.println("Actualizando admin: " + targetUserId);
        return control.updateAdmin(targetUserId, name, user, phone, organization);
    }

    public UserModel getAdminById(int id) throws DataBaseException, UserNotFoundException {
        UserControl userControl = new UserControl();
        return userControl.getUserById(id);
    }

}
