package Graphic.Interface;

import Graphic.Scene.Scene_outside;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Menu implements Controllable {
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
        printMenu();
    }
    public void changeOption(){
        if (scene.input.contains("UP")) options.changeSelected("DOWN");
        if (scene.input.contains("DOWN")) options.changeSelected("UP");
        printMenu();
    }
    public void performAction(){
        String action = options.options.get(options.selected);
        if (action.contains("Test")) System.out.println("Test Menu");
        if (action.contains("Heroes")) new Menu(scene,this,new Rectangle2D(10,10,100,70), Options.MenuType.test);
        if (action.contains("Exit")) exitMenu();
    }
    //TODO : rework printing for responsive design
    private void printMenu(){
        int POLICE_SIZE=30;
        //clear the old one
        scene.paint();
        //paint the new one
        GraphicsContext gc = scene.Gc();
        gc.setFill(Color.BLUE);
        gc.fillRect(position.getMinX(),position.getMinY(), position.getWidth(),position.getHeight());
        gc.setFill(Color.BLACK);
        for (int i=0;i<options.options.size();i++){
            if (i==options.selected)
                gc.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,POLICE_SIZE));
            else
                gc.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.REGULAR,POLICE_SIZE));
            gc.fillText(options.options.get(i), position.getMinX(), position.getMinY()+POLICE_SIZE*(i+1));
        }
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
        printMenu();
    }
}
