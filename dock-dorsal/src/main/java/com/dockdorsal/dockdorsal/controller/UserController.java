package com.dockdorsal.dockdorsal.controller;

import com.dockdorsal.dockdorsal.dto.UserDTO;
import com.dockdorsal.dockdorsal.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/user/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> updateUser(@PathVariable String email, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(email, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/user/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> deleteUser(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok("User avec l'email " + email + " a été supprimé avec succès.");
    }


    @GetMapping("/user/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String email) {
        UserDTO user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user/moi")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> getLoggedInUserDetails() {
        // Récupérer l'objet Authentication de Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Vérifier si l'authentification est valide et si l'utilisateur est authentifié
        if (authentication != null && authentication.isAuthenticated()) {
            // Récupérer les détails de l'utilisateur actuellement authentifié
            String userEmail = authentication.getName(); // Obtenez l'email de l'utilisateur

            // Vous pouvez maintenant utiliser l'email pour récupérer d'autres détails de l'utilisateur à partir de la base de données
            // Par exemple, vous pouvez utiliser le service UserService pour récupérer l'utilisateur par son email
            UserDTO loggedInUser = userService.getUserByEmail(userEmail);

            // Retourner les détails de l'utilisateur actuellement connecté
            return ResponseEntity.ok(loggedInUser);
        } else {
            // L'utilisateur n'est pas authentifié ou l'authentification est invalide
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
    }
}
