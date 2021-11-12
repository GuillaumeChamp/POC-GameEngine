package Fight;

import Universal.AnimatedImage;

public class Hero extends Entity{
    public boolean isInFront = true;
    public Hero(int hp, int armor, int speed, int damage, String name, AnimatedImage skin) {
        super(hp, armor, speed, damage, name, skin);
    }

    public void grantXp(double xp){
        //TODO !
    }

    @Override
    public void act() throws Exception {

    }
}
