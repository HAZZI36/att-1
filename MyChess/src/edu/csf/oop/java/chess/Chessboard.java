package edu.csf.oop.java.chess;

import edu.csf.oop.java.chess.pieces.*;

import java.util.Random;
import java.util.Scanner;

public class Chessboard {
    private Boolean gameRunning;
    private final AbstractPart[][] chessboard = new AbstractPart[numOfRowsAndCols][numOfRowsAndCols];// [row][column]
    Scanner user_input = new Scanner(System.in);
    private static final int numOfRowsAndCols = 8;
    private static int srcRow, srcCol, destRow, destCol;
    private static int whiteScore = 0, blackScore = 0;
    private static Boolean whitesTurnToMove;
    // Устанавливает тру, если движение недоступно. Просит пользователя ввести в move()
    // метод.
    private static Boolean invalidMove = false;
    // Удерживает строку с вводом пользователя, чтобы следовать инструкциям
    String move;

    /**
     * Создаем объект шахматной доски и заполняет его фигурами
     */

    public Chessboard() {

        initialiseBoard(chessboard);
        gameRunning = true;

    }

    /**
     * Возвращает атрибут логического запуска игры, если это значение равно false, то вам следует
     *  * прекратить вызывать move() и printBoard() и закрыть шахматную доску()
     */
    public Boolean getGameRunning() {
        return this.gameRunning;
    }

    /**
     * Заполняет шахматную доску абстрактной фигуры правильными фигурами и
     * случайным образом назначает, белые или черные ходят первыми
     *
     */
    private static void initialiseBoard(AbstractPart[][] chessboard) {
        //шахматная доска-матрица 8X8
        // ряды [0] и [1] черные
        // ряды [6] и [7] белые

        for (int row = 0; row < chessboard.length; row++) {
            for (int col = 0; col < chessboard[row].length; col++) {
                if (row == 0) {
                    switch (col) {
                        case 0 -> chessboard[row][col] = new Rook(false);
                        case 1 -> chessboard[row][col] = new Knight(false);
                        case 2 -> chessboard[row][col] = new Bishop(false);
                        case 3 -> chessboard[row][col] = new Queen(false);
                        case 4 -> chessboard[row][col] = new King(false);
                        case 5 -> chessboard[row][col] = new Bishop(false);
                        case 6 -> chessboard[row][col] = new Knight(false);
                        case 7 -> chessboard[row][col] = new Rook(false);
                    }
                } else if (row == 1) {
                    chessboard[row][col] = new Pawn(false);
                } else if (row == 6) {
                    chessboard[row][col] = new Pawn(true);
                } else if (row == 7) {
                    switch (col) {
                        case 0 -> chessboard[row][col] = new Rook(true);
                        case 1 -> chessboard[row][col] = new Knight(true);
                        case 2 -> chessboard[row][col] = new Bishop(true);
                        case 3 -> chessboard[row][col] = new Queen(true);
                        case 4 -> chessboard[row][col] = new King(true);
                        case 5 -> chessboard[row][col] = new Bishop(true);
                        case 6 -> chessboard[row][col] = new Knight(true);
                        case 7 -> chessboard[row][col] = new Rook(true);
                    }
                } else {
                    chessboard[row][col] = null;
                }
            }
        }

        // Рандомно устанавливает, кто начинает первый
        Random rand = new Random();
        whitesTurnToMove = rand.nextBoolean();

    }

    /**
     * Выводит юникод для каждого символа на консоль с помощью draw()
     */
    public void printBoard() {

        System.out.println("\ta\tb\tc\td\te\tf\tg\th");
        for (int row = 0; row < chessboard.length; row++) {
            System.out.print(8 - row + ".\t");
            for (int col = 0; col < chessboard[row].length; col++) {
                if (chessboard[row][col] != null) {
                    chessboard[row][col].draw();
                    System.out.print("\t");

                } else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }


    private boolean moveValid() {

        // недоступно, если движение фигуры выходит за рамки шахматной доски

        if (srcRow < 0 || srcRow > 7 || srcCol < 0 || srcCol > 7 || destRow < 0
                || destRow > 7 || destCol < 0 || destCol > 7) {
            System.out.println("Move is outside the board");
            return false;
        }

        if (chessboard[srcRow][srcCol] == null) {
            System.err.println("Origin is empty");
            return false;
        }

        // недоступно, когда пользователь ходит вне очереди
        if ((chessboard[srcRow][srcCol].isWhite && !whitesTurnToMove)
                || (!chessboard[srcRow][srcCol].isWhite && whitesTurnToMove)) {
            System.err.println("It's not your turn");
            return false;
        }

        // ошибка, когда фигурой ходят неправильно
        if (!chessboard[srcRow][srcCol].isMoveValid(srcRow, srcCol, destRow,
                destCol)) {
            System.err.println("This piece doesn't move like that");
            return false;
        }

        if (chessboard[destRow][destCol] == null) {
            return true;
        }

        // нельзя ставить белую фигуру на белую
        if (chessboard[srcRow][srcCol].isWhite
                && chessboard[destRow][destCol].isWhite) {
            System.err.println("White cannot land on white");
            return false;
        }

        // нельзя ставить черную фигуру на черную
        if (!chessboard[srcRow][srcCol].isWhite
                && !chessboard[destRow][destCol].isWhite) {
            System.err.println("Black cannot land on black");
            return false;
        }

        return true;

    }

    /**
     * Приватный метод, вызываемый для обновления счета того, чей ход будет после
     */
    private void updateScore() {
        if (chessboard[destRow][destCol] == null) {
            return;
        }
        if (whitesTurnToMove) {
            whiteScore += chessboard[destRow][destCol].relativeValue();
        } else {
            blackScore += chessboard[destRow][destCol].relativeValue();

        }
    }

    /**
     * Принимает от пользователя движения, например, "d2 to d3"
     *  и конверитрует эту строку в массив с координатами для шахматной доски.
     * Проверяет, если движение возможно, то вызывается метод moveValid(), фигура перемещается
     * и обновляется счет. Если движение невозможно, то рекурсивно печатается сообщение об ошибке.
     */
    public void move() {

        System.out
                .println("___________________________________________________\n"
                        + "Score: White "
                        + whiteScore
                        + " | "
                        + blackScore
                        + " Black");

        if (invalidMove) {
            System.err.println("Move is invalid. Please try again:");
            invalidMove = false;
        }

        else if (whitesTurnToMove) {
            System.out
                    .println("""
                            ___________________________________________________
                            White's turn to move
                            ___________________________________________________
                            """);
        } else {
            System.out
                    .println("""
                            ___________________________________________________
                            Black's turn to move
                            ___________________________________________________
                            """);
        }

        move = user_input.nextLine();

        if (move.equalsIgnoreCase("exit")) {
            gameRunning = false;
            System.out.println("Thanks for playing.");
            return;
        }

        //в нижний регистр
        String lowerCase = move.toLowerCase();
        // парсим строку
        String[] components = lowerCase.split(" ");

        // если вы хотите переместиться "e1 to e5" тогда
        // components[0].chartAt(0) = 'e'
        // components [0].charAt (1) = '1'

        srcRow = 7 - (components[0].charAt(1) - '1');
        srcCol = components[0].charAt(0) - 'a';
        destRow = 7 - (components[2].charAt(1) - '1');
        destCol = components[2].charAt(0) - 'a';

        if (moveValid()) {
            updateScore();
            chessboard[destRow][destCol] = chessboard[srcRow][srcCol];
            chessboard[srcRow][srcCol] = null;
            whitesTurnToMove = !whitesTurnToMove;
        } else {
            invalidMove = true;
            move();

        }

    }
}
