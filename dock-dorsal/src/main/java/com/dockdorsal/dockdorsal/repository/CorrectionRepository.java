package com.dockdorsal.dockdorsal.repository;


import com.dockdorsal.dockdorsal.dao.CorrectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CorrectionRepository extends JpaRepository<CorrectionEntity, Long> {
    Optional<CorrectionEntity> findByIdAndUser_Email(Long correctionId, String email);


    List<CorrectionEntity> findByUser_Email(String email);

    List<CorrectionEntity> findByUser_EmailAndDateSaisieBetween(String email, LocalDate startDate, LocalDate endDate);

    List<CorrectionEntity> findByDateSaisieBetween(LocalDate startDate, LocalDate endDate);
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
