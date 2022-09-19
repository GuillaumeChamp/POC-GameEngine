package Game.Fight;

import Graphic.Elements.AnimatedImage;

import java.io.Serializable;

public class Hero extends Entity implements Serializable {
    public boolean isInFront = true;
    public Hero(int hp, int armor, int speed, int damage, String name) {
        super(hp, armor, speed, damage, name, createSkin(name));
    }

    public static AnimatedImage createSkin(String name) {
        return new AnimatedImage("Skin//player.png",64,64,0);
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
    public void setSkin() {
        this.skin = Hero.createSkin(name);
    }
    public void grantXp(double xp){
        //TODO !
    }

}
