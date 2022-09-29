package com.upgradeTheQuest.Game.Universal;


import com.upgradeTheQuest.Game.OutDoor.Level;
import com.upgradeTheQuest.Game.OutDoor.PlayerMovement;

public class Player {
    public PlayerMovement skin;
    public Level location;
    private TriggerList progression;

    public Player(){
        location = new Level(false,128,72);
        skin = new PlayerMovement("Skin/player.png", 1,location);
    }
    public Player(String x,String y,String level){
        location = new Level(false,128,72);
        skin = new PlayerMovement("Skin/player.png", 1,location);
        skin.setPosition(Double.parseDouble(x),Double.parseDouble(y));
    }
}
