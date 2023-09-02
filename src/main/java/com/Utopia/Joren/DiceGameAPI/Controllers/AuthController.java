package com.Utopia.Joren.DiceGameAPI.Controllers;

import com.Utopia.Joren.DiceGameAPI.Model.Dto.LoginDto;
import com.Utopia.Joren.DiceGameAPI.Model.Dto.RegisterDto;
import com.Utopia.Joren.DiceGameAPI.Model.Repositories.RoleRepository;
import com.Utopia.Joren.DiceGameAPI.Model.Repositories.UserRepository;
import com.Utopia.Joren.DiceGameAPI.Model.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;


    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){

        return userService.register(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){

        return userService.login(loginDto);
    }
}
