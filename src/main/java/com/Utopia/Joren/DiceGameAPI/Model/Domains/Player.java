package com.Utopia.Joren.DiceGameAPI.Model.Domains;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "players")
@Data
public class Player {

    @Id
    private String id;

    private Long playerID;

    private String nickname;

    @JsonManagedReference
    private List<Game> games = new ArrayList<>();


}
