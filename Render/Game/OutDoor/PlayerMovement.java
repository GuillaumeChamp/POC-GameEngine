package Game.OutDoor;

import Graphic.Elements.MovingAnimatedImage;

public class PlayerMovement extends MovingAnimatedImage {
    double XLim;
    double YLim;

    public PlayerMovement(String path, double duration,Level level) {
        super(path, duration);
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
}
