package com.az.gamemarketplaceapi.services;

import com.az.gamemarketplaceapi.models.GamesAttribute;
import com.az.gamemarketplaceapi.repositories.GamesAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GamesAttributeService {

    @Autowired
    private GamesAttributeRepository gamesAttributeRepository;

    public List<GamesAttribute> getAllGamesAttribute(){
        return gamesAttributeRepository.findAll();
    }

    public void saveGamesAttribute(GamesAttribute gamesAttribute){
        gamesAttributeRepository.save(gamesAttribute);
    }
}
