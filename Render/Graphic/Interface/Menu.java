package Graphic.Interface;

import Graphic.Scene.Scene_outside;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Menu {
    public static boolean isFocus;
    private final Scene_outside scene;
    private final Options options;
    public Menu(Scene_outside scene){
        options = new Options();
        this.scene=scene;
        Scene_outside.setIsFocus(false);
        OptionControls.addController(scene,this);
        isFocus = true;
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
        if (action.contains("Exit")) {exitMenu();
        }
    }
    private void printMenu(){
        GraphicsContext gc = scene.Gc();
        gc.setFill(Color.BLUE);
        gc.fillRect(0,0,100,100);
        gc.setFill(Color.BLACK);
        for (int i=0;i<options.options.size();i++){
            if (i==options.selected)
                gc.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC,30));
            else
                gc.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, FontPosture.REGULAR,30));
            gc.fillText(options.options.get(i),0,30+i*30);
        }
    }
    public void exitMenu(){
        isFocus=false;
        Scene_outside.setIsFocus(true);
        scene.addController();
    }

}
