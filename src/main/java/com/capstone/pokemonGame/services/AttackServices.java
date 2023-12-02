package com.capstone.pokemonGame.services;

import org.springframework.stereotype.Service;

@Service
public class AttackServices {
    private String attack;
    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }


}
