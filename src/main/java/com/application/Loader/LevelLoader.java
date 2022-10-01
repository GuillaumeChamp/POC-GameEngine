package com.application.Loader;


import com.application.Game.Level.Level;
import com.application.Game.Level.LevelElements.Layer0.Tile;
import com.application.Game.Level.LevelElements.Layer1.OverTile;
import com.application.Graphic.Graphic_Const;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.io.File;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.util.ArrayList;

public class LevelLoader {

    final static String rep = "src"+File.separator+"main"+File.separator+"resources"+File.separator+"Level"+File.separator+"hub"+File.separator;
    final static String extension0 = ".level0";
    final static String extension1 = ".level1";
    final static String extensionImage = ".png";

    /**
     * Load a level from file
     * @param name level name
     * @return the loaded level
     * @throws Exception if one of the three files is missing
     */
    public static Level load(String name) throws Exception{
        ObjectInputStream oot = new ObjectInputStream(Files.newInputStream(new File(rep + name + extension0).toPath()));
        ObjectInputStream oot1 = new ObjectInputStream(Files.newInputStream(new File(rep + name + extension1).toPath()));
        int[][] tilesIndex = (int[][]) oot.readObject();
        OverTile[][] overTiles = (OverTile[][]) oot1.readObject();
        oot.close();
        oot1.close();
        int height = tilesIndex.length;
        int width = tilesIndex[0].length;
        int tileSize = Graphic_Const.TILES_SIZE;

        String texturePath = rep+name+extensionImage;
        Image image = new Image(new File(texturePath).toURI().toString());
        PixelReader reader = image.getPixelReader();
        ArrayList<Tile> SavedTile = new ArrayList<>();
        for (int j = 0; j < image.getWidth()/16; j++) {
            Image tileSkin = new WritableImage(reader, tileSize*j, 0, tileSize, tileSize);
            SavedTile.add(new Tile(tileSkin));
        }
        Tile[][] tiles =new Tile[height][width];
        for(int i=0;i<height;i++)
            for(int j=0;j<width;j++)
                tiles[i][j] = SavedTile.get(tilesIndex[i][j]);
        return new Level(name,overTiles,tiles);
    }

}
