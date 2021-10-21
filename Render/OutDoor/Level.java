package OutDoor;

import javafx.scene.image.*;

public class Level {
    private Image background;
    private final double sizeX;
    private final double sizeY;
    private final boolean peaceful;
    private MovingAnimatedImage[] Object; //list of all object display of the level display on the screen
//    private OutDoor.MovingAnimatedImage[] npc; need an interface to allow them to talk

    public Level(boolean peaceful){
        this.peaceful = peaceful;
        background = new Image(".//Resources//OutDoor.Level//town_land.png");
        sizeY = background.getHeight();
        sizeX = background.getWidth();
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
