package com.application.Game.Universal;


import com.application.Game.Level.Level;
import com.application.Game.Level.LevelElements.Layer1.Warp;
import com.application.Game.Level.LevelElements.TPException;
import com.application.Game.Level.PlayerMovement;
import com.application.Loader.LevelLoader;

public class Player {
    public PlayerMovement skin;
    public Level location;
    private final double FRAME_DURATION=0.2;

    public Player(){
        try {
            location = LevelLoader.load("hub");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        skin = new PlayerMovement("player.png", FRAME_DURATION);
    }

    public Player(String x,String y,String level) throws Exception {
        location = LevelLoader.load(level);
        skin = new PlayerMovement("player.png", FRAME_DURATION);
        skin.teleportPlayer(Double.parseDouble(x),Double.parseDouble(y));
    }

    public void update() {
        try {
            skin.update(location);
        } catch (TPException e) {
            changeLevel(e.getTp());
        }
    }

    /**
     * Teleport a player to a new level
     * @param tp the trigger warp holding information to new location
     */
    private void changeLevel(Warp tp){
        try {
            this.location = LevelLoader.load(tp.getExit());
            skin.teleportPlayer(tp.getxDes(),tp.getyDes());
        } catch (Exception ex) {
            System.out.println("Trying to tp to a non existing level");
            throw new RuntimeException(ex);
        }
    }
}
