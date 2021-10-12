package Figth;

import javafx.scene.image.Image;

public abstract class Entity {
    protected double hp;
    protected double armor;
    protected double speed;
    protected double damage;
    protected String name;
    protected Image[] skin;
    protected double armorBuff = 1;
    protected double damageBuff = 1;

    public Entity(int hp, int armor, int speed, int damage, String name, Image[] skin) {
        this.name = name;
        this.hp = hp;
        this.armor = armor;
        this.speed = speed;
        this.damage = damage;
        this.skin = skin;
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
        hp = hp - (damage)/(armor*armorBuff);
        if (hp<1) {
            this.died();
        }
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
     * Sub function for buff or unbuff an entity according to a specific rule
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
     * Allow to know if the opponent is dead
     * @throws Exception only if the entity is dead
     */
    public void died() throws Exception {
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
}
