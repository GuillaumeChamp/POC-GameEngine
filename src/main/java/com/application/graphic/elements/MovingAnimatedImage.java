package com.application.graphic.elements;

public class MovingAnimatedImage extends AnimatedImage {
    protected double positionX;
    protected double positionY;
    protected double velocityX;
    protected double velocityY;

    public MovingAnimatedImage(String path, double duration,int width,int length,int animationId) {
        super(path,width,length,animationId);
        positionX = 0;
        positionY = 0;
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
        this.velocityX += velocityX;
        this.velocityY += velocityY;
    }
}