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
    public void update(){
        this.positionY += this.velocityY;
        this.positionX += this.velocityX;
    }
}
