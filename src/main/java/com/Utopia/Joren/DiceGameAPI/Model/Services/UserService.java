package com.Utopia.Joren.DiceGameAPI.Model.Services;

import com.Utopia.Joren.DiceGameAPI.Model.Domains.Role;
import com.Utopia.Joren.DiceGameAPI.Model.Domains.UserEntity;
import com.Utopia.Joren.DiceGameAPI.Model.Dto.RegisterDto;
import com.Utopia.Joren.DiceGameAPI.Model.Repositories.RoleRepository;
import com.Utopia.Joren.DiceGameAPI.Model.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<String> register(RegisterDto registerDto){

        if(userRepository.existsByEmail(registerDto.getEmail())){
            return new ResponseEntity<>("Email already in use. Try to log in", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();

        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registration correct!", HttpStatus.OK);
    }
}
