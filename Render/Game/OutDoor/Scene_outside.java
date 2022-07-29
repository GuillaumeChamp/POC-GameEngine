package Game.OutDoor;
import Game.Universal.Game_Scene;
import Game.Universal.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Scene_outside extends Scene implements Game_Scene {
    private final Player player;
    private final ArrayList<String> input = new ArrayList<>(); //store the keyboard input
    private final GraphicsContext gc;

    final long width; //width of the window
    final long height; //height of the window

    public Scene_outside(Group root, Player player,GraphicsContext gc,long width,long height){
        super(root);
        this.player=player;
        this.gc=gc;
        this.height= height;
        this.width=width;

        this.setOnKeyPressed(e -> {
                    String code = e.getCode().toString();
                    if (!input.contains(code))
                        input.add(code);
                    if (code.equals("R")) System.out.println(player.skin.getPositionX() +"  "+ player.skin.getPositionY());
                }
        );

        this.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            input.remove(code);
        });
    }
    private void walk(){
        player.skin.setVelocity(0,0);
        boolean moved = false;
        if (input.contains("LEFT")) {
            player.skin.addVelocity(-10, 0);
            moved = true;
        }
        if (input.contains("RIGHT")) {
            player.skin.addVelocity(10, 0);
            moved = true;
        }
        if (input.contains("UP")) {
            player.skin.addVelocity(0, -10);
            moved = true;
        }
        if (input.contains("DOWN")) {
            player.skin.addVelocity(0, 10);
            moved = true;
        }
        player.skin.update();
        if (moved) triggerCombat();
    }

    private void triggerCombat(){
        if (!player.location.isPeaceful()){
            double rng = Math.random();
            if (rng>0.95) Combat();
        }
    }

    private void Combat(){
        //TODO : Switch to Fight
        System.out.println("new Fight");
    }

    @Override
    public void Tick(double t){
        walk();
        double offSetLandX = player.skin.getPositionX() - (width >> 1);
        double offSetLandY = player.skin.getPositionY() - (height >> 1);
        if (offSetLandX < 0) offSetLandX = 0;
        if (offSetLandX > player.location.getSizeX()-512) offSetLandX = player.location.getSizeX()-width;
        if (offSetLandY < 0) offSetLandY = 0;
        if (offSetLandY > player.location.getSizeY()-512) offSetLandY = player.location.getSizeY()-height;

        gc.drawImage(player.location.getBackground(),offSetLandX,offSetLandY,width,height,0,0,width,height);
        gc.drawImage(player.skin.getFrame(t), (int) player.skin.getPositionX() - offSetLandX, (int) player.skin.getPositionY()-offSetLandY);
    }
}
