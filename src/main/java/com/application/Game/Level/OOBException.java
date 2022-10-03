package com.application.Game.Level;

public class OOBException extends ArrayIndexOutOfBoundsException{
    public final int lineIndex;
    public final int columnIndex;

    public OOBException(int columnIndex, int lineIndex) {
        this.lineIndex = lineIndex;
        this.columnIndex = columnIndex;
    }
}
