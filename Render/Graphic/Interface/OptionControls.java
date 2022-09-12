package Graphic.Interface;

import Graphic.Scene.Scene_outside;

public class OptionControls {
    public static void addController(Scene_outside scene,Menu menu){
        scene.setOnKeyPressed(e -> {
                    String code = e.getCode().toString();
                    if (!scene.input.contains(code)) {
                        scene.input.add(code);
                        menu.changeOption();
                        menu.scrolling=System.nanoTime();
                    }
                    if (code.equals("ENTER"))
                        menu.exitMenu();
                    if (code.equals("SPACE")) menu.performAction();
                }
        );

        scene.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            scene.input.remove(code);
            if (scene.input.isEmpty()) menu.scrolling=Long.MAX_VALUE;
        });
    }

}
