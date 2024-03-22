package com.dockdorsal.dockdorsal.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "corrections")
public class CorrectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valeur_correction")
    private double valeurCorrection;

    @Column(name = "date_saisie")
    private LocalDate dateSaisie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
