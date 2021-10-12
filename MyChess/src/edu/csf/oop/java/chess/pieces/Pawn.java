package edu.csf.oop.java.chess.pieces;

import edu.csf.oop.java.chess.AbstractPart;

public class Pawn extends AbstractPart {

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void draw() {
        if (this.isWhite) {
            System.out.print("\u2659");
        }
        if (!(this.isWhite)) {
            System.out.print("\u265F");

        }

    }

    @Override
    public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {

        //если пешка движется вперед на один
        //или движется вперед на два с начальной позиции
        //или бьет черную фигуру
        if (this.isWhite) {
            return (((srcCol == destCol) && srcRow == (destRow + 1))
                    || ((srcRow == 6) && (srcCol == destCol) && (srcRow == (destRow + 2)))
                    || ((srcRow == (destRow + 1))
                    && (Math.abs(srcCol - destCol) == 1)));
        }
        else {
            return (((srcCol == destCol) && srcRow == (destRow - 1))
                    || ((srcRow == 1) && (srcCol == destCol) && (srcRow == (destRow - 2)))
                    || ((srcRow == (destRow - 1))
                    && (Math.abs(srcCol - destCol) == 1)));
        }



    }

    @Override
    public int relativeValue() {
        return 1;
    }
}
