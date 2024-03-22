package com.dockdorsal.dockdorsal.repository;

import com.dockdorsal.dockdorsal.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
