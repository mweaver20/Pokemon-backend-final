package com.capstone.pokemonGame.services;

import com.capstone.pokemonGame.models.Level;
import com.capstone.pokemonGame.models.Levels;
import com.capstone.pokemonGame.models.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BattleServices {

    //returns list of all levels created in levels entity
    public boolean rollTheDice() {
        Random random =  new Random();
        int diceRoll = random.nextInt(11);
        return diceRoll >= 5? true: false;
    }

    //will take players battle option, determine if attack was successfull, and update both opponent and players HP
    public void battle(Player player, Level level, String attack){
        int playerHP = player.getHp();
        int opponentHP = level.getOpponentHP();
        boolean sucessfulAttack = rollTheDice();

        if(sucessfulAttack){
            if(attack == "low") {
                opponentHP -= 10;
                level.setOpponentHP(opponentHP);
            } else {
                opponentHP -= 14;
                level.setOpponentHP(opponentHP);
            }
        } else {
            if(attack == "low") {
                playerHP -= 7;
                player.setHp(playerHP);
            } else {
                playerHP -= 11;
                player.setHp(playerHP);
            }
        }

    }

    //pokestop functionality increases players Hp based on random number 0 - 10
    public void pokeStop(Player player) {
        Random random =  new Random();
        int diceRoll = random.nextInt(11);
        int playerHP = player.getHp();
        int newHP = playerHP += diceRoll;
        player.setHp(newHP);
    }


}
