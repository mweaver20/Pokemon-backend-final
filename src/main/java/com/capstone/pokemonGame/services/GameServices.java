package com.capstone.pokemonGame.services;

import com.capstone.pokemonGame.models.Player;
import org.springframework.stereotype.Service;

@Service
public class GameServices {
    private LevelServices ls;
    private BattleServices bs;

    public GameServices(){
        this.bs = new BattleServices();
        this.ls = new LevelServices(bs);
    }

    //plays all levels until player runs out of health
    public void playGame(Player player) {
        int playerHP = player.getHp();
        while(playerHP > 0) {
            ls.playLevel(player);
        }
        System.out.println("Your pokemon has died :(");
        System.out.println("Game over");
        //run game over services
        System.exit(1);
    }
}
