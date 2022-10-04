package com.application.Game.Fight;

import com.application.Graphic.Elements.AnimatedImage;

import java.io.Serializable;

public class Hero extends Entity implements Serializable {
    protected boolean isInFront = true;
    private int xp = 0;
    private int level;

    public Hero(String name){
        super(10, 10, 10, 10, name, createSkin(name));
        switch (name) {
            case "1" :
                this.hpMax =32;
                this.hp = 32;
                this.armor = 5;
                this.speed = 10;
                this.damage = 5;
                break;
            case "2" :
                this.hpMax=40;
                this.hp = 40;
                this.armor = 10;
                this.speed = 8;
                this.damage = 1;
                break;
        }
        this.level = 0;
    }
    public boolean checkLevelUp(){
        return xp > Math.pow(2, 2+level);
    }

    public static AnimatedImage createSkin(String name) {
        return new AnimatedImage("player.png",64,64,0);
    }

    public void defineAttack(FightActions actions, Entity target){
        this.action=actions;
        this.target=target;
    }
    @Override
    public boolean performDeterminedAction() {
        if (action ==FightActions.ATTACK) {
            return this.attack(target);
        }
        return false;
    }
    public void setSkin() {
        this.skin = Hero.createSkin(name);
    }
    public void grantXp(int xp){
        if (this.xp==Integer.MAX_VALUE) return;
        if (this.level==-1) return;
        try {
            this.xp = Math.addExact(this.xp,xp);
        }catch (ArithmeticException e){
            this.xp = Integer.MAX_VALUE;
        }
        while (checkLevelUp()){
            this.levelUp();
        }

    }

    private void levelUp() {
        this.level++;
        this.hpMax = this.hpMax + 2*level;
        this.damage = this.damage +2*level;
        this.speed = this.speed + level;
        this.hp = this.hpMax;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Hero{" +
                "xp=" + xp +
                ", level=" + level +
                ", hpMax=" + hpMax +
                ", hp=" + hp +
                ", armor=" + armor +
                ", speed=" + speed +
                ", damage=" + damage +
                ", name='" + name + '\'' +
                '}';
    }
    public void fullHeal(){
        this.isAlive=true;
        this.hp=this.hpMax;
    }
}
