package Fight;

import javafx.scene.image.Image;

public class Hero extends Entity{
    public boolean isInFront = true;
    public Hero(int hp, int armor, int speed, int damage, String name, Image[] skin) {
        super(hp, armor, speed, damage, name, skin);
    }

    @Override
    public void act() throws Exception {

    }
}
