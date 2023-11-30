package com.capstone.pokemonGame.models;
import java.util.ArrayList;
import java.util.List;
public class Levels {


    //10 levels with 3 poke-stops
    private Level level1 = new Level(1, "battle", "Pikachu", 12);
    private Level level2 = new Level(2, "battle", "Raichu", 14);
    private Level level3 = new Level(3, "pokestop");
    private Level level4 = new Level(4, "battle", "Pidgey", 10);
    private Level level5 = new Level(5, "battle","Charmander",17 );
    private Level level6 = new Level(6, "pokestop");
    private Level level7 = new Level(7, "battle", "Squirtle", 20);
    private Level level8 = new Level(8, "battle", "Rattata", 15 );
    private Level level9 = new Level(9, "pokestop");
    private Level level10 = new Level(10, "Final","Charizard", 25);

    //list of levels
    private List<Level> levels = new ArrayList<>();
    public Levels() {
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        levels.add(level4);
        levels.add(level5);
        levels.add(level6);
        levels.add(level7);
        levels.add(level8);
        levels.add(level9);
        levels.add(level10);
    }

    public List<Level> getLevels() {
        return levels;
    }
}
