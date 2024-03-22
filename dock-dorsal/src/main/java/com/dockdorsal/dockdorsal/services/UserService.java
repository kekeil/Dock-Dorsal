package com.dockdorsal.dockdorsal.services;


import com.dockdorsal.dockdorsal.dto.UserDTO;
import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);   // Utilisé pour la création d'un utilisateur par un administrateur ou à d'autres fins
    UserDTO updateUser(String email, UserDTO userDTO);
    void deleteUserByEmail(String email);
    List<UserDTO> getAllUsers();
    UserDTO getUserByEmail(String email);
}
