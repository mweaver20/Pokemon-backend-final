package com.capstone.pokemonGame.services;

import com.capstone.pokemonGame.models.Level;
import com.capstone.pokemonGame.models.Levels;
import com.capstone.pokemonGame.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServices {
    private BattleServices bs;
    public LevelServices(BattleServices bs){
        this.bs = bs;
    }
    public List<Level> getLevels() {
        Levels levels = new Levels();
        return levels.getLevels();
    }

    public Level getLevelInfo(int level){
        List<Level> levels = getLevels();
        return levels.get(level - 1);
    }


    public void playLevel(Player player) {
        int playerLevel = player.getLevel();
        int playerHP = player.getHp();
        Level level = getLevelInfo(playerLevel);
         switch(level.getLevelTitle()){
             case "battle":
                bs.battle(player,level,);
         }
    }




}
