package com.dockdorsal.dockdorsal.security;

import com.dockdorsal.dockdorsal.dao.UserEntity;
import com.dockdorsal.dockdorsal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Configuration
public class OurUserInfoUserDetailsService implements UserDetailsService {
   // private static final Logger logger = LoggerFactory.getLogger(OurUserInfoUserDetailsService.class);
    private UserRepository userRepository;
    @Autowired
    public OurUserInfoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(username)
                .map(userDto -> {
                    UserEntity userEntity = new UserEntity();
                    userEntity.setEmail(userDto.getEmail());
                    userEntity.setPassword(userDto.getPassword());
                    userEntity.setRoles(userDto.getRoles());
                    // Set other fields if needed
                    return userEntity;
                });

        return userEntityOptional.map(OurUserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User Does Not Exist"));
    }
}
