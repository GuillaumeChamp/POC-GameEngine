package com.application.Game.Fight.Alteration;

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
            case poisonI :
                this.name = alteration.poisonI;
                this.duration = 3;
                this.strength = 1;
                break;
            case sleep :
                this.name = alteration.sleep;
                this.duration = 3;
                this.strength = 1;
                break;
            case death :
                this.name = alteration.death;
                this.duration = 9999;
                break;
            default :
                System.out.println("try to create an alteration that not exist");
                this.name=null;
                this.duration= 99999;
                this.strength=0;
                break;
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
