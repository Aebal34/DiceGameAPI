package com.Utopia.Joren.DiceGameAPI.Model.Domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "games")
@Data
public class Game {

    @Id
    private String id;

    private int result;

    @JsonBackReference
    private Player player;

    public Game (){
        int dice1 =(int)(Math.random()*6+1);
        int dice2 = (int)(Math.random()*6+1);

        this.result = dice1+dice2;
    }
}
