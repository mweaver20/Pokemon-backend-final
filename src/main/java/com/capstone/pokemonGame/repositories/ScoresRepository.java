package com.capstone.pokemonGame.repositories;

import com.capstone.pokemonGame.entity.Scores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoresRepository extends JpaRepository<Scores, Long> {
    Optional<Scores> findByUser_Username(String username);
    List<Object[]> findTop10ByOrderByScoreDesc();
}
