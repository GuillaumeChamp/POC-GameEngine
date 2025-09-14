package com.application.game.level.elements;

import com.application.game.level.elements.layer1.Warp;

public class TPException extends Exception{
    Warp tp;
    public TPException(Warp warp){
        this.tp=warp;
    }

    public Warp getTp() {
        return tp;
    }
}
