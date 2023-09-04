package com.Utopia.Joren.DiceGameAPI.Model.Dto;

import com.Utopia.Joren.DiceGameAPI.Model.Domains.Game;
import lombok.Data;

import java.util.List;

@Data
public class PlayerDto {

    private String nickname;

    private List<Game> games;
}