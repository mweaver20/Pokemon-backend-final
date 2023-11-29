package com.capstone.pokemonGame.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;


public class Player {
    private String username;
    private int hp;
    private int level;

    // Constructors, getters, setters...

    // Constructors
    public Player(String username) {
        // Default constructor
        this.username = username;
        this.hp = 100;
        this.level = 1;
    }


    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //HP is also players score
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
