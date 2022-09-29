package com.upgradeTheQuest.Game.Fight.Alteration;

public class Alteration {
    public enum alteration {poisonI,sleep,death}
    alteration name;
    int duration;
    int time=0;
    int strength;

    /**
     * Create alteration based
     * @param name must be poisonI, sleep, death
     */
    public Alteration(alteration name){
        switch (name) {
            case poisonI -> {
                this.name = alteration.poisonI;
                this.duration = 3;
                this.strength = 1;
            }
            case sleep -> {
                this.name = alteration.sleep;
                this.duration = 3;
                this.strength = 1;
            }
            case death ->{
                this.name = alteration.death;
                this.duration = 9999;
            }
            default -> {
                System.out.println("try to create an alteration that not exist");
                this.name=null;
                this.duration= 99999;
                this.strength=0;
            }
        }
    }
    public void addTurn(){
        if (!this.name.equals(alteration.death))
        this.time++;
    }
    public boolean checkEnd(){
        return  (time >= duration);
    }
}
