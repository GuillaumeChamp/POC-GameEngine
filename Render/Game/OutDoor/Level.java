package Game.OutDoor;

import Game.OutDoor.LevelElements.PNJ;
import Game.OutDoor.LevelElements.Warp;
import Game.OutDoor.LevelElements.MovingAnimatedImage;
import javafx.scene.image.*;

import java.util.ArrayList;

public class Level {
    private final Image background;
    private final double sizeX;
    private final double sizeY;
    private final boolean peaceful;
    private MovingAnimatedImage[] Object; //list of all object display of the level display on the screen
    private PNJ[] npc;
    private ArrayList<Warp> tp;

    public Level(boolean peaceful){
        this.peaceful = peaceful;
        background = new Image("Level//town_land.png");
        sizeY = background.getHeight();
        sizeX = background.getWidth();
    }

    public ArrayList<Warp> getTp() {
        return tp;
    }

    public void setTp(ArrayList<Warp> tp) {
        this.tp = tp;
    }

    public boolean isPeaceful() {
        return peaceful;
    }

    public double getSizeX() {
        return sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }

    public Image getBackground() {
        return background;
    }
}
