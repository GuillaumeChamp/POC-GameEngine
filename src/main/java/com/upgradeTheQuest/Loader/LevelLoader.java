package com.upgradeTheQuest.Loader;


import com.upgradeTheQuest.Game.OutDoor.Level;

public class LevelLoader extends Loader<Level> {
    @Override
    public Level load(String name) {
        return new Level(true,128,72);
    }
}
