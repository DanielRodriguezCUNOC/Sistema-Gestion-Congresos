package com.gestion.congresos.Backend.handler.participant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gestion.congresos.Backend.db.controls.participant.ControlParticipantActivity;
import com.gestion.congresos.Backend.db.controls.participant.ControlParticipantInscription;
import com.gestion.congresos.Backend.db.controls.payments.ControlPaymentCongress;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.utils.GetIdParticipantFromSession;

import jakarta.servlet.http.HttpServletRequest;

public class ParticipantActivityHandler {

    private GetIdParticipantFromSession getIdParticipant;

    public ParticipantActivityHandler(HttpServletRequest request) {
        this.getIdParticipant = new GetIdParticipantFromSession(request);
    }

    public List<String[]> getWorkshopsByCongress() throws DataBaseException {

        ControlPaymentCongress controlPaymentCongress = new ControlPaymentCongress();
        ControlParticipantActivity controlParticipantActivity = new ControlParticipantActivity();
        ControlParticipantInscription controlParticipantInscription = new ControlParticipantInscription();

        int idUser = getIdParticipant.getIdParticipantFromSession();

        List<String[]> availableWorkshops = new ArrayList<>();

        List<Integer> congressIds = controlPaymentCongress.getCongressIdsPaidByUser(idUser);

        Set<Integer> registeredWorkshops = new HashSet<>(
                controlParticipantInscription.getRegisteredWorkshopsByParticipant(idUser));

        for (Integer idCongress : congressIds) {

            List<String[]> workshops = controlParticipantActivity.getWorkshopsByInscritCongress(idUser, idCongress);

            for (String[] workshop : workshops) {
                int idActivity = Integer.parseInt(workshop[0]);

                if (!registeredWorkshops.contains(idActivity)) {
                    availableWorkshops.add(workshop);
                }
            }
        }

        return availableWorkshops;
    }

}
