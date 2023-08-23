package com.Utopia.Joren.DiceGameAPI.Model.Repositories;

import com.Utopia.Joren.DiceGameAPI.Model.Domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<String> findByEmail(String email);
}
