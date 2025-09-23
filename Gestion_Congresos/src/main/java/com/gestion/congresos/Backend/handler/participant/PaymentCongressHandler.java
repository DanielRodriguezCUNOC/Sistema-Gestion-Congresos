package com.gestion.congresos.Backend.handler.participant;

import java.util.List;

import com.gestion.congresos.Backend.db.controls.inscription.ControlInscription;
import com.gestion.congresos.Backend.db.controls.payments.ControlPayment;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.utils.GetIdParticipantFromSession;

import jakarta.servlet.http.HttpServletRequest;

public class PaymentCongressHandler {

    private GetIdParticipantFromSession getIdParticipantFromSession;
    private HttpServletRequest request;

    public PaymentCongressHandler(HttpServletRequest request) {
        this.request = request;
        this.getIdParticipantFromSession = new GetIdParticipantFromSession(request);
    }

    public List<String[]> getAvailableCongressesForPayment() {

        int idUser = getIdParticipantFromSession.getIdParticipantFromSession();

        ControlInscription controlInscription = new ControlInscription();

        List<String[]> congresses = controlInscription.getInscriptionsToPayByParticipant(idUser);

        return congresses;

    }

    public boolean processPayment() throws DataBaseException {

        int idCongreso = Integer.parseInt(request.getParameter("idCongress"));

        int idUser = getIdParticipantFromSession.getIdParticipantFromSession();

        ControlPayment controlPayment = new ControlPayment();
        try {
            return controlPayment.insertPayment(idUser, idCongreso);
        } catch (Exception e) {
            return false;
        }
    }

}
