package Graphic.Scene;

import Game.Fight.Enemy;
import Game.Fight.Entity;
import Game.Fight.Hero;
import Graphic.Elements.AnimatedImage;
import Graphic.Game;
import Game.Universal.Stuff.Inventory;
import Game.Universal.Stuff.Item;
import javafx.scene.Group;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Fight_Scene extends Game_Scene {
    //TODO : might hold enemy but not the hero except on static field

    private final Image background = new Image("Level//town_land.png");
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<Hero> heroes= new ArrayList<>();
    private boolean endOfTurn;
    private final ArrayList<Item> loots = new ArrayList<>();
    private double xpearn = 0;
    private final Scene_outside back;

    /**
     * Create a new scene to be ready for a fight
     * @param root root of the app
     */
    public Fight_Scene(Group root,Scene_outside back){
        super(root,null);
        this.back=back;
        int rand = (int) (Math.ceil((Math.random()*100-1)/25));
        heroes.add(new Hero(100,10,10,10,"hero",new AnimatedImage("Skin//player.png",64,64,0)));
        for (int i =0;i<rand;i++)
            enemies.add(new Enemy(100,10,10,10,"chou",new AnimatedImage("Skin//player.png",64,64,0),4,null));
        //TODO make a loader of hero
        //TODO read the list of enemy and load them
    }

    private Entity determineOrder(ArrayList<Entity> entities){
        Entity first = entities.get(0);
        for (Entity e: entities) {
            if (e.getSpeed()> first.getSpeed()) first = e;
        }
        return first;
    }

    private void playTurn(){
        endOfTurn = false;
        ArrayList<Entity> entities = new ArrayList<>();
        for(Enemy e: enemies) e.SelectTarget(heroes);
        entities.addAll(enemies);
        entities.addAll(heroes);
        while (!entities.isEmpty()){
            Entity actor = determineOrder(entities);
            entities.remove(actor);
            try {
                actor.act();
            }catch (Exception e){
                if (e.getMessage().equals("EndOfEntity")) deadHandler(actor,entities);
            }
        }
        for (Entity e : heroes) e.RemoveAlteration();
        for (Entity e : enemies) e.RemoveAlteration();
    }
    private void win(){
        int number = heroes.size();
        for (Hero hero : heroes) hero.grantXp(xpearn/number);
        for (Item item : loots) Inventory.add(item);
        Game.changeScene(back);
    }
    private void loss(){
        Game.changeScene(back);
        //TODO : Set map and location tp game over
    }
    @Override
    public void Tick() {
        super.Tick();
        draw();
        xpearn++;
        if (xpearn >800) win(); //TODO Remove test
        if (endOfTurn) playTurn();
    }
    private void draw(){
        //Ratio of entity pictures
        double xRatio = 0.1*width;
        double yRatio = 0.1*height;
        double ratio = Math.min(xRatio,yRatio);
        double enemiesPosX = 0;
        double enemiesPosY = 0;
        double heroesPosX = width/2;
        double heroesPosY = height - yRatio;

        gc.drawImage(background,0,0,back.getWidth(), back.getHeight());
        double t = System.nanoTime() / 1000000000.0;
        double enemyOffSet = 0;
        double heroOffSet = 0;
        for(Enemy e : enemies){
            gc.drawImage(e.getSkin(t),enemiesPosX+enemyOffSet,enemiesPosY,ratio,ratio);
            enemyOffSet = enemyOffSet + width/5;
        }
        for(Hero h : heroes){
            gc.drawImage(h.getSkin(t),heroesPosX,heroesPosY,ratio,ratio);
            heroOffSet = heroOffSet +width/5;
        }
    }

    @Override
    public void performControl() {

    }

    @Override
    public void addController() {
        //TODO : load control map
    }

    @Override
    public void exit() {
    }

    /**
     * Sub-function of playTurn use to handle a dead entity
     * @param actor killer of the entity (the one who target the cause of the exception)
     * @param entities list of the waiting entities
     */
    private void deadHandler(Entity actor, ArrayList<Entity> entities){
        Entity dead = actor.getTarget();
        entities.remove(dead);
        if(dead.getClass()==Hero.class) {
            heroes.remove(dead);
            if (heroes.isEmpty()) loss();
        }
        if (dead.getClass()==Enemy.class) {
            try {
                enemies.remove(dead);
                loots.addAll(((Enemy) dead).loot());
                xpearn += ((Enemy) dead).xp;
                if (enemies.isEmpty()) win();
            }catch (Exception ee){
                ee.printStackTrace();
            }
        }
    }
}