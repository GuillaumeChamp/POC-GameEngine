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
/*
import javafx.scene.image.*;

        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.OutputStream;

public class MovingAnimatedImage extends AnimatedImage implements java.io.Serializable{
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private double accelerationX;
    private double accelerationY;
    private double Fx;
    private double Fy;
    //physic values
    final double mass = 40;
    final double g = 3;
    final double f = 2.33333;

    public MovingAnimatedImage(Image[] frames, double positionX, double positionY, double duration, int width, int height) {
        this.positionX = positionX;
        this.positionY = positionY;
        setFrames(frames);
        this.duration = duration;
        this.width = width;
        this.height = height;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setF(double Fx, double Fy) {
        this.Fx = Fx;
        this.Fy = Fy;
    }

    public void addF(double Fx, double Fy) {
        this.Fx += Fx;
        this.Fy += Fy;
    }

    public void update() {
        //acceleration
        accelerationX = (Fx-velocityX/f)/mass;
        accelerationY = (Fy+g-velocityY/f)/mass;
        //velocity
        velocityY += accelerationY;
        velocityX += accelerationX;
        //updating pose
        positionY += velocityY;
        positionX += velocityX;
        if (0 > positionX) positionX = 0;
        if (0 > positionY) {
            positionY = 0;
            velocityY = -velocityY;
        }
        if (positionX > 1712 - 40) positionX = 1712 - 40;
        if (positionY > 512 - 40) {
            positionY = 512 - 40;
            velocityY = -velocityY;
        }
    }

    public boolean if_hit(double x, double y) {
        boolean end = false;
        if (x > this.positionX)
            if (this.positionX + this.width > x)
                if (y > this.positionY)
                    if (this.positionY + this.height > y)
                        end = true;
        return end;
    }

    public void setState(MovingAnimatedImage ufo2) {
        this.positionX= ufo2.positionX;
        this.positionY= ufo2.positionY;
        this.velocityX= ufo2.velocityX;
        this.velocityY= ufo2.velocityY;
        this.width= ufo2.width;
        this.height= ufo2.height;
        this.accelerationX=ufo2.accelerationX;
        this.accelerationY= ufo2.accelerationY;
        this.Fx=ufo2.Fx;
        this.Fy=ufo2.Fy;
    }
}

 */