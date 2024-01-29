package com.az.gamemarketplaceapi.repositories;
import com.az.gamemarketplaceapi.models.GamesAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

public interface GamesAttributeRepository extends JpaRepository<GamesAttribute, Long> {
}

