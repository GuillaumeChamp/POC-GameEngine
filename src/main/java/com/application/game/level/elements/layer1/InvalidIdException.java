package com.application.game.level.elements.layer1;

public class InvalidIdException extends ArrayIndexOutOfBoundsException{
    public InvalidIdException() {
        super("Invalid Id for an OverTile of this type");
    }
    public InvalidIdException(String s) {
        super(s);
    }
}
