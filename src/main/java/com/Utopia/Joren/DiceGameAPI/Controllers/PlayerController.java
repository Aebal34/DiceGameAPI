package com.Utopia.Joren.DiceGameAPI.Controllers;

import com.Utopia.Joren.DiceGameAPI.Model.Dto.PlayerDto;
import com.Utopia.Joren.DiceGameAPI.Model.Services.IPlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final IPlayerService playerService;


    public PlayerController(IPlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<String> addPlayer(@RequestBody PlayerDto playerDto){

        return playerService.addPlayer(playerDto);
    }
}
