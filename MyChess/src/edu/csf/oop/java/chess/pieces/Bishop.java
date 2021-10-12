package edu.csf.oop.java.chess.pieces;

import edu.csf.oop.java.chess.AbstractPart;

public class Bishop extends AbstractPart {
    public Bishop(boolean isWhite) {
        super(isWhite);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void draw() {
        if (isWhite) {
            System.out.print("\u2657");
        } else {
            System.out.print("\u265D");
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
