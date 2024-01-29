package com.az.gamemarketplaceapi.repositories;

import com.az.gamemarketplaceapi.models.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    Attribute findByName(String name);
}
