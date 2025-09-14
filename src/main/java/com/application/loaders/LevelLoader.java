package com.application.loaders;


import com.application.game.level.Level;
import com.application.game.level.elements.layer0.Tile;
import com.application.game.level.elements.layer1.Collision;
import com.application.game.level.elements.layer1.Encounter;
import com.application.game.level.elements.layer1.OverTile;
import com.application.game.level.elements.layer1.Warp;
import com.application.graphic.Graphic_Const;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LevelLoader {
    private static final String DESTINATION = "destination";

    static final String LEVELS_REPOSITORY = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "level" + File.separator;
    static final String extension0 = ".level0";
    static final String EXTENSION_OVER_TILES = ".json";
    static final String extensionImage = ".png";

    /**
     * Load a level from file
     *
     * @param name level name
     * @return the loaded level
     * @throws Exception if one of the three files is missing
     */
    public static Level load(String name) throws Exception {
        ObjectInputStream oot = new ObjectInputStream(Files.newInputStream(new File(LEVELS_REPOSITORY + name + File.separator + name + extension0).toPath()));
        OverTile[][] overTiles = loadOverTiles(LEVELS_REPOSITORY + name + File.separator + name + EXTENSION_OVER_TILES);
        int[][] tilesIndex = (int[][]) oot.readObject();
        oot.close();
        int height = tilesIndex.length;
        int width = tilesIndex[0].length;
        int tileSize = Graphic_Const.TILES_SIZE;

        String texturePath = LEVELS_REPOSITORY + name + File.separator + name + extensionImage;
        Image image = new Image(new File(texturePath).toURI().toString());
        PixelReader reader = image.getPixelReader();
        ArrayList<Tile> SavedTile = new ArrayList<>();
        for (int j = 0; j < image.getWidth() / 16; j++) {
            Image tileSkin = new WritableImage(reader, tileSize * j, 0, tileSize, tileSize);
            SavedTile.add(new Tile(tileSkin));
        }
        Tile[][] tiles = new Tile[height][width];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                tiles[i][j] = SavedTile.get(tilesIndex[i][j]);
        return new Level(name, overTiles, tiles);
    }

    private static OverTile[][] loadOverTiles(String path) throws Exception {
        InputStream is = Files.newInputStream(Paths.get(path));
        JSONTokener token = new JSONTokener(is);
        JSONObject object = new JSONObject(token);
        OverTile[][] overTiles = new OverTile[object.getInt("length")][object.getInt("width")];
        JSONArray array = object.getJSONArray("overTile");
        for (int i = 0; i < array.length(); i++) {
            JSONObject tile = (JSONObject) array.get(i);
            try {
                tile.get(DESTINATION);
                overTiles[tile.getInt("y")][tile.getInt("x")] =
                        new Warp(tile.getString(DESTINATION), tile.getInt("xDestination"), tile.getInt("yDestination"));
            } catch (JSONException keyNotFound) {
                int id = tile.getInt("id");
                if (id > 0) overTiles[tile.getInt("y")][tile.getInt("x")] =
                        new Encounter(tile.getInt("id"));
                else overTiles[tile.getInt("y")][tile.getInt("x")] = new Collision();
            }
        }
        return overTiles;
    }

}
