package Game.Fight;

import Graphic.Elements.AnimatedImage;

public class Hero extends Entity{
    public boolean isInFront = true;
    public Hero(int hp, int armor, int speed, int damage, String name, AnimatedImage skin) {
        super(hp, armor, speed, damage, name, skin);
    }
    public void defineAttack(FightActions actions, Entity target){
        this.action =actions;
        this.target=target;
    }
    @Override
    public void performDeterminedAction() throws Exception {
        if (action ==FightActions.ATTACK) {
            this.attack(target);
        }
    }

    public void grantXp(double xp){
        //TODO !
    }

}
