package com.application.Game.Level;

import com.application.Game.Level.LevelElements.Layer0.Tile;
import com.application.Game.Level.LevelElements.Layer1.Collision;
import com.application.Game.Level.LevelElements.Layer1.OverTile;
import com.application.Game.Level.LevelElements.Layer1.Warp;
import com.application.Graphic.Graphic_Const;

import java.util.ArrayList;

public class Level {
    private final String name;
    private final Tile[][] tiles;
    private final OverTile[][] overTiles;

    private ArrayList<Warp> tp;

    public Level(String name,OverTile[][] overTiles, Tile[][] tiles){
        this.name=name;
        this.overTiles=overTiles;
        this.tiles=tiles;
    }
    public Tile[][] getTiles() {
        return tiles;
    }
    public boolean isCollision(double playerX, double playerY){
        int tileSize = Graphic_Const.TILES_SIZE;
        double xIndex = Math.ceil(playerX / (tileSize));
        if (xIndex==-0) xIndex = -1;
        double yIndex = Math.ceil(playerY / (tileSize));
        if (yIndex==-0) yIndex = -1;
        try {
            return overTiles[(int) xIndex][(int) yIndex].getClass()== Collision.class;
        }catch (ArrayIndexOutOfBoundsException e){
            throw new OOBException((int) yIndex, (int) xIndex);
        }catch (NullPointerException ee){
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public double getSizeX() {
        return tiles.length*Graphic_Const.TILES_SIZE;
    }

    public double getSizeY() {
        return tiles[0].length*Graphic_Const.TILES_SIZE;
    }
}
