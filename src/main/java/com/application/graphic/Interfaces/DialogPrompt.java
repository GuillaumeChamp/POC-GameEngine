package com.application.graphic.Interfaces;

import com.application.graphic.Graphic_Const;
import com.application.graphic.scene.Scene_outside;
import javafx.geometry.Rectangle2D;


public class DialogPrompt implements MenuType{
    Scene_outside theScene;
    String[] content;
    int index = 0;

    /**
     * Prompt a dialogue on the screen first input make return to the scene
     * @param theScene unique scene
     * @param content all text line to prompt
     */
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

    /**
     * Allow painter to see where the text layout have to be place
     * @param effectivePolice police with a ratio to allow responsiveness
     * @return always the square at the bottom of the screen (3 tiles height)
     */
    @Override
    public Rectangle2D getPosition(double effectivePolice) {
        return new Rectangle2D(0,
                (Graphic_Const.V_TILES_PER_SCREEN-3)*Graphic_Const.TILES_SIZE,
                Graphic_Const.H_TILES_PER_SCREEN*Graphic_Const.TILES_SIZE,
                3*Graphic_Const.TILES_SIZE*effectivePolice);
    }

    /**
     * custom getter
     * @return the current text to display and exit if null
     */
    public String getContent() {
        try {
            return content[index];
        }catch (ArrayIndexOutOfBoundsException e){
            this.exit();
            return null;
        }
    }

    /**
     * Give the control back to the scene
     */
    @Override
    public void exit() {
        theScene.addController();
        theScene.exit();
    }

    /**
     * Make the text change to the next part or exit if ended
     */
    public void next() {
        index++;
        if(index>= content.length) exit();
    }
}
