package com.capstone.pokemonGame.services;

import com.capstone.pokemonGame.models.Level;
import com.capstone.pokemonGame.models.Levels;
import com.capstone.pokemonGame.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class LevelServices {
    private BattleServices bs;
    private Scanner sc;

    @Autowired
    public LevelServices(BattleServices bs){
        this.bs = bs;
        this.sc = new Scanner(System.in);
    }

    //plays single level
    public void playLevel(Player player) {
        int playerLevel = player.getLevel();
        //move to outer function
        //int playerHP = player.getHp();
        Level level = getLevelInfo(playerLevel);

        switch(level.getLevelTitle()) {
            case "battle":
                int opponentHP = level.getOpponentHP();
                while(opponentHP > 0) {
                    String attack = getBattleOption(player);
                    bs.battle(player,level, attack);
                }
                int newLevel = playerLevel + 1;
                System.out.println("you have defeated your opponent! Moving on to level " + newLevel);
                player.setLevel(newLevel);
                break;

            case "pokestop":
                System.out.println("Welcome to the poke stop! Lets se what we can do for your pokemon!");
                bs.pokeStop(player);
                System.out.println("Our nurses were able to increase your pokemons health to " + player.getHp() + "HP! Come again!");
                System.out.println("Moving to next level");
                int currentLevel = player.getLevel();
                player.setLevel(currentLevel + 1);
                break;

            case "final":
                int opHP = level.getOpponentHP();
                while(opHP > 0) {
                    String attack = getBattleOption(player);
                    bs.battle(player,level, attack);
                }
                System.out.println("Congrats! You have defeated all your opponents and finished the game!");
                System.out.println("Your final score is" + player.getHp() + "!");
                //add end of game services here
                break;
        }

    }

    public List<Level> getLevels() {
        Levels levels = new Levels();
        return levels.getLevels();
    }

    public Level getLevelInfo(int level){
        List<Level> levels = getLevels();
        return levels.get(level - 1);
    }

    public String getBattleOption(Player player){
        Level currentLevel = getLevelInfo(player.getLevel());
        String opponent = currentLevel.getOpponent();
        int opponentHP = currentLevel.getOpponentHP();

        System.out.println("Your opponent is " + opponent + " and their HP level is " + opponentHP);
        System.out.println("Please choose your attack option: \n" +
                "1. type low for the low risk attack option\n" +
                "2. type high for the high risk attack option");
        String attack = sc.nextLine().toLowerCase();
        return attack;
    }





}
