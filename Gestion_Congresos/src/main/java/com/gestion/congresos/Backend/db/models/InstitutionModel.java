package com.gestion.congresos.Backend.db.models;

public class InstitutionModel {

    private String name_institution;
    private String address_institution;

    public InstitutionModel(String name_institution, String address_institution) {
        this.name_institution = name_institution;
        this.address_institution = address_institution;
    }

    // * Constructor sin parametros */
    public InstitutionModel() {
    }

    public String getName_institution() {
        return name_institution;
    }

    public void setName_institution(String name_institution) {
        this.name_institution = name_institution;
    }

    public String getAddress_institution() {
        return address_institution;
    }

    public void setAddress_institution(String address_institution) {
        this.address_institution = address_institution;
    }

    public boolean isValid() {
        return name_institution != null && !name_institution.isEmpty() &&
                address_institution != null && !address_institution.isEmpty();
    }

}
