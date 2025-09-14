package com.application.graphic.elements;

import com.application.game.universal.ImageHolder;
import javafx.scene.image.Image;

import java.io.Serializable;


public class AnimatedImage implements Serializable
{
    protected Image[] frames;
    protected double duration;

    /**
     * Create a sprite which can move
     * @param path spritesheet path
     * @param width image width
     * @param length image length
     * @param animationId row to load
     */
    public AnimatedImage(String path,int width,int length,int animationId){
        frames = ImageHolder.getSprites(path,animationId,width,length);
        duration = 0.1;
    }

    /**
     * Get a particular frame
     * @param time current time that is used to cycle through sprite sheet
     * @return a single Image
     */
    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    /**
     * Useful for animation and cinematic
     * @param duration frame duration on second (I guess)
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Useful to change the texture might be use on trigger or cinematic
     * @param frames new texture of the character
     */
    public void setFrames(Image[] frames) {
        this.frames = frames;
    }
}
