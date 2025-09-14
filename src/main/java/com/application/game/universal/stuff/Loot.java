package com.application.game.universal.stuff;

public class Loot extends Item{
    private final double dropRate;
    //Fixme : need to change the loot class maybe use an interface loot.able
    public Loot(double dropRate) {
        this.dropRate = dropRate;
    }
    public boolean tryToLoot(double rng){
        return (rng<dropRate);
    }
}
