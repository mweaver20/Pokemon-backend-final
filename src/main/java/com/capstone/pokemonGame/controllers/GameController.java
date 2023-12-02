package com.capstone.pokemonGame.controllers;

import com.capstone.pokemonGame.models.*;
import com.capstone.pokemonGame.services.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@SessionAttributes("player")
public class GameController {
    @Autowired
    private final LoginServices ls;
    private final RegistrationServices rs;
    private final PlayerServices ps;
    private final AttackServices as;
    private final GameServices gs;
    private final ScoresService ss;
    public GameController(LoginServices ls, RegistrationServices rs, PlayerServices ps, LevelServices lvs, AttackServices as, BattleServices bs, GameServices gs, ScoresService ss){
        this.ls = ls;
        this.rs = rs;
        this.ps = ps;
        this.as = as;
        this.gs = gs;
        this.ss = ss;
    }

    //logs players in or denys access
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, Model model){
        //checks database to for provided username
        if(ls.usernameExists(loginRequest.getUsername())){
            //if username is found, check password
            if(ls.verifyPassword(loginRequest.getUsername(), loginRequest.getPassword())) {
                //if username and password are verified, create a new player instance to be used for game session
                Player player = ps.createPlayer(loginRequest.getUsername());
                //adding player as model attribute to access it across multiple controller methods
                model.addAttribute("player", player);
                return ResponseEntity.ok("user has been logged in");
            } else {
                //Incorrect password
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or password incorrect");
            }
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username not found");
        }
    }

    //registers new users and adds their information to database
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest loginRequest, Model model) {
        //checks if username is taken
        boolean usernameTaken = rs.usernameExists(loginRequest.getUsername());

        if(usernameTaken){
            //if username is taken, alert the user to choose another one
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken, please choose another");
        } else {
            //else register user to database and create a new player instance with their username
            rs.registerUser(loginRequest);
            Player player = ps.createPlayer(loginRequest.getUsername());
            //adding player as model attribute to access it across multiple controller methods
            model.addAttribute("player", player);
            return ResponseEntity.ok("account has been created");
        }

    }

    //gets selected playable character from user and sets players character property
    @PostMapping("verified/characterSelection")
    public ResponseEntity<String> selectCharacter(@RequestBody CharacterSelectionRequest csr, @ModelAttribute("player") Player player) {
        ps.setPlayerCharacter(player, csr.getSelectedCharacter());
        return ResponseEntity.ok("character has been set, game can now begin");
    }


    //sends players current HP and level to display for users in between levels
    @GetMapping("verified/levels")
    public List<Integer> getPlayerData(@ModelAttribute("player") Player player){
        List<Integer> playerStats = new ArrayList<>();
        playerStats = ps.getPlayerLevelAndHP(player);
        return playerStats;
    }

    //gets players attack selection, sets attack property, redirects users back to battle gameplay
    @PostMapping("verified/battle")
    public String getAttack(AttackOption attackOption){
        as.setAttack(attackOption.getAttack());
        return "redirect:/verified/battle/attack";
    }

    //starting game play sequence
    @GetMapping("verified/battle/attack")
    public String playGame(@ModelAttribute("player") Player player){
        String url = gs.playGame(player);
        return url;
    }

    //sends top 10 scores and corresponding usernames from database to display on game complete page
    @GetMapping("verified/gameComplete")
    public List<Object[]> getScores() {
        List<Object[]> scores = ss.getTop10ScoresWithUsernames();
        return scores;
    }


}
