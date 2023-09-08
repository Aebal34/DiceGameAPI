package com.Utopia.Joren.DiceGameAPI.Model.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private int id;

    private String email;

    private String password;
}
