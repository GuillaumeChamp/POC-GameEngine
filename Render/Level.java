import javafx.scene.image.*;

public class Level {
    private Image background;
    private final double sizeX;
    private final double sizeY;
    private MovingAnimatedImage[] Object; //list of all object display of the level display on the screen
//    private MovingAnimatedImage[] npc; need an interface to make them talkable

    public Level(){
        background = new Image(".//Resources//Level//town_land.png");
        sizeY = background.getHeight();
        sizeX = background.getWidth();
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
