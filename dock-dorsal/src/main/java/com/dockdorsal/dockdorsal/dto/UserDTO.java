package com.dockdorsal.dockdorsal.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserDTO {
    private String nom;
    private String prénom;
    private String email;
    private String password;
    private String roles;
    // Getters
    public String getNom() {
        return nom;
    }

    public String getPrénom() {
        return prénom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

}

