package com.gestion.congresos.Backend.validations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

public class ValidatorData {

    public boolean isValidName(String name) {
        if (name == null || name.length() > 255)
            return false;
        RegexValidator nameValidator = new RegexValidator("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]{2,255}$");
        return nameValidator.isValid(name);
    }

    public boolean isValidUsername(String user) {
        if (user == null || user.length() > 100)
            return false;
        RegexValidator userValidator = new RegexValidator("^[a-zA-Z0-9_]{4,100}$");
        return userValidator.isValid(user);
    }

    public boolean isValidPassword(String password) {
        if (password == null || password.length() > 100)
            return false;
        RegexValidator passValidator = new RegexValidator("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,100}$");
        return passValidator.isValid(password);
    }

    public boolean isValidEmail(String email) {
        if (email == null || email.length() > 100)
            return false;
        return EmailValidator.getInstance().isValid(email);
    }

    public boolean isValidID(String id) {
        if (id == null || id.length() > 15)
            return false;
        RegexValidator idValidator = new RegexValidator("^\\d{6,15}$"); // Ajustable según tu formato
        return idValidator.isValid(id);
    }

    public boolean isValidPhone(String phone) {
        if (phone == null || phone.length() > 25)
            return false;
        RegexValidator phoneValidator = new RegexValidator("^\\+?\\d{8,25}$");
        return phoneValidator.isValid(phone);
    }

    public boolean isValidOrganization(String org) {
        if (org == null || org.length() > 150)
            return false;
        RegexValidator orgValidator = new RegexValidator("^[A-Za-zÁÉÍÓÚáéíóúñÑ0-9 ]{2,150}$");
        return orgValidator.isValid(org);
    }

    public boolean isValidState(String estado) {
        return "ACTIVO".equals(estado) || "INACTIVO".equals(estado);
    }

    public boolean isValidTypeUser(String typeUser) {
        return "Administrador Sistema".equals(typeUser) || "Administrador Congreso".equals(typeUser)
                || "Comite Cientifico".equals(typeUser)
                || "Participante".equals(typeUser) || "Ponente Invitado".equals(typeUser);
    }

    public boolean isValidTypeActivity(String tipoActividad) {
        return "Taller".equals(tipoActividad) || "Ponencia".equals(tipoActividad);
    }

    public boolean isValidPercentage(Double porcentaje) {
        if (porcentaje == null)
            return false;
        return porcentaje >= 0.0 && porcentaje <= 100.0;
    }

    public boolean isValidQuantity(BigDecimal cantidad) {
        if (cantidad == null)
            return false;
        return cantidad.compareTo(BigDecimal.ZERO) > 0 && cantidad.compareTo(new BigDecimal("99999999.99")) <= 0;
    }

    public boolean isValidDate(String fecha) {
        if (fecha == null)
            return false;
        try {
            LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean isValidTime(String hora) {
        if (hora == null)
            return false;
        try {
            LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm:ss"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
