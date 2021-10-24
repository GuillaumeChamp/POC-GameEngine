package OutDoor;
import Universal.ImageBuilder;
import Universal.MovingAnimatedImage;

public class pnj {
    protected String name;
    protected MovingAnimatedImage entity;
    protected String[] dialogue;
    protected int state = 0;
    protected int[] startX;
    protected int[] startY;


    public pnj(String name, String skinName, String[] dialogue, int[] startX, int[] startY) {
        this.name = name;
        this.entity = new MovingAnimatedImage(new ImageBuilder().build(skinName),1);
        this.dialogue = dialogue;
        this.startX = startX;
        this.startY = startY;
    }

    public String talk(){
        return dialogue[state];
    }

    public void changeState(int state){
        this.state=state;
    }

    public int getStartX() {
        return startX[state];
    }

    public int getStartY() {
        return startY[state];
    }
}
