package com.Utopia.Joren.DiceGameAPI.Model.Repositories;

import com.Utopia.Joren.DiceGameAPI.Model.Domains.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    public Optional<String> findByEmail(String email);

    public Boolean existsByEmail(String email);
}
