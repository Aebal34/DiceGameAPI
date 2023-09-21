package com.Utopia.Joren.DiceGameAPI.Model.Dto;

import com.Utopia.Joren.DiceGameAPI.Model.Domains.Player;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameDto {

    private int result;

    private boolean win;

    private Player player;
}
