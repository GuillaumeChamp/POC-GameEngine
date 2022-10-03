package com.application.Game.Level;

import com.application.Graphic.Elements.MovingAnimatedImage;
import com.application.Graphic.Graphic_Const;

public class PlayerMovement extends MovingAnimatedImage {

    public PlayerMovement(String path, double duration) {
        super(path,duration, Graphic_Const.SPRITE_WIDTH,Graphic_Const.SPRITE_HEIGHT,0);
    }
    public void update(Level location) {
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

    public void changeLevel(Level newLevel,double x,double y){
        XLim = newLevel.getSizeX();
        YLim = newLevel.getSizeY();
        velocityX=0;
        velocityY=0;
        positionX=x;
        positionY=y;
    }
    public void setPosition(double x, double y){
        this.positionX=x;
        this.positionY=y;
    }
}
