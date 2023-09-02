package com.Utopia.Joren.DiceGameAPI.Security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


public class JWTGenerator {

    public String generateToken(Authentication authentication){

        String email = authentication.getName();
        return "Hello world";
    }
}
