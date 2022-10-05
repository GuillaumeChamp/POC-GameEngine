package com.application.Graphic.Interface;

import com.application.Graphic.Scene.Game_Scene;
import com.application.Graphic.Scene.Scene_outside;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class Menu implements MenuType {
    public long scrolling;
    private final Scene_outside scene;
    private final Options options;
    private final Controllable caller;
    private final Point2D position;
    public Menu(Scene_outside scene, Controllable caller, Point2D position, Options.MenuType type){
        options = new Options(type);
        this.scene=scene;
        this.caller=caller;
        this.position=position;
        this.addController();
        scene.lastMenu = this;
    }
    public void performController(){
        long scrollingDelay = 150000000;
        if(System.nanoTime()-scrolling<scrollingDelay) return;
        if (Game_Scene.input.contains("UP")) options.changeSelected("DOWN");
        if (Game_Scene.input.contains("DOWN")) options.changeSelected("UP");
        scrolling=System.nanoTime();
    }
    public void performAction(){
        String action = options.options.get(options.selected);
        if (action.contains("Test")) new DialogPrompt(scene,new String[]{"this is a test"});
        if (action.contains("Heroes")) new Menu(scene,this,new Point2D(0,0), Options.MenuType.test);
        if (action.contains("Exit")) exitMenu();
    }

    public Options getContent() {
        return options;
    }

    public Rectangle2D getPosition(double effectivePolice) {
        return new Rectangle2D(position.getX(), position.getY(), 100,options.length()*effectivePolice);
    }

    public void exitMenu(){
        caller.addController();
        caller.exit();
    }

    @Override
    public void addController() {
        Scene_outside.setIsFocus(false);
        OptionControls.addController(scene,this);
    }

    @Override
    public void exit() {
        scene.lastMenu=this;
    }
}
