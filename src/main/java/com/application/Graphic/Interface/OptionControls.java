package com.application.Graphic.Interface;

import com.application.Graphic.Scene.Game_Scene;
import com.application.Graphic.Scene.Scene_outside;

public class OptionControls {
    public static void addController(Scene_outside scene, Menu menu){
        scene.setOnKeyPressed(e -> {
                    String code = e.getCode().toString();
                    if (!Game_Scene.input.contains(code)) {
                        Game_Scene.input.add(code);
                        menu.performController();
                        menu.scrolling=0;
                    }
                    if (code.equals("ENTER"))
                        menu.exitMenu();
                    if (code.equals("SPACE")) menu.performAction();
                }
        );

        scene.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            Game_Scene.input.remove(code);
            if (Game_Scene.input.isEmpty()) menu.scrolling=Long.MAX_VALUE;
        });
    }

    public static void addController(Scene_outside scene, DialogPrompt menu){
        scene.setOnKeyPressed(e -> menu.next()
        );
    }

}
