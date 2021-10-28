import Universal.Player;
import OutDoor.Scene_outside;
import Universal.Game_Scene;
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.animation.AnimationTimer;

public class Render extends Application {

    public void start(Stage theStage) {
        theStage.setTitle("Render");
        Player player = new Player();
        Group root = new Group();

        final long startNanoTime = System.nanoTime();
        final long width = 512; //width of the window
        final long height = 512; //height of the window
        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Game_Scene ActiveScene = new Scene_outside(root,player,gc,width,height);
        theStage.setScene((Scene) ActiveScene);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                ActiveScene.Tick(t);
            }
        }.start();
        theStage.show();
    }
}