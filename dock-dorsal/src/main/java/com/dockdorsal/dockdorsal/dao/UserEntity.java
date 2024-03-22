package com.dockdorsal.dockdorsal.dao;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom; // Changement de la variable prénom à prenom

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CorrectionEntity> corrections;

    // Getters
    public String getNom() {
        return nom;
    }

    public String getPrénom() {
        return prenom;
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
        this.prenom = prénom;
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
