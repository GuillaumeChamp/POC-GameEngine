import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.animation.AnimationTimer;
import java.io.*;
import java.util.ArrayList;

public class Render extends Application {

    public void start(Stage theStage) {
        theStage.setTitle("Render");
        ArrayList<String> input = new ArrayList<>(); //store the keyboard input

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        final long startNanoTime = System.nanoTime();
        final long width = 512; //width of the window
        final long height = 512; //height of the window

        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Player player = new Player();
        theScene.setOnKeyPressed(e -> {
                    String code = e.getCode().toString();
                    if (!input.contains(code))
                        input.add(code);
                    if (code.equals("R")) System.out.println(player.skin.getPositionX() +"  "+ player.skin.getPositionY());
                }
        );

        theScene.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            input.remove(code);
        });

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                player.skin.setVelocity(0,0);
                if (input.contains("LEFT")) player.skin.addVelocity(-10, 0);
                if (input.contains("RIGHT")) player.skin.addVelocity(10, 0);
                if (input.contains("UP")) player.skin.addVelocity(0, -10);
                if (input.contains("DOWN")) player.skin.addVelocity(0, 10);

                player.skin.update();

                double offsetlandX = player.skin.getPositionX() - (width >> 1);
                double offsetlandY = player.skin.getPositionY() - (height >> 1);
                if (offsetlandX < 0) offsetlandX = 0;
                if (offsetlandX > player.location.getSizeX()-512) offsetlandX = player.location.getSizeX()-width;
                if (offsetlandY < 0) offsetlandY = 0;
                if (offsetlandY > player.location.getSizeY()-512) offsetlandY = player.location.getSizeY()-height;

                // background image clears canvas;
                gc.drawImage(player.location.getBackground(),offsetlandX,offsetlandY,width,height,0,0,width,height);
                gc.drawImage(player.skin.getFrame(t), (int) player.skin.getPositionX() - offsetlandX, (int) player.skin.getPositionY()-offsetlandY);
            }
        }.start();
        theStage.show();
    }
}