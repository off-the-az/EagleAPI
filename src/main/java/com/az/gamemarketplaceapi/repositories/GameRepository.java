package com.az.gamemarketplaceapi.repositories;

import com.az.gamemarketplaceapi.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByName(String gameName);
}
