package com.az.gamemarketplaceapi.controllers;

import com.az.gamemarketplaceapi.models.Attribute;
import com.az.gamemarketplaceapi.models.Game;
import com.az.gamemarketplaceapi.models.GamesAttribute;
import com.az.gamemarketplaceapi.repositories.AttributeRepository;
import com.az.gamemarketplaceapi.repositories.GameRepository;
import com.az.gamemarketplaceapi.repositories.GamesAttributeRepository;
import com.az.gamemarketplaceapi.services.AttributeService;
import com.az.gamemarketplaceapi.services.FileService;
import com.az.gamemarketplaceapi.services.GameService;
import com.az.gamemarketplaceapi.services.GamesAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/game")
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private GamesAttributeService gamesAttributeService;

    @Autowired
    private FileService fileStorageService;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }
    @GetMapping("/get/id/")
    public ResponseEntity<?> getById(@RequestParam(value = "value", required = true, defaultValue = "1") Long id){
        try{
            return new ResponseEntity<>(gameService.getGameById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.CONFLICT);
        }
    }
    @PostMapping(value="/add", consumes = {"*/*"})
    public ResponseEntity<?> update(@ModelAttribute Game game, @RequestParam("file") MultipartFile file, @RequestParam("attributes") String attributes) {
        try {
            Game isExsiteGame = gameService.getGameByName(game.getName());
            if(isExsiteGame == null){
                String fileName = fileStorageService.storeFile("game", String.valueOf(game.getName()), file);
                String fileDownloadUri = "http://localhost:8000/downloadFile/game/" + fileName;
                game.setPhoto(fileDownloadUri);
                gameService.saveGame(game);
                String[] categories = attributes.split(", ");
                System.out.println(Arrays.toString(categories));
                for(String category : categories){
                    Attribute attribute = attributeService.findByName(category);
                    System.out.println(attribute);
                    Game exiteGame = gameService.getGameByName(game.getName());
                    System.out.println(exiteGame);
                    if(exiteGame != null && attribute != null){
                        System.out.println(exiteGame.getId() + "    " + attribute.getId());
                        GamesAttribute exiteGamesAttribute = new GamesAttribute(exiteGame.getId(), attribute.getId());
                        System.out.println(exiteGamesAttribute);
                        gamesAttributeService.saveGamesAttribute(exiteGamesAttribute);
                    }
                }
                return new ResponseEntity<>("Game added!", HttpStatus.OK);
            }else return new ResponseEntity<>("Game is excited!", HttpStatus.CONFLICT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.NOT_FOUND);
        }
    }
}
