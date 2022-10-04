package com.application.Game.Fight;

import com.application.Game.Fight.Alteration.Alteration;
import com.application.Graphic.Elements.AnimatedImage;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Entity implements Serializable {
    protected double hpMax;
    protected double hp;
    protected double armor;
    protected double speed;
    protected double damage;
    protected String name;
    protected transient AnimatedImage skin;
    protected transient double armorBuff = 1;
    protected transient double damageBuff = 1;
    protected transient Entity target;
    protected transient FightActions action;
    protected ArrayList<Alteration> state;
    protected boolean isAlive = true;
    //TODO add new behaviour to include elemental aspect and speelcasting stat
    public Entity(int hpMax, int armor, int speed, int damage, String name, AnimatedImage skin) {
        this.name = name;
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.armor = armor;
        this.speed = speed;
        this.damage = damage;
        this.skin = skin;
    }

    public Entity getTarget() {
        return target;
    }

    public void inflictAlteration(Alteration.alteration a){
        Alteration alt = new Alteration(a);
        if (state.contains(alt)) state.add(alt); //Fixme : real check
    }
    protected void stateHandler(){
        //TODO State handler
        for (Alteration a :state) {
                a.addTurn();
        }
    }

    /**
     * Need to be call at the end of the turn
     */
    public void RemoveAlteration(){
        if (state==null) return;
        state.removeIf(Alteration::checkEnd);
    }
    /**
     * Use it to determine who attack first
     * @return the speed value
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * inflict the damage to this entity and check if the target is still alive
     * @param damage amount of damage
     * @throws Exception I am dead now
     */
    public void takeDamage(double damage) throws Exception {
        if (damage<0) return;
        hp = hp - (damage)/(armor*armorBuff);
        if (hp <1) {
            this.died();
        }
    }

    public void heal(double amount) {
        if (amount<0) return;
        hp = hp + amount;
        if(hp > hpMax) hp = hpMax;
    }

    /**
     * Applied buff to this entity base on the classic rule
     * Need to handle an Exception to print an in-game message to say stat can't go upper or lower
     * @param stat  armor or attack only
     * @param up    true if it is a buff
     * @throws Exception In case of limits are reached
     */
    public void buff(String stat,boolean up) throws Exception {
        switch (stat) {
            case "armor" :
                appliedBuff(armorBuff, up);
                break;
            case "attack" :
                appliedBuff(damageBuff, up);
                break;
            default :
                System.out.println("Error in typo of a buff passed to the function");
                break;
        }
    }

    /**
     * Sub function for buff or un-buff an entity according to a specific rule
     * @param type  which multiplier have to be modify
     * @param up    true if it's a buff
     * @throws GameException Throw if the rule can't be follow
     */
    private void appliedBuff(double type,boolean up) throws GameException{
        if (up & type<2){
            type += 0.5;
        }
        if (!up & type>0.5){
            type -= 0.25;
        }
        if((up&type>=2) | (!up&type<=0.5)) throw new GameException("Can't change");
    }

    /**
     * Allow to know if the opponent is dead and kill him
     * @throws GameException only if the entity is dead
     */
    public void died() throws GameException {
        this.hp =0;
        isAlive=false;
        throw new GameException("EndOfEntity");
    }

    /**
     * Attack an entity
     * @param entity targeted entitiy
     * @return true if the target died
     */
    public boolean attack(Entity entity) {
        try {
            entity.takeDamage(damage*damageBuff);
        } catch (Exception e) {
            return true;
        }
        return false;
    }
    public abstract boolean performDeterminedAction();

    public Image getSkin(double t) {
        return skin.getFrame(t);
    }

    public double getPercentHp() {
        return hp/hpMax;
    }

    protected void endOfAttack(){
        this.target=null;
        this.action =null;
    }
    public String getName(){
        return this.name;
    }

    public boolean isAlive() {
        return isAlive;
    }
    public void debuff(){
        this.damageBuff=1;
        this.armorBuff=1;
    }
}
