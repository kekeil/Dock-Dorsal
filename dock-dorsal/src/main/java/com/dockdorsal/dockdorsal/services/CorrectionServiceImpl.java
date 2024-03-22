package com.dockdorsal.dockdorsal.services;

import com.dockdorsal.dockdorsal.dao.CorrectionEntity;
import com.dockdorsal.dockdorsal.dao.UserEntity;
import com.dockdorsal.dockdorsal.dto.CorrectionDTO;
import com.dockdorsal.dockdorsal.repository.CorrectionRepository;
import com.dockdorsal.dockdorsal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
@Service
public class CorrectionServiceImpl implements CorrectionService {

    private final CorrectionRepository correctionRepository;
    private final UserRepository userRepository;

    public CorrectionServiceImpl(CorrectionRepository correctionRepository, UserRepository userRepository) {
        this.correctionRepository = correctionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String getAuthenticatedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName(); // Dans de nombreux cas, le nom d'utilisateur est l'e-mail
        }
        return null; // Ou lancez une exception si l'utilisateur n'est pas authentifié
    }

    @Override
    public void submitCorrection(double correctionValue) {
        String email = getAuthenticatedUserEmail();
        if (email != null) {
            Optional<UserEntity> userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                UserEntity user = userOptional.get();
                CorrectionEntity correctionEntity = new CorrectionEntity();
                correctionEntity.setValeurCorrection(correctionValue);
                correctionEntity.setDateSaisie(LocalDate.now());
                correctionEntity.setUser(user);
                correctionRepository.save(correctionEntity);
            } else {
                throw new IllegalArgumentException("User not found with email: " + email);
            }
        } else {
            throw new IllegalStateException("User not authenticated.");
        }
    }



    @Override
    public void updateCorrection(String email, Long correctionId, double correctionValue) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            Optional<CorrectionEntity> correctionOptional = correctionRepository.findByIdAndUser_Email(correctionId, email);
            if (correctionOptional.isPresent()) {
                CorrectionEntity correctionEntity = correctionOptional.get();
                correctionEntity.setValeurCorrection(correctionValue);
                correctionRepository.save(correctionEntity);
            } else {
                throw new IllegalArgumentException("Correction not found for user with email: " + email + " and correction ID: " + correctionId);
            }
        } else {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
    }


    @Override
    public void deleteCorrection(String email, Long correctionId) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            Optional<CorrectionEntity> correctionOptional = correctionRepository.findByIdAndUser_Email(correctionId, email);
            if (correctionOptional.isPresent()) {
                correctionRepository.delete(correctionOptional.get());
            } else {
                throw new IllegalArgumentException("Correction not found for user with email: " + email + " and correction ID: " + correctionId);
            }
        } else {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
    }

    @Override
    public List<CorrectionDTO> getUserCorrections(String email) {
        List<CorrectionEntity> correctionEntities = correctionRepository.findByUser_Email(email);
        return correctionEntities.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CorrectionDTO> getUserCorrectionsByDateRange(String email, LocalDate startDate, LocalDate endDate) {
        List<CorrectionEntity> correctionEntities = correctionRepository.findByUser_EmailAndDateSaisieBetween(email, startDate, endDate);
        return correctionEntities.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CorrectionDTO> getAllCorrections() {
        List<CorrectionEntity> correctionEntities = correctionRepository.findAll();
        return correctionEntities.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<CorrectionDTO> getAllCorrectionsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<CorrectionEntity> correctionEntities = correctionRepository.findByDateSaisieBetween(startDate, endDate);
        return correctionEntities.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CorrectionDTO getCorrectionByEmail(String email, Long correctionId) {
        Optional<CorrectionEntity> correctionOptional = correctionRepository.findByIdAndUser_Email(correctionId, email);
        if (correctionOptional.isPresent()) {
            return mapEntityToDto(correctionOptional.get());
        } else {
            throw new IllegalArgumentException("Correction not found for user with email: " + email + " and correction ID: " + correctionId);
        }
    }

    private CorrectionDTO mapEntityToDto(CorrectionEntity correctionEntity) {
        CorrectionDTO correctionDTO = new CorrectionDTO();
        correctionDTO.setId(correctionEntity.getId());
        correctionDTO.setValeurCorrection(correctionEntity.getValeurCorrection());
        correctionDTO.setDateSaisie(correctionEntity.getDateSaisie());
        return correctionDTO;
    }

    // You can add more helper methods as needed
}
