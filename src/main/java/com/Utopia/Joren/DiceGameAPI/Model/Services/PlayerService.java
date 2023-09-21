package com.Utopia.Joren.DiceGameAPI.Model.Services;

import com.Utopia.Joren.DiceGameAPI.Model.Domains.Game;
import com.Utopia.Joren.DiceGameAPI.Model.Domains.Player;
import com.Utopia.Joren.DiceGameAPI.Model.Domains.UserEntity;
import com.Utopia.Joren.DiceGameAPI.Model.Dto.PlayerDto;
import com.Utopia.Joren.DiceGameAPI.Model.Repositories.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService implements IPlayerService{

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public ResponseEntity<String> addPlayer(PlayerDto playerDto){

        Player player = Player.builder()
                .nickname(playerDto.getNickname())
                .userID(playerDto.getUserID())
                .build();

        playerRepository.save(player);

        return new ResponseEntity<>("Player "+player.getNickname()+" created.", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<PlayerDto>> getAllByUser(int userID) {

        List<Player> players = playerRepository.findAll().stream()
                .filter(player -> player.getUserID() == userID)
                .toList();

        List<PlayerDto> playerDtos = players.stream()
                .map(player -> {
                    return PlayerDto.builder()
                            .games(player.getGames())
                            .nickname(player.getNickname())
                            .userID(player.getUserID())
                            .build();
                })
                .toList();

        return new ResponseEntity<>(playerDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> editPlayersNickname(String nickname, String newNickname) {

        Optional<Player> player = Optional.ofNullable(playerRepository.findByNickname(nickname));
        if(player.isEmpty()){
            return new ResponseEntity<>("Player not found.", HttpStatus.NOT_FOUND);
        }
        player.get().setNickname(newNickname);

        playerRepository.save(player.get());

        return new ResponseEntity<>("Nickname changed.", HttpStatus.OK);
    }



    @Override
    public ResponseEntity<Player> getPlayerByNickname(String nickname) {

        Optional<Player> player = Optional.ofNullable(playerRepository.findByNickname(nickname));

        return player.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Override
    public void addGameToPlayer(Game game) {

        Player player = game.getPlayer();
        player.getGames().add(game);

        playerRepository.save(player);
    }
}
