package com.Utopia.Joren.DiceGameAPI.Controllers;

import com.Utopia.Joren.DiceGameAPI.Model.Domains.Game;
import com.Utopia.Joren.DiceGameAPI.Model.Domains.Player;
import com.Utopia.Joren.DiceGameAPI.Model.Dto.GameDto;
import com.Utopia.Joren.DiceGameAPI.Model.Dto.PlayerDto;
import com.Utopia.Joren.DiceGameAPI.Model.Services.IGameService;
import com.Utopia.Joren.DiceGameAPI.Model.Services.IPlayerService;
import com.Utopia.Joren.DiceGameAPI.Model.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@Slf4j
public class PlayerController {

    private final IPlayerService playerService;

    private final UserService userService;

    private final IGameService gameService;

    public PlayerController(IPlayerService playerService, UserService userService, IGameService gameService) {
        this.playerService = playerService;
        this.userService = userService;
        this.gameService = gameService;
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

    @PutMapping
    public ResponseEntity<String> editPLayerNickname(@RequestParam String actualNickname, @RequestBody String newNickname){

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        int userID = userService.findUserByEmail(userEmail).getBody().getId();

        Player player = playerService.getPlayerByNickname(actualNickname).getBody();

        if(player != null){
            if(player.getUserID() == userID){
                return playerService.editPlayersNickname(actualNickname, newNickname);
            }
        }else{
            return playerService.editPlayersNickname(actualNickname, newNickname);
        }
        return new ResponseEntity<>("This player belongs to another user.", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/{nickname}/games")
    public ResponseEntity<String> rollDice(@PathVariable String nickname){

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        int userID = userService.findUserByEmail(userEmail).getBody().getId();

        Player player = playerService.getPlayerByNickname(nickname).getBody();

        if(player != null){
            if(player.getUserID() == userID){
                Game game = new Game();
                game.setPlayer(player);
                playerService.addGameToPlayer(game);
                return gameService.saveGame(game);
            }
        }else{
            return new ResponseEntity<>("This player doesn't exist.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("This player belongs to another user.", HttpStatus.FORBIDDEN);
    }

    private int getUserID(){

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        return userService.findUserByEmail(userEmail).getBody().getId();
    }
}
