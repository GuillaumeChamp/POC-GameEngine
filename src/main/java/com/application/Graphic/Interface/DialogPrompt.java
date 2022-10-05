package com.application.Graphic.Interface;

import com.application.Graphic.Graphic_Const;
import com.application.Graphic.Scene.Scene_outside;
import javafx.geometry.Rectangle2D;


public class DialogPrompt implements MenuType{
    Scene_outside theScene;
    String[] content;
    int index = 0;

    public DialogPrompt(Scene_outside theScene, String[] content) {
        this.theScene = theScene;
        this.content = content;
        theScene.lastMenu=this;
        this.addController();
    }

    @Override
    public void addController() {
        OptionControls.addController(theScene,this);
    }

    @Override
    public void performController() {
        //No special action
    }

    @Override
    public Rectangle2D getPosition(double effectivePolice) {
        return new Rectangle2D(0,(Graphic_Const.V_TILES_PER_SCREEN-3)*Graphic_Const.TILES_SIZE, Graphic_Const.H_TILES_PER_SCREEN*Graphic_Const.TILES_SIZE,3*Graphic_Const.TILES_SIZE*effectivePolice);
    }

    public String getContent() {
        try {
            return content[index];
        }catch (ArrayIndexOutOfBoundsException e){
            this.exit();
            return null;
        }
    }

    @Override
    public void exit() {
        theScene.addController();
        theScene.exit();
    }

    public void next() {
        index++;
        if(index>= content.length) exit();
    }
}
