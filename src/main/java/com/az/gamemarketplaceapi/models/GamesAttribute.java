package com.az.gamemarketplaceapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class GamesAttribute {
    private Long id;
    private Long gameId;
    private Long attributeId;

    public GamesAttribute(Long gameId, Long attributeId){
        this.attributeId = attributeId;
        this.gameId = gameId;
    }

    public GamesAttribute() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
}
