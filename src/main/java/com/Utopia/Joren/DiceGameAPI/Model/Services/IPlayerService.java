package com.Utopia.Joren.DiceGameAPI.Model.Services;

import com.Utopia.Joren.DiceGameAPI.Model.Dto.PlayerDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPlayerService {

    ResponseEntity<String> addPlayer(PlayerDto playerDto);

    ResponseEntity<List<PlayerDto>> getAllByUser(int userID);
}
