package com.Utopia.Joren.DiceGameAPI.Model.Services;

import com.Utopia.Joren.DiceGameAPI.Model.Domains.Game;
import com.Utopia.Joren.DiceGameAPI.Model.Domains.Player;
import com.Utopia.Joren.DiceGameAPI.Model.Dto.GameDto;
import com.Utopia.Joren.DiceGameAPI.Model.Dto.PlayerDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPlayerService {

    ResponseEntity<String> addPlayer(PlayerDto playerDto);

    ResponseEntity<List<PlayerDto>> getAllByUser(int userID);

    ResponseEntity<String> editPlayersNickname(String nickname, String newNickname);

    ResponseEntity<Player> getPlayerByNickname(String nickname);

    void addGameToPlayer(Game game);
}
