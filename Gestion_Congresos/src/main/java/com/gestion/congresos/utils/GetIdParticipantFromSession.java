package com.gestion.congresos.utils;

import jakarta.servlet.http.HttpServletRequest;

public class GetIdParticipantFromSession {

    private HttpServletRequest request;

    public GetIdParticipantFromSession(HttpServletRequest request) {
        this.request = request;
    }

    public int getIdParticipantFromSession() {
        Object idUserObj = request.getSession().getAttribute("idUser");

        if (idUserObj == null) {
            return 0;
        }
        return (int) idUserObj;
    }
}
