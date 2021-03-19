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

        public void start(Stage theStage)
        {
            theStage.setTitle( "Render" );
            ArrayList<String> input = new ArrayList<>(); //store the keyboard input

            Group root = new Group();
            Scene theScene = new Scene( root );
            theStage.setScene( theScene );
            final long startNanoTime = System.nanoTime();

            Canvas canvas = new Canvas( 512, 512 );
            root.getChildren().add( canvas );

            GraphicsContext gc = canvas.getGraphicsContext2D();
            /*
            Image[] ufo_frames = new Image[6]; //40*40
            Image[] boom = new Image[8];
            boom[0] = new Image("explosion_0.png");
            boom[1] = new Image("explosion_1.png");
            boom[2] = new Image("explosion_2.png");
            boom[3] = new Image("explosion_3.png");
            boom[4] = new Image("explosion_4.png");
            boom[5] = new Image("explosion_5.png");
            boom[6] = new Image("explosion_6.png");
            boom[7] = new Image("explosion_7.png");
            ufo_frames[0] = new Image( "ufo_0.png" );
            ufo_frames[1] = new Image( "ufo_1.png" );
            ufo_frames[2] = new Image( "ufo_2.png" );
            ufo_frames[3] = new Image( "ufo_3.png" );
            ufo_frames[4] = new Image( "ufo_4.png" );
            ufo_frames[5] = new Image( "ufo_5.png" );
            Image night = new Image("starNight.png");
            Image city = new Image("transparentNightSky.png");//512*1756
            MovingAnimatedImage ufo=new MovingAnimatedImage(ufo_frames,500,250,0.1,40,40);
            MovingAnimatedImage Dubai_Tower = new MovingAnimatedImage(null,950,20,0.5,50,330);
            MovingAnimatedImage Sea = new MovingAnimatedImage(null,0,458,0.5,1712,512);
            MovingAnimatedImage buildings = new MovingAnimatedImage(null,0,231,0.5,561,223);

            ArrayList<MovingAnimatedImage> hitable = new ArrayList<>();
            hitable.add(Dubai_Tower);
            hitable.add(Sea);
            hitable.add(buildings);

            theScene.setOnKeyPressed(e -> {
                String code = e.getCode().toString();
                if ( !input.contains(code) )
                    input.add( code );
                if (code.equals("S")){
                    try {java.io.FileOutputStream save = new FileOutputStream("ufosave");
                        ObjectOutputStream oos= new ObjectOutputStream(save);
                        oos.writeObject(ufo);
                        save.close();
                        System.out.println("saved");
                    }
                    catch (Exception ae) {System.out.println("error state not saved");}
                }
                if (code.equals(("R"))){
                    try {
                        FileInputStream fis = new FileInputStream("ufosave");
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        MovingAnimatedImage ufo2 =(MovingAnimatedImage) ois.readObject();
                        ois.close();
                        ufo.setState(ufo2);
                        System.out.println("Restore");}
                    catch (Exception ae) {System.out.println("Mission fail");};
                }

            });
            theScene.setOnKeyReleased(e -> {
                String code = e.getCode().toString();
                input.remove( code );
            });
            new AnimationTimer()
            {
                public void handle(long currentNanoTime)
                {
                    double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                    ufo.setF(0,0);
                    if (input.contains("LEFT")) ufo.addF(-10,0);
                    if (input.contains("RIGHT")) ufo.addF(10,0);
                    if (input.contains("UP")) ufo.addF(0,-10);
                    if (input.contains("DOWN")) ufo.addF(0,10);

                    int X = (int) ufo.getPositionX()-256;
                    if (X < 0) X=0;
                    else if (X > 1200) X =1200;
                    ufo.update();

                    // background image clears canvas;
                    gc.drawImage(night, -X/6,0);
                    gc.drawImage(city,X, 0,512,512,0,0,512,512);
                    ufo.setFrames(ufo_frames);
                    //collision detection : make the ufo explode
                    for (MovingAnimatedImage wall : hitable)
                        if (wall.if_hit(ufo.getPositionX(),ufo.getPositionY()))
                            ufo.setFrames(boom);
                    //else
                    //  ufo.setFrames(ufo_frames);
                    gc.drawImage(ufo.getFrame(t),(int) ufo.getPositionX()-X,(int) ufo.getPositionY());
                }
            }.start();
            theStage.show();
             */
        }
}