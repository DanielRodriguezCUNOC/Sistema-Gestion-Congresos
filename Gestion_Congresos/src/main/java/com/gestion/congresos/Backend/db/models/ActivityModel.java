package com.gestion.congresos.Backend.db.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class ActivityModel {

    private Integer idActividad;
    private Integer idSalon;
    private Integer idCongreso;
    private Integer idTipoActividad;
    private String nombreActividad;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String descripcion;
    private Integer cupoTaller;

    public ActivityModel() {
    }

    public ActivityModel(Integer idSalon, Integer idCongreso,
            Integer idTipoActividad, String nombreActividad,
            LocalDate fecha, LocalTime horaInicio,
            LocalTime horaFin, String descripcion, Integer cupoTaller) {

        this.idSalon = idSalon;
        this.idCongreso = idCongreso;
        this.idTipoActividad = idTipoActividad;
        this.nombreActividad = nombreActividad;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.descripcion = descripcion;
        this.cupoTaller = cupoTaller;
    }

    // ----- Getters y Setters -----
    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Integer getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(Integer idSalon) {
        this.idSalon = idSalon;
    }

    public Integer getIdCongreso() {
        return idCongreso;
    }

    public void setIdCongreso(Integer idCongreso) {
        this.idCongreso = idCongreso;
    }

    public Integer getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(Integer idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCupoTaller() {
        return cupoTaller;
    }

    public void setCupoTaller(Integer cupoTaller) {
        this.cupoTaller = cupoTaller;
    }

    public boolean isValid() {
        if (idSalon == null || idCongreso == null || idTipoActividad == null) {
            return false;
        }
        if (nombreActividad == null || nombreActividad.isBlank()) {
            return false;
        }
        if (fecha == null || horaInicio == null || horaFin == null) {
            return false;
        }
        if (horaFin.isBefore(horaInicio)) {
            return false;
        }
        if (descripcion == null || descripcion.isBlank()) {
            return false;
        }
        return true;
    }
}
