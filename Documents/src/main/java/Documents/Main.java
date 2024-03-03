package com.Documents;


public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.initializeBoard(board.board1);
        board.printBoard();
        // System.out.println("X "+board.mainCharacter.getX()+" Y "+board.mainCharacter.getY());
        board.mainCharacter.move(-1, 0, board);
    
        board.moveEnemies();
        board.printBoard();
        board.mainCharacter.move(0, 1, board);
        board.moveEnemies();
        board.printBoard();
        
    }
}