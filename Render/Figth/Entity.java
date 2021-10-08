package Figth;

import javafx.scene.image.Image;

public abstract class Entity {
    protected int hp;
    protected int armor;
    protected int speed;
    protected int damage;
    protected String name;
    protected Image[] skin;

    public Entity(int hp, int armor, int speed, int damage, String name, Image[] skin) {
        this.name = name;
        this.hp = hp;
        this.armor = armor;
        this.speed = speed;
        this.damage = damage;
        this.skin = skin;
    }

    public abstract void Attack();

    public void takeDamage(int damage) throws Exception {
        hp = hp - (damage)/armor;
        if (hp<1) {
            this.died();
        }
    }

    public void died() throws Exception {
        throw new Exception("EndOfEntity");
    }

    public abstract void Attack(Entity entity) throws Exception;
}
