package edu.csf.oop.java.chess;

public abstract class AbstractPart {
     public boolean isWhite;

    public AbstractPart(boolean isWhite) {
        this.isWhite = isWhite;
    }
    public boolean isWhite() {
        return isWhite;
    }

    public abstract void draw();

    /**
     * Проверяет, является ли данный ход допустимым
     */
    public abstract boolean isMoveValid(int srcRow, int srcCol, int destRow,
                                        int destCol);

    /**
     * возвращает положение относительно других фигур
     */
    public abstract int relativeValue();

}
