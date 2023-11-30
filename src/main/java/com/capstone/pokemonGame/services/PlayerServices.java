package com.capstone.pokemonGame.services;
import com.capstone.pokemonGame.models.Player;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServices {
    //create new player with username
    public Player createPlayer(String username){
        return new Player(username);
    }

    public void setPlayerCharacter(Player player, String character){
        player.setCharacter(character);
    }

    public String getCharacterSelection(Player player){
        return player.getCharacter();
    }

   //updates players HP to int argument
   public void updatePlayerHP(Player player, int HP) {
        player.setHp(HP);
   }

   //updates players level by 1
   public void updatePlayerLevel(Player player) {
        int currentLevel = player.getLevel();
        player.setLevel(currentLevel + 1);
   }

    //returns a list with the players level and HP information, first item is level and second is HP
    public List<Integer> getPlayerLevelAndHP(Player player){
        Integer playerHP = player.getHp();
        Integer playerLevel = player.getLevel();

        List<Integer> levelAndHP = new ArrayList<>();
        levelAndHP.add(playerLevel);
        levelAndHP.add(playerHP);

        return levelAndHP;
    }



}
