package com.capstone.pokemonGame.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndOfGameServices {
    @Autowired
    private ScoresService scoresService;

    public EndOfGameServices(ScoresService ss) {
        this.scoresService = ss;
    }

    public List<Object[]> getTopTenScores() {
        //get top 10 scores from database and their usernames
        List<Object[]> topScores = scoresService.getTop10ScoresWithUsernames();
        return topScores;
    }

}
