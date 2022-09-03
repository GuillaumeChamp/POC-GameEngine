package Graphic.Interface;

import Graphic.Scene.Scene_outside;
import javafx.scene.Scene;

import java.awt.event.KeyEvent;
import java.beans.EventHandler;

public class OptionControls {
    //TODO add scrolling
    public static void addController(Scene_outside scene,Menu menu){
        scene.setOnKeyPressed(e -> {
                    String code = e.getCode().toString();
                    if (!scene.input.contains(code)) {
                        scene.input.add(code);
                        menu.changeOption();
                    }
                    if (code.equals("ENTER"))
                        menu.exitMenu();
                    if (code.equals("SPACE")) menu.performAction();
                }
        );

        scene.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            scene.input.remove(code);
        });
    }

}
