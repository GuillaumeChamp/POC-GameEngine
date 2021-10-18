package Fight;

import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Enemy extends Entity{
    public Enemy(int hp, int armor, int speed, int damage, String name, Image[] skin) {
        super(hp, armor, speed, damage, name, skin);
    }

    /**
     * Select randomly a target among heroes according to its position
     * @param heroes list of all heroes alive
     * @return the target of the attack if there are only one
     */
    public void SelectTarget (ArrayList<Hero> heroes){
        target = heroes.get(0);
        for (Hero h : heroes){
            double rng = Math.random();
            if (h.isInFront){
                if (rng > 0.35) target=h;
            }
            else if(rng> 0.7) target = h;
        }
    }

    @Override
    public void act() throws Exception {
        Attack(target);
    }
}
