package com.dockdorsal.dockdorsal.controller;


import com.dockdorsal.dockdorsal.dto.CorrectionDTO;
import com.dockdorsal.dockdorsal.services.CorrectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CorrectionController {

    private final CorrectionService correctionService;

    public CorrectionController(CorrectionService correctionService) {
        this.correctionService = correctionService;
    }

    @PostMapping("/corrections")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> submitCorrection(@RequestBody CorrectionDTO correctionDTO) {
        correctionService.submitCorrection(correctionDTO.getValeurCorrection());
        return ResponseEntity.ok("Correction soumise avec succès.");
    }



    @GetMapping("/corrections")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CorrectionDTO>> getAllCorrections() {
        List<CorrectionDTO> corrections = correctionService.getAllCorrections();
        return ResponseEntity.ok(corrections);
    }

    @GetMapping("/corrections/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CorrectionDTO>> getUserCorrections(@PathVariable String email) {
        List<CorrectionDTO> corrections = correctionService.getUserCorrections(email);
        return ResponseEntity.ok(corrections);
    }

    @GetMapping("/corrections/{email}/date-range")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CorrectionDTO>> getUserCorrectionsByDateRange(@PathVariable String email, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<CorrectionDTO> corrections = correctionService.getUserCorrectionsByDateRange(email, startDate, endDate);
        return ResponseEntity.ok(corrections);
    }

    @GetMapping("/corrections/date-range")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CorrectionDTO>> getAllCorrectionsByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<CorrectionDTO> corrections = correctionService.getAllCorrectionsByDateRange(startDate, endDate);
        return ResponseEntity.ok(corrections);
    }

    @GetMapping("/corrections/{email}/{correctionId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CorrectionDTO> getCorrectionByEmail(@PathVariable String email, @PathVariable Long correctionId) {
        CorrectionDTO correction = correctionService.getCorrectionByEmail(email, correctionId);
        return ResponseEntity.ok(correction);
    }

    @PutMapping("/corrections/{email}/{correctionId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> updateCorrection(@PathVariable String email, @PathVariable Long correctionId, @RequestBody CorrectionDTO correctionDTO) {
        double correctionValue = correctionDTO.getValeurCorrection();
        correctionService.updateCorrection(email, correctionId, correctionValue);
        return ResponseEntity.ok("Correction updated successfully.");
    }


    @DeleteMapping("/corrections/{email}/{correctionId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> deleteCorrection(@PathVariable String email, @PathVariable Long correctionId) {
        correctionService.deleteCorrection(email, correctionId);
        return ResponseEntity.ok("Correction deleted successfully.");
    }
    @GetMapping("/corrections/date-range/moi")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<CorrectionDTO>> getUserCorrectionsByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        // Récupérer l'e-mail de l'utilisateur authentifié
        String userEmail = getAuthenticatedUserEmail();

        // Utiliser l'e-mail récupéré pour obtenir les corrections de l'utilisateur dans la plage de dates spécifiée
        List<CorrectionDTO> corrections = correctionService.getUserCorrectionsByDateRange(userEmail, startDate, endDate);
        return ResponseEntity.ok(corrections);
    }
    private String getAuthenticatedUserEmail() {
        // Récupérer l'objet Authentication de Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Vérifier si l'authentification est valide et si l'utilisateur est authentifié
        if (authentication != null && authentication.isAuthenticated()) {
            // Récupérer l'e-mail de l'utilisateur authentifié
            return authentication.getName();
        }
        // Gérer les cas où l'utilisateur n'est pas authentifié
        throw new IllegalStateException("User not authenticated");
    }
    @GetMapping("/corrections/moi/{correctionId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<CorrectionDTO> getCorrectionByEmail(@PathVariable Long correctionId) {
        // Récupérer l'e-mail de l'utilisateur authentifié
        String userEmail = getAuthenticatedUserEmail();
        CorrectionDTO correction = correctionService.getCorrectionByEmail(userEmail, correctionId);
        return ResponseEntity.ok(correction);
    }

    @PutMapping("/corrections/moi/{correctionId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> updateCorrection(@PathVariable Long correctionId, @RequestBody CorrectionDTO correctionDTO) {
        // Récupérer l'e-mail de l'utilisateur authentifié
        String userEmail = getAuthenticatedUserEmail();
        double correctionValue = correctionDTO.getValeurCorrection();
        correctionService.updateCorrection(userEmail, correctionId, correctionValue);
        return ResponseEntity.ok("Correction updated successfully.");
    }

    @DeleteMapping("/corrections/moi/{correctionId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> deleteCorrection(@PathVariable Long correctionId) {
        // Récupérer l'e-mail de l'utilisateur authentifié
        String userEmail = getAuthenticatedUserEmail();
        correctionService.deleteCorrection(userEmail, correctionId);
        return ResponseEntity.ok("Correction deleted successfully.");
    }

}
