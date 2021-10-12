import javafx.scene.image.*;

public class Level {
    private Image background;
    private final double sizeX;
    private final double sizeY;
    private final boolean peaceful;
    private MovingAnimatedImage[] Object; //list of all object display of the level display on the screen
//    private MovingAnimatedImage[] npc; need an interface to make them talkable

    public Level(boolean peaceful){
        this.peaceful = peaceful;
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
