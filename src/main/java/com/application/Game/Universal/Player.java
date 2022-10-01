package com.application.Game.Universal;


import com.application.Game.Level.Level;
import com.application.Game.Level.PlayerMovement;
import com.application.Loader.LevelLoader;

public class Player {
    public PlayerMovement skin;
    public Level location;
    private TriggerList progression;

    public Player(){
        try {
            location = LevelLoader.load("hub");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        skin = new PlayerMovement("Skin/player.png", 1,location);
    }
    public Player(String x,String y,String level) throws Exception {
        location = LevelLoader.load(level);
        skin = new PlayerMovement("Skin/player.png", 1,location);
        skin.setPosition(Double.parseDouble(x),Double.parseDouble(y));
    }
}
