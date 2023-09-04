package com.Utopia.Joren.DiceGameAPI.Model.Services;

import com.Utopia.Joren.DiceGameAPI.Model.Dto.PlayerDto;
import org.springframework.http.ResponseEntity;

public interface IPlayerService {

    public ResponseEntity<String> addPlayer(PlayerDto playerDto);
}
