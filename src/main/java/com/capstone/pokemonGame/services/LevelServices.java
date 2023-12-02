package com.capstone.pokemonGame.services;

import com.capstone.pokemonGame.models.*;
import com.capstone.pokemonGame.repositories.ScoresRepository;
import com.capstone.pokemonGame.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//import java.util.Scanner;

@Service
public class LevelServices {
    private BattleServices bs;
    private AttackServices as;
    private ScoresService ss;
    private UserRepository us;
    private ScoresRepository sr;

    @Autowired
    public LevelServices(BattleServices bs, AttackServices as, UserRepository us, ScoresRepository sr){
        this.bs = bs;
        this.as = as;
        this.us = us;
        this.sr = sr;
        this.ss = new ScoresService(us, sr);
    }

    //plays single level
    public String playLevel(Player player) {
        //getting info needed to play game
        int playerLevel = player.getLevel();
        Level level = getLevelInfo(playerLevel);

        String result = null;

        switch(level.getLevelTitle()) {
            case "battle":
                int opponentHP = level.getOpponentHP();
                int playerHP = player.getHp();
                while(opponentHP > 0 && playerHP > 0) {
                    //if attack is null then redirect to attack selection page which redirects users back to battle page
                    String attack = as.getAttack();
                    if(attack == null){
                        String getAttack = "redirect:/verified/battle";
                        return getAttack;
                    }
                    //battle
                    bs.battle(player,level, attack);
                }
                //check players and opponents HP after each battle and redirect appropratly
                if(playerHP <= 0){
                    //redirect to game over page if player dies
                    result = "redirect:/verified/gameOver";
                } else if(opponentHP <= 0){
                    //redirect to in between levels page if player wins level and increase players level property
                    int newLevel = playerLevel + 1;
                    //sets players new level
                    player.setLevel(newLevel);
                    //redirects users to in-between levels page;
                    result = "redirect:/verified/levels";
                }
                break;


            case "pokestop":
                bs.pokeStop(player);
                int currentLevel = player.getLevel();
                player.setLevel(currentLevel + 1);
                result = "redirect:/verified/levels";
                break;

            case "final":
                int playHP = player.getHp();
                int opHP = level.getOpponentHP();
                while(opHP > 0 && playHP > 0) {
                    String attack = as.getAttack();
                    if(attack == null){
                        String getAttack = "redirect:/verified/battle";
                        return getAttack;
                    }
                    bs.battle(player,level, attack);
                }
                if(playHP <= 0){
                    result = "redirect:/verified/gameOver";
                } else if (opHP <= 0) {
                    //sending players username and score into database
                    ss.updateScoreByUsername(player.getUsername(), player.getHp());
                    result = "redirect:/verified/gameCompleted";
                }
                break;
        }
        //returns proper redirectory endpoint
        return result;
    }

    public List<Level> getLevels() {
        Levels levels = new Levels();
        return levels.getLevels();
    }

    public Level getLevelInfo(int level){
        List<Level> levels = getLevels();
        return levels.get(level - 1);
    }


}
