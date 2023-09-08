package com.Utopia.Joren.DiceGameAPI.Controllers;

import com.Utopia.Joren.DiceGameAPI.Model.Dto.PlayerDto;
import com.Utopia.Joren.DiceGameAPI.Model.Services.IPlayerService;
import com.Utopia.Joren.DiceGameAPI.Model.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/players")
@Slf4j
public class PlayerController {

    private final IPlayerService playerService;

    private final UserService userService;


    public PlayerController(IPlayerService playerService, UserService userService) {
        this.playerService = playerService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> addPlayer(@RequestBody PlayerDto playerDto){

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        int userID = userService.findUserByEmail(userEmail).getBody().getId();

        playerDto.setUserID(userID);

        return playerService.addPlayer(playerDto);
    }

    @GetMapping
    public ResponseEntity<List<PlayerDto>> getPlayers(){

        return playerService.getAllByUser(getUserID());
    }

    private int getUserID(){

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        return userService.findUserByEmail(userEmail).getBody().getId();
    }
}
