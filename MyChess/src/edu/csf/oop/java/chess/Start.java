package edu.csf.oop.java.chess;

import edu.csf.oop.java.chess.logging.Log;

import java.util.Scanner;

public class Start {
     Scanner user_input = new Scanner(System.in);

    public static void main(String[] args) {
        Chessboard myChessboard = new Chessboard();
        Log.logging();


        while (myChessboard.getGameRunning()) {
            myChessboard.printBoard();
            myChessboard.move();

        }

    }
}
