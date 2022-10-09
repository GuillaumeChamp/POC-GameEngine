package com.application.Game.Level.LevelElements;

import com.application.Game.Level.LevelElements.Layer1.Warp;

public class TPException extends Exception{
    Warp tp;
    public TPException(Warp warp){
        this.tp=warp;
    }

    public Warp getTp() {
        return tp;
    }
}
