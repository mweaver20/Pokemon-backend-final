package com.capstone.pokemonGame.services;

import com.capstone.pokemonGame.entity.Scores;
import com.capstone.pokemonGame.entity.User;
import com.capstone.pokemonGame.repositories.ScoresRepository;
import com.capstone.pokemonGame.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoresService {


    @Autowired
    private ScoresRepository scoresRepository;

    @Autowired
    private UserRepository userRepository;

    public ScoresService(UserRepository userRepository, ScoresRepository scoresRepository){
        this.scoresRepository = scoresRepository;
        this.userRepository = userRepository;
    }

    public void updateScoreByUsername(String username, int newScore) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<Scores> scoresOptional = scoresRepository.findByUser_Username(username);

            Scores scores;
            if (scoresOptional.isPresent()) {
                scores = scoresOptional.get();
            } else {
                scores = new Scores();
                scores.setUser(user);
            }

            scores.setScore(newScore);
            scoresRepository.save(scores);
        }
    }


    public List<Object[]> getTop10ScoresWithUsernames() {
        // Use a custom query to fetch the top 10 scores along with corresponding usernames
        List<Object[]> topScores = scoresRepository.findTop10ByOrderByScoreDesc();

        // Each Object[] in the result contains two elements: [username, score]
        return topScores;
    }
}
