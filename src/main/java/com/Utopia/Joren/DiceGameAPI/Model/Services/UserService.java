package com.Utopia.Joren.DiceGameAPI.Model.Services;

import com.Utopia.Joren.DiceGameAPI.Model.Domains.Role;
import com.Utopia.Joren.DiceGameAPI.Model.Domains.UserEntity;
import com.Utopia.Joren.DiceGameAPI.Model.Dto.AuthResponseDto;
import com.Utopia.Joren.DiceGameAPI.Model.Dto.LoginDto;
import com.Utopia.Joren.DiceGameAPI.Model.Dto.RegisterDto;
import com.Utopia.Joren.DiceGameAPI.Model.Dto.UserDto;
import com.Utopia.Joren.DiceGameAPI.Model.Repositories.RoleRepository;
import com.Utopia.Joren.DiceGameAPI.Model.Repositories.UserRepository;
import com.Utopia.Joren.DiceGameAPI.Security.JWTGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository, AuthenticationManager authenticationManager, JWTGenerator jwtGenerator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
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

    public ResponseEntity<AuthResponseDto> login(LoginDto loginDto){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

    public ResponseEntity<UserDto> findUserByEmail(String email){

        UserEntity user = userRepository.findByEmail(email).get();

        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
