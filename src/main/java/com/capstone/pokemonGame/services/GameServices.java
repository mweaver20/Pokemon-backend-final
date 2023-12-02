package com.capstone.pokemonGame.services;

import com.capstone.pokemonGame.models.Player;
import com.capstone.pokemonGame.repositories.ScoresRepository;
import com.capstone.pokemonGame.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GameServices {
    private LevelServices ls;
    private BattleServices bs;
    private AttackServices as;
    private UserRepository ur;
    private ScoresRepository sr;

    public GameServices(UserRepository ur, ScoresRepository sr){
        this.bs = new BattleServices();
        this.as = new AttackServices();
        this.ur = ur;
        this.sr = sr;
        this.ls = new LevelServices(bs, as, ur, sr);
    }

    //plays all levels until player runs out of health
    public String playGame(Player player) {
        int playerHP = player.getHp();
        //play levels and redirect users to either game over or game completed page
        String result = ls.playLevel(player);
        return result;
    }
}
