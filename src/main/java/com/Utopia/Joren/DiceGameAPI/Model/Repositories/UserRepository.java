package com.Utopia.Joren.DiceGameAPI.Model.Repositories;

import com.Utopia.Joren.DiceGameAPI.Model.Domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<String> findByEmail(String email);
}
