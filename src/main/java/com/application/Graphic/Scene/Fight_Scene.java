package com.application.Graphic.Scene;

import com.application.Game.Fight.Enemy;
import com.application.Game.Fight.Entity;
import com.application.Game.Fight.FightActions;
import com.application.Game.Fight.Hero;
import com.application.Game.Universal.PlayerData;
import com.application.Game.Universal.Stuff.Inventory;
import com.application.Game.Universal.Stuff.Item;
import com.application.Graphic.Elements.AnimatedImage;
import com.application.Graphic.Game;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

//TODO : Rebuild to delete scene aspect
public class Fight_Scene extends Game_Scene {
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
        int rand = 1+ (int) (Math.ceil((Math.random()*100-1)/50));
        heroes = PlayerData.getHeroes();
        for (int i =0;i<rand;i++)
            enemies.add(new Enemy(16,1,8,10,"chou",new AnimatedImage("player.png",64,64,0),10,null));
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
        entitiesToPlay.removeIf(entity -> !entity.isAlive());
        while (!entitiesToPlay.isEmpty()){
            Entity actor = determineOrder(entitiesToPlay);
            entitiesToPlay.remove(actor);
                if(actor.performDeterminedAction()) deadHandler(actor,entitiesToPlay);
        }
        for (Entity e : heroes) e.RemoveAlteration();
        for (Entity e : enemies) e.RemoveAlteration();
    }
    private void win(){
        int number = heroes.size();
        for (Hero hero : heroes) hero.grantXp((int) (xpEarn /number));
        if (!loots.isEmpty())for (Item item : loots) Inventory.add(item);
        back.addController();
        Game.changeScene(back);
    }
    private void loss(){
        System.out.println("defeat no xp acquired");
        for (Hero h : heroes) h.fullHeal();
        Game.changeScene(back);
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
            if (e.isAlive()) {
                gc.drawImage(e.getSkin(t),enemiesPosX+enemyOffSet,enemiesPosY,ratio,ratio);
                //draw health bar
                setColor(e);
                gc.fillRect(enemiesPosX+enemyOffSet,enemiesPosY+ratio*1.1,e.getPercentHp()*ratio,ratio/4);
            }

            enemyOffSet = enemyOffSet + width/5;
        }
        for(Hero h : heroes){
            if (h.isAlive()) {
                gc.drawImage(h.getSkin(t), heroesPosX, heroesPosY, ratio, ratio);
                setColor(h);
                gc.fillRect(heroesPosX + heroOffSet, heroesPosY - ratio * 1.1, h.getPercentHp() * ratio, ratio / 4);
            }
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
                            h.defineAttack(FightActions.ATTACK,enemies.stream().filter(Entity::isAlive).findFirst().get());
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
     * @param actor killer of the entity
     * @param entities list of the waiting entities
     */
    private void deadHandler(Entity actor, ArrayList<Entity> entities){
        Entity dead = actor.getTarget();
        entities.remove(dead);
        if(dead.getClass()==Hero.class) {
            for (Hero h : heroes) {
                if (h.isAlive()) return;
            }
            loss();
        }
        if (dead.getClass()==Enemy.class) {
            try {
                loots.add(((Enemy) dead).loot());
                xpEarn = xpEarn + ((Enemy) dead).xp;
                for (Enemy e: enemies) {
                    if (e.isAlive()) return;
                }
                win();
            }catch (Exception ee){
                ee.printStackTrace();
            }
        }
    }
}