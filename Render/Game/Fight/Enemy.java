package Game.Fight;

import Graphic.Elements.AnimatedImage;
import Game.Universal.Stuff.Item;
import Game.Universal.Stuff.Loot;

import java.util.ArrayList;

public class Enemy extends Entity{
    public final double xp;
    private final Loot[] loots;

    public Enemy(int hp, int armor, int speed, int damage, String name, AnimatedImage skin, double xp, Loot[] loots) {
        super(hp, armor, speed, damage, name, skin);
        this.xp = xp;
        this.loots = loots;
    }

    public void SelectAttack(ArrayList<Hero> heroes,ArrayList<Enemy> enemies){
        //TODO : implements other possibles actions
        action =FightActions.ATTACK;
        SelectTargetSimpleAttack(heroes);
    }

    /**
     * Select randomly a target among heroes according to its position
     * complex code and loop to be fair
     * @param heroes list of all heroes alive
     */
    private void SelectTargetSimpleAttack(ArrayList<Hero> heroes){
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

    public Item loot(){
        double rng = Math.random();
        if (loots==null) return null;
        for (Loot loot : loots)
            if (loot.tryToLoot(rng)) return loot;
        return null;
    }

    @Override
    public boolean performDeterminedAction() {
        if (target==null) return false;
        return attack(target);
    }
}
