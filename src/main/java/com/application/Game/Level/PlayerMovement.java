package com.application.Game.Level;

import com.application.Game.Level.LevelElements.TPException;
import com.application.Graphic.Elements.MovingAnimatedImage;
import com.application.Graphic.Graphic_Const;
import javafx.scene.image.Image;

public class PlayerMovement extends MovingAnimatedImage {

    public PlayerMovement(String path, double duration) {
        super(path,duration, Graphic_Const.SPRITE_WIDTH,Graphic_Const.SPRITE_HEIGHT,0);
    }

    /**
     * Update the position of the player checking level constrain (collision and bound)
     * @param location current level
     */
    public void update(Level location) throws TPException {
        double oldY = positionY;
        double oldX = positionX;
        positionY += velocityY;
        positionX += velocityX;
        try {
            if (location.isCollision(positionX,positionY)){
                positionY= oldY;
                positionX = oldX;
            }
        }catch (OOBException e){
            if(e.lineIndex <0) positionX=0;
            if (e.lineIndex>=location.getSizeX()/16) positionX = location.getSizeX()-Graphic_Const.TILES_SIZE;
            if(e.columnIndex <0) positionY=0;
            if (e.columnIndex>=location.getSizeY()/16) positionY=location.getSizeY()-Graphic_Const.TILES_SIZE;
        }
    }

    /**
     * Teleport the player to a new position and reset his speed
     * @param x position in pixel
     * @param y position in pixel
     */
    public void teleportPlayer(double x, double y){
        velocityX=0;
        velocityY=0;
        positionX=x;
        positionY=y;
    }

    @Override
    public Image getFrame(double time) {
        if (velocityX==0 && velocityY==0) return frames[0];
        else return super.getFrame(time);
    }
}
