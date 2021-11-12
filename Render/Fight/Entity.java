package Fight;

import Fight.Alteration.Alteration;
import Universal.AnimatedImage;

import java.util.ArrayList;

public abstract class Entity {
    protected double hpMax;
    protected double hp;
    protected double armor;
    protected double speed;
    protected double damage;
    protected String name;
    protected AnimatedImage skin;
    protected double armorBuff = 1;
    protected double damageBuff = 1;
    protected Entity target;
    protected ArrayList<Alteration> state;
    //TODO add new behaviour to include elemental aspect and speelcasting stat
    public Entity(int hpMax, int armor, int speed, int damage, String name, AnimatedImage skin) {
        this.name = name;
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.armor = armor;
        this.speed = speed;
        this.damage = damage;
        this.skin = new AnimatedImage(name);
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
    protected void RemoveAlteration(){
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
            case "armor" -> appliedBuff(armorBuff, up);
            case "attack" -> appliedBuff(damageBuff, up);
            default -> System.out.println("Error in typo of a buff passed to the function");
        }
    }

    /**
     * Sub function for buff or un-buff an entity according to a specific rule
     * @param type  which multiplier have to be modify
     * @param up    true if it's a buff
     * @throws Exception Throw if the rule can't be follow
     */
    private void appliedBuff(double type,boolean up) throws Exception{
        if (up & type<2){
            type += 0.5;
        }
        if (!up & type>0.5){
            type -= 0.25;
        }
        if((up&type>=2) | (!up&type<=0.5)) throw new Exception("Can't change");
    }

    /**
     * Allow to know if the opponent is dead and kill him
     * @throws Exception only if the entity is dead
     */
    public void died() throws Exception {
        this.hpMax =0;
        throw new Exception("EndOfEntity");
    }

    /**
     * Inflict damage from this entity
     * @param entity target
     * @throws Exception target is Dead
     */
    public void Attack(Entity entity) throws Exception {
        entity.takeDamage(damage*damageBuff);
    }
    public abstract void act() throws Exception;
}
