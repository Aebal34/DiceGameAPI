package com.Utopia.Joren.DiceGameAPI.Model.Services;

import com.Utopia.Joren.DiceGameAPI.Model.Domains.Game;
import com.Utopia.Joren.DiceGameAPI.Model.Dto.GameDto;
import org.springframework.http.ResponseEntity;

public interface IGameService {

    ResponseEntity<String> saveGame(Game game);
}
