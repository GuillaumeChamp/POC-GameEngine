package Fight;

import Fight.Enemy;
import Fight.Entity;
import Fight.Hero;
import Universal.Game_Scene;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Fight_Scene extends Scene implements Game_Scene {
    //TODO : might hold enemy but not the hero except on static field
    private final Image background = new Image(".//Resources/OutDoor.Level/town_land.png");
    private ArrayList<Enemy> enemies;
    private ArrayList<Hero> heroes;
    private boolean endOfTurn;

    /**
     * Create a new scene to be ready for a fight
     * @param root root of the app
     */
    public Fight_Scene(Group root){
        super(root);
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
                if (e.getMessage().equals("EndOfEntity")){
                    Entity dead = actor.getTarget();
                    entities.remove(dead);
                    if(dead.getClass()==Hero.class) {
                        heroes.remove(dead);
                        if (heroes.isEmpty()) loss();
                    }
                    else {
                        try {
                            enemies.remove(dead); //TODO replace by a loot methode and check if the fight is not finish
                            if (enemies.isEmpty()) win();
                        }catch (Exception ee){
                            ee.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    private void win(){
        //TODO : swap the scene and grant loot and xps
    }
    private void loss(){
        //TODO : swap the scene and go back to an int and maybe lose money
    }
    @Override
    public void Tick(double t) {
        //TODO : make the attack selection
        if (endOfTurn) playTurn();

    }
}
