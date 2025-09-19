package com.gestion.congresos.Backend.db.models;

import java.math.BigDecimal;
import java.sql.Date;

public class CongressModel {

    private Integer idCongreso;

    private Integer idInstitucion;
    private String nombreCongreso;
    private Date fechaInicio;
    private Date fechaFin;

    private BigDecimal precio;

    private boolean aceptaConvocatoria = true;

    private boolean estado = true;

    private int cupo;

    // ----- Constructores -----
    public CongressModel() {
    }

    public CongressModel(Integer idInstitucion, String nombreCongreso,
            Date fechaInicio, Date fechaFin,
            BigDecimal precio, boolean aceptaConvocatoria, boolean estado, Integer cupo) {
        this.idInstitucion = idInstitucion;
        this.nombreCongreso = nombreCongreso;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.aceptaConvocatoria = aceptaConvocatoria;
        this.estado = estado;
        this.cupo = cupo;
    }

    // ----- Getters y Setters -----
    public Integer getIdCongreso() {
        return idCongreso;
    }

    public void setIdCongreso(Integer idCongreso) {
        this.idCongreso = idCongreso;
    }

    public Integer getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNombreCongreso() {
        return nombreCongreso;
    }

    public void setNombreCongreso(String nombreCongreso) {
        this.nombreCongreso = nombreCongreso;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Boolean getAceptaConvocatoria() {
        return aceptaConvocatoria;
    }

    public void setAceptaConvocatoria(Boolean aceptaConvocatoria) {
        this.aceptaConvocatoria = aceptaConvocatoria;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public boolean isValid() {
        if (nombreCongreso == null || nombreCongreso.isBlank()) {
            return false;
        }
        if (idInstitucion == null || idInstitucion <= 0) {
            return false;

        }
        if (fechaInicio == null || fechaFin == null) {
            return false;
        }
        if (precio == null || precio.compareTo(BigDecimal.ZERO) < 35) {
            return false;
        }

        if (fechaInicio.after(fechaFin)) {
            return false;
        }
        return true;
    }
}
