package com.Utopia.Joren.DiceGameAPI.Model.Repositories;

import com.Utopia.Joren.DiceGameAPI.Model.Domains.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {


}
