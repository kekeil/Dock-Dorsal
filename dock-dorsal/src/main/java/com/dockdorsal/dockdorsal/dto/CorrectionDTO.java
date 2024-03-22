package com.dockdorsal.dockdorsal.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CorrectionDTO {
    private Long id;
    private double valeurCorrection;
    private LocalDate dateSaisie;
}
