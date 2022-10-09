package com.application.Graphic.Scene;

import com.application.Game.Level.LevelElements.Layer0.Tile;
import com.application.Game.Universal.Player;
import com.application.Graphic.Game;
import com.application.Graphic.Graphic_Const;
import com.application.Graphic.Interface.DialogPrompt;
import com.application.Graphic.Interface.Menu;
import com.application.Graphic.Interface.MenuType;
import com.application.Graphic.Interface.Options;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Scene_outside extends Game_Scene{
    private final Player player;
    public MenuType lastMenu = null;
    private int lastFight;

    public Scene_outside(Group root, Player player, Canvas canvas, double width, double height){
        super(root, canvas);
        this.player=player;
        lastFight=0;
        Game_Scene.height = height;
        Game_Scene.width = width;
        isFocus=true;
        this.addController();
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
        player.update();
        if (moved) triggerCombat();
    }

    private void triggerCombat(){
        lastFight++;
        int FIGHT_PROTECTION = 50;
        if (lastFight< FIGHT_PROTECTION) return;
        double rng = Math.random();
        if (rng>0.98) Combat();
    }

    private void Combat(){
        Game.changeScene(new Fight_Scene(new Group(),this));
        lastFight=0;
    }

    public void Tick(){
        super.Tick();
        if(lastMenu==null) {
            paintScene();
            return;
        }
        paintMenu();
    }

    @Override
    public void performControl() {
        if (lastMenu==null) {
            walk();
            return;
        }
        lastMenu.performController();
    }

    private void paintMenu() {
        double printLimitX = Graphic_Const.H_TILES_PER_SCREEN*Graphic_Const.TILES_SIZE;
        double printLimitY = Graphic_Const.V_TILES_PER_SCREEN*Graphic_Const.TILES_SIZE;
        //Calculate ratio to allow resize
        double xRatio = width/ printLimitX;
        double yRatio = height/ printLimitY;
        paintScene();
        int POLICE_SIZE=18;
        double effectivePolice = POLICE_SIZE*Math.max(xRatio,yRatio);
        Rectangle2D TextFrame = lastMenu.getPosition(effectivePolice);

        gc.setFill(Color.BLUE);
        gc.fillRect(TextFrame.getMinX()*xRatio,TextFrame.getMinY()*yRatio, TextFrame.getWidth()*xRatio,TextFrame.getHeight());
        gc.setFill(Color.BLACK);
        if (lastMenu.getClass()==Menu.class){
            Options options = (Options) lastMenu.getContent();
            for (int i=0;i<options.options.size();i++){
                if (i==options.getSelected())
                    gc.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR,effectivePolice));
                else
                    gc.setFont(Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR,effectivePolice));
                gc.fillText(options.options.get(i), TextFrame.getMinX()*xRatio, (TextFrame.getMinY()*yRatio+effectivePolice*(i+1)));
            }
        }
        if (lastMenu.getClass()== DialogPrompt.class){
            String content = (String) lastMenu.getContent();
            gc.fillText(content,TextFrame.getMinX()*xRatio,(TextFrame.getMinY()*yRatio+effectivePolice));
        }
    }

    public void paintScene(){
        double t = System.nanoTime() / 1000000000.0;
        int tileSize = Graphic_Const.TILES_SIZE;
        int playerSize = Graphic_Const.PLAYER_TILE_SIZE;
        double printLimitX = Graphic_Const.H_TILES_PER_SCREEN*tileSize;
        double printLimitY = Graphic_Const.V_TILES_PER_SCREEN*tileSize;
        //Calculate ratio to allow resize
        double xRatio = width/ printLimitX;
        double yRatio = height/ printLimitY;

        double offSetLandX = player.skin.getPositionX()-(printLimitX/2)-playerSize*xRatio/2;
        double offSetLandY = player.skin.getPositionY()-(printLimitY/2)-playerSize*yRatio/2;

        if (offSetLandX <= 0) offSetLandX = 0;
        if (offSetLandX >= player.location.getSizeX()-printLimitX) offSetLandX = player.location.getSizeX()-printLimitX;
        if (offSetLandY < 0) offSetLandY = 0;
        if (offSetLandY >= player.location.getSizeY()-printLimitY) offSetLandY = player.location.getSizeY()-printLimitY;

        //gc.drawImage(player.location.getBackground(),offSetLandX,offSetLandY, printLimitX, printLimitY,0,0, printLimitX*xRatio,yRatio*printLimitY);
        Tile[][] tiles = player.location.getTiles();
        for(int i=0;i<tiles.length;i++)
            for (int j=0;j<tiles[0].length;j++)
                gc.drawImage(tiles[i][j].getSkin(), i * tileSize * xRatio-offSetLandX*xRatio, j * tileSize * yRatio-offSetLandY*yRatio, tileSize * xRatio, tileSize * yRatio);
        gc.drawImage(player.skin.getFrame(t), (player.skin.getPositionX() - offSetLandX)*xRatio, (player.skin.getPositionY()-offSetLandY)*yRatio,playerSize*xRatio*tileSize,playerSize*yRatio*tileSize);
    }

    @Override
    protected void clear() {
        paintScene();
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
                        new Menu(this,this,new Point2D(0,0), Options.MenuType.main);
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
