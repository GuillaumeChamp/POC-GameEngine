package com.application.Graphic;

import com.application.Game.Universal.PlayerData;
import com.application.Graphic.Scene.Game_Scene;
import com.application.Graphic.Scene.Scene_outside;
import com.application.Sound.BackgroundMusic;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Game extends Application {
    static Canvas canvas;
    private static Game_Scene activeScene;
    public void start(Stage theStage) {
        canvas = new Canvas(Game_Scene.width, Game_Scene.height);
        theStage.setTitle("Render");

        Group root = new Group();
        int borderXSize = 0;
        int borderYSize = 70;

        final double defaultWidth = Screen.getPrimary().getBounds().getWidth()-borderXSize;
        final double defaultHeight = Screen.getPrimary().getBounds().getHeight()-borderYSize;

        root.getChildren().add(canvas);

        activeScene = new Scene_outside(root, PlayerData.getPlayer(),canvas,defaultWidth,defaultHeight);
        theStage.setScene(activeScene);
        BackgroundMusic.playMusique("Out of Hand.wav");
        theStage.setMaximized(true);

        theStage.setOnCloseRequest(e->{
            try {
                PlayerData.saveHeroes();
                PlayerData.save();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                activeScene.Tick();
            }
        }.start();
        theStage.show();
    }
    public static void changeScene(Game_Scene scene){
        activeScene=scene;
    }

    public static void main(String[] args) {
        Game.launch();
    }
}