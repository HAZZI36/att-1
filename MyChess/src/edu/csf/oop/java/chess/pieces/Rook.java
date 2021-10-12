package edu.csf.oop.java.chess.pieces;

import edu.csf.oop.java.chess.AbstractPart;

public class Rook extends AbstractPart {
    public Rook(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void draw() {
        if (isWhite){
            System.out.print("\u2656");
        }
        else{
            System.out.print("\u265C");
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
