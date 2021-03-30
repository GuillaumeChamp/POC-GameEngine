import javafx.scene.image.*;

public class MovingAnimatedImage extends AnimatedImage{
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;

    public MovingAnimatedImage(Image[] frames, double duration) {
        positionX = 0;
        positionY = 0;
        this.frames = frames;
        this.duration = duration;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setVelocity(double velocityX,double velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }
    public void addVelocity(double velocityX,double velocityY) {
        this.velocityX =+ velocityX;
        this.velocityY =+ velocityY;
    }
    public void update() {
        positionY += velocityY;
        positionX += velocityX;
        if (0 > positionX) positionX = 0;
        if (0 > positionY) positionY = 0;
        if (positionX > 2048-40) positionX = 2048 - 40;
        if (positionY > 1660 - 40) positionY = 1660 - 40;
    }
}