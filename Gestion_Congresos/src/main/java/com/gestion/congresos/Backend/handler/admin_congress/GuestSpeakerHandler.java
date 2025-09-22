package com.gestion.congresos.Backend.handler.admin_congress;

import com.gestion.congresos.Backend.db.controls.user.UserControl;
import com.gestion.congresos.Backend.db.models.UserModel;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

public class GuestSpeakerHandler {

    private HttpServletRequest request;

    public GuestSpeakerHandler(HttpServletRequest request) {
        this.request = request;
    }

    public GuestSpeakerHandler() {
    }

    public UserModel getGuestSpeaker() {

        UserControl userControl = new UserControl();

        try {
            int idUser = Integer.parseInt(request.getSession().getAttribute("idUser").toString());

            if (idUser <= 0) {
                return null;
            }

            return userControl.getUserById(idUser);

        } catch (NumberFormatException e) {
            return null;
        } catch (DataBaseException | UserNotFoundException e) {
            return null;
        }
    }

}
