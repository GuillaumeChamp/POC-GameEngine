package com.upgradeTheQuest.Game.OutDoor;

import com.upgradeTheQuest.Graphic.Elements.MovingAnimatedImage;
import com.upgradeTheQuest.Graphic.Graphic_Const;

public class PlayerMovement extends MovingAnimatedImage {

    public PlayerMovement(String path, double duration,Level level) {
        super(path,duration, Graphic_Const.SPRITE_WIDTH,Graphic_Const.SPRITE_HEIGHT,0);
        XLim = level.getSizeX();
        YLim = level.getSizeY();
    }
    public void update() {
        positionY += velocityY;
        positionX += velocityX;
        if (0 > positionX) positionX = 0;
        if (0 > positionY) positionY = 0;
        if (positionX > XLim-40) positionX = XLim - 40;
        if (positionY > YLim- 40) positionY = YLim - 40;
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
