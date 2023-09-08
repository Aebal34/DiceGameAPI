package com.Utopia.Joren.DiceGameAPI.Model.Dto;

import com.Utopia.Joren.DiceGameAPI.Model.Domains.Game;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlayerDto {

    private String nickname;

    private int userID;

    private List<Game> games;
}
