package Fight;

import Universal.AnimatedImage;

import java.util.ArrayList;

public abstract class Enemy extends Entity{
    public Enemy(int hp, int armor, int speed, int damage, String name, AnimatedImage skin) {
        super(hp, armor, speed, damage, name, skin);
    }

    /**
     * Select randomly a target among heroes according to its position
     * complex code and loop to be fair
     * @param heroes list of all heroes alive
     */
    public void SelectTarget (ArrayList<Hero> heroes){
        target=null;
        boolean front = false;
        double rng = Math.random();
        if (rng>0.8) front = true;
        for (Hero h : heroes){
            rng = Math.random();
            if (h.isInFront){
                if (front&target==null) target=h;
                else if (rng>0.5) target=h;
            }
            else {
                if ((!front) & target==null) target=h;
                else if (rng>0.5) target=h;
            }
        }
    }

    @Override
    public void act() throws Exception {
        Attack(target);
    }
}
