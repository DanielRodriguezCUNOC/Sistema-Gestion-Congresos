package com.gestion.congresos.Backend.exceptions;

public class DataBaseException extends Exception {

    public DataBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBaseException(String message) {
        super(message);
    }
}
