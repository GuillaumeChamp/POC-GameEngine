package com.application.Graphic.Scene;

import com.application.Graphic.Interface.Controllable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Screen;

import java.util.ArrayList;

public abstract class Game_Scene extends Scene implements Controllable {
    static public double width = Screen.getPrimary().getBounds().getWidth();
    static public double height = Screen.getPrimary().getBounds().getHeight() - 70;
    static protected GraphicsContext gc;
    static protected Canvas canvas;
    static protected boolean isFocus;
    public final static ArrayList<String> input = new ArrayList<>(); //store the keyboard input

    public Game_Scene(Parent parent, Canvas canvas) {
        super(parent);
        if (canvas != null) {
            Game_Scene.canvas = canvas;
            gc = canvas.getGraphicsContext2D();
        }
        this.addResizeable();
    }

    public void Tick(){
        performControl();
    }
    abstract public void performControl();

    public static void setIsFocus(boolean isFocus) {
        Game_Scene.isFocus = isFocus;
    }

    /**
     * Add the resizeable behaviour to the GameScene
     */
    private void addResizeable() {
        this.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            width = (double) newSceneWidth;
            clear();
        });
        this.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            height = (double) newSceneHeight;
            clear();
        });
    }

    protected void clear() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
