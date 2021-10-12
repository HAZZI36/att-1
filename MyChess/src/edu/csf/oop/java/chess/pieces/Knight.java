package edu.csf.oop.java.chess.pieces;

import edu.csf.oop.java.chess.AbstractPart;

public class Knight extends AbstractPart {
    public Knight(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void draw() {
        if (isWhite){
            System.out.print("\u2658");
        }
        else{
            System.out.print("\u265E");
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
