package com.Utopia.Joren.DiceGameAPI.Model.Services;

import com.Utopia.Joren.DiceGameAPI.Model.Domains.Player;
import com.Utopia.Joren.DiceGameAPI.Model.Dto.PlayerDto;
import com.Utopia.Joren.DiceGameAPI.Model.Repositories.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlayerService implements IPlayerService{

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public ResponseEntity<String> addPlayer(PlayerDto playerDto){

        Player player = Player.builder()
                .nickname(playerDto.getNickname())
                .userID(playerDto.getUserID())
                .build();

        playerRepository.save(player);

        return new ResponseEntity<>("Player "+player.getNickname()+" created.", HttpStatus.CREATED);
    }
}
