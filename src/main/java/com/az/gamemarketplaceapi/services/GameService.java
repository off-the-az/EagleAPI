package com.az.gamemarketplaceapi.services;


import com.az.gamemarketplaceapi.models.Game;
import com.az.gamemarketplaceapi.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }
    public Optional<Game> getGameById(Long id){
        return gameRepository.findById(id);
    }

    public Game getGameByName(String name){
        return gameRepository.findByName(name);
    }

    public void saveGame(Game game){
        gameRepository.save(game);
    }
}
