package com.dockdorsal.dockdorsal.services;

import com.dockdorsal.dockdorsal.dao.UserEntity;
import com.dockdorsal.dockdorsal.dto.UserDTO;
import com.dockdorsal.dockdorsal.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class UserServiceImpl implements UserService {
    //private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserEntity userEntity = mapDtoToEntity(userDTO);
        UserEntity savedUser = userRepository.save(userEntity);
        return mapEntityToDto(savedUser);
    }

    @Override
    public UserDTO updateUser(String email, UserDTO userDTO) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            UserEntity existingUser = optionalUser.get();
            existingUser.setNom(userDTO.getNom());
            existingUser.setPrénom(userDTO.getPrénom());
            existingUser.setEmail(userDTO.getEmail());
            existingUser.setPassword(userDTO.getPassword());
            existingUser.setRoles(userDTO.getRoles());

            UserEntity updatedUser = userRepository.save(existingUser);
            return mapEntityToDto(updatedUser);
        } else {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
    }


    public void deleteUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            return mapEntityToDto(optionalUser.get());
        } else {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
    }

    private UserEntity mapDtoToEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setNom(userDTO.getNom());
        userEntity.setPrénom(userDTO.getPrénom());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setRoles(userDTO.getRoles());
        return userEntity;
    }

    private UserDTO mapEntityToDto(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setNom(userEntity.getNom());
        userDTO.setPrénom(userEntity.getPrénom());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setRoles(userEntity.getRoles());
        return userDTO;
    }
}