package Graphic.Scene;

import Game.Universal.Player;
import Graphic.Graphic_Const;
import Graphic.Interface.Menu;
import Graphic.Interface.Options;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Scene_outside extends Game_Scene{
    private final Player player;
    public Menu lastMenu = null;
    public final ArrayList<String> input = new ArrayList<>(); //store the keyboard input

    public Scene_outside(Group root, Player player, Canvas canvas, double width, double height){
        super(root, canvas);
        this.player=player;
        Game_Scene.height = height;
        Game_Scene.width = width;
        isFocus=true;
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
    public GraphicsContext Gc(){
        return gc;
    }

    public void Tick(){
        super.Tick();
    }

    @Override
    public void performControl() {
        if (lastMenu==null) {
            walk();
            paint();
            return;
        }
        paintMenu(lastMenu);
    }

    private void paintMenu(Menu lastMenu) {
        lastMenu.printMenu();
    }

    public void paint(){
        double t = System.nanoTime() / 1000000000.0;
        double printLimitX = Graphic_Const.H_TILES_PER_SCREEN*Graphic_Const.H_TILES_SIZE;
        double printLimitY = Graphic_Const.V_TILES_PER_SCREEN*Graphic_Const.V_TILES_SIZE;
        //Calculate ratio to allow resize
        double xRatio = width/ printLimitX;
        double yRatio = height/ printLimitY;

        double offSetLandX = player.skin.getPositionX() - (printLimitX/2)-Graphic_Const.H_PLAYER_TILE_SIZE*xRatio/2;
        double offSetLandY = player.skin.getPositionY() - (printLimitY/2)-Graphic_Const.V_PLAYER_TILE_SIZE*yRatio/2;


        if (offSetLandX < 0) offSetLandX = 0;
        if (offSetLandX >= player.location.getSizeX()-printLimitX-Graphic_Const.H_PLAYER_TILE_SIZE) offSetLandX = player.location.getSizeX()-printLimitX-Graphic_Const.H_PLAYER_TILE_SIZE;
        if (offSetLandY < 0) offSetLandY = 0;
        if (offSetLandY >= player.location.getSizeY()-printLimitY-Graphic_Const.V_PLAYER_TILE_SIZE) offSetLandY = player.location.getSizeY()-printLimitY-Graphic_Const.V_PLAYER_TILE_SIZE;

        gc.drawImage(player.location.getBackground(),offSetLandX,offSetLandY, printLimitX, printLimitY,0,0, printLimitX*xRatio,yRatio*printLimitY);

        gc.drawImage(player.skin.getFrame(t), (player.skin.getPositionX() - offSetLandX)*xRatio, (player.skin.getPositionY()-offSetLandY)*yRatio,Graphic_Const.H_PLAYER_TILE_SIZE*xRatio*Graphic_Const.H_TILES_SIZE,Graphic_Const.V_PLAYER_TILE_SIZE*yRatio*Graphic_Const.V_TILES_SIZE);
    }

    @Override
    protected void clear() {
        paint();
    }

    @Override
    public void addController() {
        this.setOnKeyPressed(e -> {
                    String code = e.getCode().toString();
                    if (!input.contains(code))
                        input.add(code);
                    if (code.equals("R"))
                        System.out.println(player.skin.getPositionX() +"  "+ player.skin.getPositionY());
                    if (code.equals("ENTER"))
                        new Menu(this,this,new Rectangle2D(0,0,100,70), Options.MenuType.main);
                }
        );

        this.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            input.remove(code);
        });
    }

    @Override
    public void exit() {
        lastMenu = null;
    }
}
