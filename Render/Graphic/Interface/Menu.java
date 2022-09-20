package Graphic.Interface;

import Graphic.Scene.Game_Scene;
import Graphic.Scene.Scene_outside;
import javafx.geometry.Rectangle2D;

public class Menu implements Controllable {
    public long scrolling;
    private final Scene_outside scene;
    private final Options options;
    private final Controllable caller;
    private final Rectangle2D position;
    public Menu(Scene_outside scene, Controllable caller, Rectangle2D position, Options.MenuType type){
        options = new Options(type);
        this.scene=scene;
        this.caller=caller;
        this.position=position;
        this.addController();
        scene.lastMenu = this;
    }
    public void changeOption(){
        long scrollingDelay = 150000000;
        if(System.nanoTime()-scrolling<scrollingDelay) return;
        if (Game_Scene.input.contains("UP")) options.changeSelected("DOWN");
        if (Game_Scene.input.contains("DOWN")) options.changeSelected("UP");
        scrolling=System.nanoTime();
    }
    public void performAction(){
        String action = options.options.get(options.selected);
        if (action.contains("Test")) System.out.println("Test Menu");
        if (action.contains("Heroes")) new Menu(scene,this,new Rectangle2D(10,10,100,70), Options.MenuType.test);
        if (action.contains("Exit")) exitMenu();
    }

    public Options getOptions() {
        return options;
    }

    public Rectangle2D getPosition() {
        return position;
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
