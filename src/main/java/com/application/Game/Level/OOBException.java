package com.application.Game.Level;

/**
 * Special kind of exception to throw when an entity is out of bound
 * Holding information relative the which bound have been break
 */
public class OOBException extends ArrayIndexOutOfBoundsException{
    public final int lineIndex;
    public final int columnIndex;

    /**
     * Create a new OOBException
     * @param columnIndex columnIndex when the exception occur
     * @param lineIndex lineIndex when the exception occur
     */
    public OOBException(int columnIndex, int lineIndex) {
        this.lineIndex = lineIndex;
        this.columnIndex = columnIndex;
    }
}
