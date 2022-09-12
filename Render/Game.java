import Game.Universal.Player;
import Graphic.Scene.Scene_outside;
import Graphic.Scene.Game_Scene;
import Sound.BackgroundMusic;
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.animation.AnimationTimer;

public class Game extends Application {
    static Canvas canvas;
    public void start(Stage theStage) {
        canvas = new Canvas(Game_Scene.width,Game_Scene.height);
        theStage.setTitle("Render");
        Player player = new Player();
        Group root = new Group();
        int borderXSize = 0;
        int borderYSize = 70;

        final double defaultWidth = Screen.getPrimary().getBounds().getWidth()-borderXSize;
        final double defaultHeight = Screen.getPrimary().getBounds().getHeight()-borderYSize;

        root.getChildren().add(canvas);

        Game_Scene ActiveScene = new Scene_outside(root,player,canvas,defaultWidth,defaultHeight);
        theStage.setScene(ActiveScene);
        BackgroundMusic.playMusique("Out of Hand.wav");
        theStage.setMaximized(true);


        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                ActiveScene.Tick();
            }
        }.start();
        theStage.show();
    }

    public static void main(String[] args) {
        Game.launch();
    }
}