package com.Utopia.Joren.DiceGameAPI.Model.Services;

import com.Utopia.Joren.DiceGameAPI.Model.Domains.Game;
import com.Utopia.Joren.DiceGameAPI.Model.Dto.GameDto;
import com.Utopia.Joren.DiceGameAPI.Model.Repositories.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GameService implements IGameService{

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    @Override
    public ResponseEntity<String> saveGame(Game game) {

        gameRepository.save(game);
        return new ResponseEntity<>("Game saved.", HttpStatus.CREATED);
    }
}
