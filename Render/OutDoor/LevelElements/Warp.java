package OutDoor.LevelElements;

import OutDoor.Level;
import javafx.geometry.Rectangle2D;

public class Warp {
    Level exit;
    Rectangle2D hitBox;

    /**
     * Build the specified exit
     * @param exit Level where teleported
     * @param x upper left corner
     * @param y upper left corner
     * @param width width of the hit box
     * @param height height of the hit box
     */
    public Warp(Level exit, int x, int y, int width, int height) {
        this.exit = exit;
        this.hitBox = new Rectangle2D(x,y,width,height);
    }
    public Level teleport(){
        return exit;
    }
}