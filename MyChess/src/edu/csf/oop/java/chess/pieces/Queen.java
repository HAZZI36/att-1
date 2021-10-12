package edu.csf.oop.java.chess.pieces;

import edu.csf.oop.java.chess.AbstractPart;

public class Queen extends AbstractPart {
    public Queen(boolean isWhite) {
        super(isWhite);

    }

    @Override
    public void draw() {
        if (isWhite){
            System.out.print("\u2655");
        }
        else{
            System.out.print("\u265B");
        }
    }

    @Override
    public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
        return false;
    }

    @Override
    public int relativeValue() {
        return 0;
    }
}
