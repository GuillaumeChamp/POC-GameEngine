package Universal;

import javafx.scene.image.*;

public class AnimatedImage
{
    protected Image[] frames;
    protected double duration;

    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setFrames(Image[] frames) {
        this.frames = frames;
    }
}
