package com.Utopia.Joren.DiceGameAPI.Model.Services;

import com.Utopia.Joren.DiceGameAPI.Model.Domains.UserEntity;
import com.Utopia.Joren.DiceGameAPI.Model.Dto.UserDto;
import com.Utopia.Joren.DiceGameAPI.Model.Repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity save(UserDto userDto){


    }
}
