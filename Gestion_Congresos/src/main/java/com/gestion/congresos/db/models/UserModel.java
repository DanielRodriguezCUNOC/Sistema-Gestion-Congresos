package com.gestion.congresos.db.models;

public class UserModel {

    private int idUser;
    private int idRol;
    private String name;
    private String user;
    private String password;
    private String email;
    private String ID;
    private String phone;
    private byte[] photo;
    private String organization;
    private String state;

    public UserModel() {
    }

    public UserModel(int idRol, String name, String user, String password, String email, String ID,
            String phone, byte[] photo, String organization, String state) {
        this.idRol = idRol;
        this.name = name;
        this.user = user;
        this.password = password;
        this.email = email;
        this.ID = ID;
        this.phone = phone;
        this.photo = photo;
        this.organization = organization;
        this.state = state;
    }

    // Getters y Setters

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isValide(UserModel user) {
        if (user.getIdRol() <= 0 || user.getName().isBlank() || user.getUser().isBlank() || user.getPassword().isBlank()
                || user.getEmail().isBlank() || user.getID().isBlank() || user.getPhone().isBlank()
                || user.getOrganization().isBlank() || user.getState().isBlank()) {
            return false;
        }
        return true;

    }
}
