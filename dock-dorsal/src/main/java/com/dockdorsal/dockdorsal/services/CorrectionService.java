package com.dockdorsal.dockdorsal.services;


import com.dockdorsal.dockdorsal.dto.CorrectionDTO;
import java.time.LocalDate;
import java.util.List;

public interface CorrectionService {
    String getAuthenticatedUserEmail();

    void submitCorrection(double correctionValue);

    void updateCorrection(String email, Long correctionId, double correctionValue);

    void deleteCorrection(String email, Long correctionId);

    List<CorrectionDTO> getUserCorrections(String email);

    List<CorrectionDTO> getUserCorrectionsByDateRange(String email, LocalDate startDate, LocalDate endDate);

    List<CorrectionDTO> getAllCorrections();

    List<CorrectionDTO> getAllCorrectionsByDateRange(LocalDate startDate, LocalDate endDate);

    CorrectionDTO getCorrectionByEmail(String email, Long correctionId);
}
