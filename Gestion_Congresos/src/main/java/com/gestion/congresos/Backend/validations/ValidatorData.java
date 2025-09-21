package com.gestion.congresos.Backend.validations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidatorData {

    public boolean isValidName(String name) {
        if (name == null || name.length() > 255)
            return false;
        return name.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]{2,255}$");
    }

    public boolean isValidUsername(String user) {
        if (user == null || user.length() > 100)
            return false;
        return user.matches("^[a-zA-Z0-9_]{4,100}$");
    }

    public boolean isValidPassword(String password) {
        if (password == null || password.length() > 100)
            return false;
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,100}$");
    }

    public boolean isValidEmail(String email) {
        if (email == null || email.length() > 100)
            return false;

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        return email.matches(emailRegex);
    }

    public boolean isValidID(String id) {
        if (id == null || id.length() > 15)
            return false;
        return id.matches("^\\d{6,15}$");
    }

    public boolean isValidPhone(String phone) {
        if (phone == null || phone.length() > 25)
            return false;
        return phone.matches("^\\+?\\d{8,25}$");
    }

    public boolean isValidOrganization(String org) {
        if (org == null || org.length() > 150)
            return false;
        return org.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ0-9 ]{2,150}$");
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
        return "TALLER".equalsIgnoreCase(tipoActividad) || "PONENCIA".equalsIgnoreCase(tipoActividad);
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
            LocalTime.parse(hora, DateTimeFormatter.ISO_LOCAL_TIME);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean isValidQuota(int cupo) {
        return String.valueOf(cupo).matches("^[1-9]\\d{0,4}$");
    }

    public boolean isValidString(String value) {
        if (value == null) {
            return false;
        }
        value = value.trim();
        if (value.length() < 1 || value.length() > 255) {
            return false;
        }

        return value.matches("^[\\w\\sáéíóúÁÉÍÓÚñÑ0-9.,()\\-]+$");
    }

}
