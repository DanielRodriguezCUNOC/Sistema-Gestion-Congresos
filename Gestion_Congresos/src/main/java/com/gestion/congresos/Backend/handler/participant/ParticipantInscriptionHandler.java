package com.gestion.congresos.Backend.handler.participant;

import com.gestion.congresos.Backend.db.controls.participant.ControlParticipantActivity;
import com.gestion.congresos.Backend.db.controls.participant.ControlParticipantInscription;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.exceptions.UserAlreadyExistsException;
import com.gestion.congresos.utils.GetIdParticipantFromSession;

import jakarta.servlet.http.HttpServletRequest;

public class ParticipantInscriptionHandler {

    private HttpServletRequest request;
    private GetIdParticipantFromSession getIdParticipant;

    public ParticipantInscriptionHandler(HttpServletRequest request) {
        this.request = request;
        this.getIdParticipant = new GetIdParticipantFromSession(request);
    }

    public boolean registerParticipantToCongress() throws DataBaseException, UserAlreadyExistsException {
        ControlParticipantInscription control = new ControlParticipantInscription();

        int idCongress = Integer.parseInt(request.getParameter("idCongress"));

        int idUsuario = getIdParticipant.getIdParticipantFromSession();

        if (idUsuario == 0) {
            return false;
        }

        if (control.isParticipantRegisteredInCongress(idUsuario, idCongress)) {
            throw new UserAlreadyExistsException("El participante ya está inscrito en el congreso.");
        }
        return control.registerParticipantToCongress(idUsuario, idCongress);

    }

    public boolean registerParticipantToWorkshop() throws DataBaseException, UserAlreadyExistsException {
        ControlParticipantActivity control = new ControlParticipantActivity();

        int idWorkshop = Integer.parseInt(request.getParameter("idWorkshop"));

        int idUsuario = getIdParticipant.getIdParticipantFromSession();

        if (idUsuario == 0) {
            return false;
        }

        if (control.isAlreadyRegisteredInWorkshop(idUsuario, idWorkshop)) {
            throw new UserAlreadyExistsException("El participante ya está inscrito en la actividad.");
        }
        return control.registerParticipantToWorkshop(idUsuario, idWorkshop);

    }

}
