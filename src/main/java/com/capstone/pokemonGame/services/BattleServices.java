package com.capstone.pokemonGame.services;

import com.capstone.pokemonGame.models.Level;
import com.capstone.pokemonGame.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BattleServices {
    @Autowired
    AttackServices as;

    public BattleServices(){
        this.as = new AttackServices();
    }

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
        boolean successfullAttack = rollTheDice();

            if(successfullAttack){
                if(attack == "low") {
                    System.out.println("Your attack was successfull! Your opponent took 10 damage");
                    opponentHP -= 10;
                    System.out.println("Opponents HP is now " + opponentHP);
                    level.setOpponentHP(opponentHP);
                    as.setAttack(null);
                } else {
                    System.out.println("Your attack was successfull! Your opponent took 14 damage");
                    opponentHP -= 14;
                    System.out.println("Opponents HP is now " + opponentHP);
                    level.setOpponentHP(opponentHP);
                    as.setAttack(null);
                }
            } else {
                if(attack == "low") {
                    System.out.println("Attack was unsuccsessfull, your pokemon took 7 damage");
                    playerHP -= 7;
                    System.out.println("Pokemon HP is now " + playerHP);
                    player.setHp(playerHP);
                    as.setAttack(null);
                } else {
                    System.out.println("Attack was unsuccsessfull, your pokemon took 11 damage");
                    playerHP -= 11;
                    System.out.println("Pokemon HP is now " + playerHP);
                    player.setHp(playerHP);
                    as.setAttack(null);
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
