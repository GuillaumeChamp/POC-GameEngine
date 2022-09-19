package Graphic.Scene;

import Game.Fight.Enemy;
import Game.Fight.Entity;
import Game.Fight.FightActions;
import Game.Fight.Hero;
import Game.Universal.PlayerData;
import Graphic.Elements.AnimatedImage;
import Graphic.Game;
import Game.Universal.Stuff.Inventory;
import Game.Universal.Stuff.Item;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

//TODO : Rebuild to delete scene aspect
public class Fight_Scene extends Game_Scene {
    //TODO : might hold enemy but not the hero except on static field
    private final Image background = new Image("Level//town_land.png");
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<Hero> heroes;
    private boolean endOfTurn;
    private final ArrayList<Item> loots = new ArrayList<>();
    private double xpEarn = 0;
    private final Scene_outside back;

    /**
     * Create a new scene to be ready for a fight
     * @param root root of the app
     */
    public Fight_Scene(Group root,Scene_outside back){
        super(root,null);
        this.back=back;
        int rand = (int) (Math.ceil((Math.random()*100-1)/25));
        heroes = PlayerData.getHeroes();
        for (int i =0;i<rand;i++)
            enemies.add(new Enemy(30,1,10,10,"chou",new AnimatedImage("Skin//player.png",64,64,0),4,null));
        for (Hero h : heroes) h.setSkin();
        //TODO read the list of enemy and load them
        this.addController();
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
        ArrayList<Entity> entitiesToPlay = new ArrayList<>();
        for(Enemy e: enemies) e.SelectAttack(heroes,enemies);
        entitiesToPlay.addAll(enemies);
        entitiesToPlay.addAll(heroes);
        while (!entitiesToPlay.isEmpty()){
            Entity actor = determineOrder(entitiesToPlay);
            entitiesToPlay.remove(actor);
            try {
                actor.performDeterminedAction();
            }catch (Exception e){
                if (e.getMessage().equals("EndOfEntity")) deadHandler(actor,entitiesToPlay);
            }
        }
        for (Entity e : heroes) e.RemoveAlteration();
        for (Entity e : enemies) e.RemoveAlteration();
    }
    private void win(){
        int number = heroes.size();
        for (Hero hero : heroes) hero.grantXp(xpEarn /number);
        if (!loots.isEmpty())for (Item item : loots) Inventory.add(item);
        back.addController();
        Game.changeScene(back);
    }
    private void loss(){
        Game.changeScene(back);
        PlayerData.addHero("1");
    }
    @Override
    public void Tick() {
        super.Tick();
        draw();
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
            //draw health bar
            setColor(e);
            gc.fillRect(enemiesPosX+enemyOffSet,enemiesPosY+ratio*1.1,e.getPercentHp()*ratio,ratio/4);
            enemyOffSet = enemyOffSet + width/5;
        }
        for(Hero h : heroes){
            gc.drawImage(h.getSkin(t),heroesPosX,heroesPosY,ratio,ratio);
            //draw health bar
            setColor(h);
            gc.fillRect(heroesPosX+heroOffSet,heroesPosY-ratio*1.1,h.getPercentHp()*ratio,ratio/4);
            heroOffSet = heroOffSet +width/5;
        }
    }
    private void setColor(Entity entity){
        if (entity.getPercentHp()>0.50) {
            gc.setFill(Color.GREEN);
            return;
        }
        if (entity.getPercentHp()>0.30) {
            gc.setFill(Color.ORANGE);
            return;
        }
        gc.setFill(Color.RED);
    }

    @Override
    public void performControl() {

    }

    @Override
    public void addController() {
        back.setOnKeyPressed(e -> {
                    String code = e.getCode().toString();
                    if (!input.contains(code))
                        input.add(code);
                    if (code.equals("ENTER")){
                        for (Hero h : heroes){
                            h.defineAttack(FightActions.ATTACK,enemies.get(0));
                        }
                        endOfTurn=true;
                    }

                }
        );

        back.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            input.remove(code);
        });
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
                loots.add(((Enemy) dead).loot());
                xpEarn += ((Enemy) dead).xp;
                if (enemies.isEmpty()) win();
            }catch (Exception ee){
                ee.printStackTrace();
            }
        }
    }
}