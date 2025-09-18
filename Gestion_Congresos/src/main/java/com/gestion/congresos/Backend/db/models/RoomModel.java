package com.gestion.congresos.Backend.db.models;

public class RoomModel {

    private Integer idSalon;
    private Integer idInstitucion;
    private String nombreSalon;
    private String direccion;
    private Integer capacidad;

    public RoomModel() {
    }

    public RoomModel(Integer idInstitucion, String nombreSalon,
            String direccion, Integer capacidad) {
        this.idInstitucion = idInstitucion;
        this.nombreSalon = nombreSalon;
        this.direccion = direccion;
        this.capacidad = capacidad;
    }

    // ----- Getters y Setters -----
    public Integer getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(Integer idSalon) {
        this.idSalon = idSalon;
    }

    public Integer getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNombreSalon() {
        return nombreSalon;
    }

    public void setNombreSalon(String nombreSalon) {
        this.nombreSalon = nombreSalon;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isValid() {
        return idInstitucion != null && nombreSalon != null && direccion != null && capacidad != null && capacidad > 0;
    }

}
