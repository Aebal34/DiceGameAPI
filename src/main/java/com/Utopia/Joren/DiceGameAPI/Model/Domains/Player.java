package com.Utopia.Joren.DiceGameAPI.Model.Domains;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "players")
@Data
@Builder
public class Player {

    @Id
    private String id;

    @Indexed(unique = true)
    private String nickname;

    private int userID;

    @JsonManagedReference
    private List<Game> games = new ArrayList<>();
}
