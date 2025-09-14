package Graphic;

import com.application.graphic.elements.AnimatedImage;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Cropping extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Testing Animated image");
        Group root = new Group();
        Scene scene = new Scene(root);
        int WINDOW = 512;
        int SPRITE_SIZE = 64;
        Canvas canvas = new Canvas(WINDOW, WINDOW);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        AnimatedImage imageTest = new AnimatedImage("player.png",SPRITE_SIZE,SPRITE_SIZE,0);
        root.getChildren().add(canvas);
        stage.setScene(scene);
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gc.drawImage(imageTest.getFrame(currentNanoTime/1000),(WINDOW-SPRITE_SIZE)/2,(WINDOW-SPRITE_SIZE)/2);
            }
        }.start();
        stage.show();
    }

    public static void main(String[] args) {
        Cropping.launch();
    }
}
