package com.Documents;

import java.util.ArrayList;
import java.util.List;

public class Board{

    
    List<Character> characterList = new ArrayList();
    List<StaticCharacter> staticsList = new ArrayList();
    StartTile startTile;
    EndTile endTile;
    MainCharacter mainCharacter;
    static int width = 0;
    static int height = 0;
    static MapObject [][] board;

    Board(){
        
    }
    static int getWidth(){ return width; }

    static int getHeight(){ return height; }

    Character getMainCharacter(){ return mainCharacter; }

    List<Character> getCharacterList(){ return characterList; }

    static MapObject [][] board1 = {   {new Wall(), new Wall(), new Wall(), new Wall(), new Wall(), new Wall(), new EndTile(0, 7), new Wall()},
                                {new Wall(), new EnemyGhost(1, 1, 1), new Room(), new Room(), new Room(), new Room(), new Room(), new Wall()},
                                {new Wall(), new Room(), new Room(), new Room(), new Room(), new Room(), new Room(), new Wall()},
                                {new Wall(), new Room(), new Wall(), new Wall(), new Room(), new Room(), new Room(), new Wall()},
                                {new Wall(), new Room(), new Wall(), new Wall(), new Rewards(4, 4), new Traps(4, 5), new Wall(), new Wall()},
                                {new Wall(), new Room(), new Room(), new Room(), new Room(), new Room(), new Wall(), new Wall()},
                                {new Wall(), new Room(), new Room(), new Room(), new Room(), new Wall(), new Wall(), new Wall()},
                                {new Wall(), new StartTile(7, 1), new Wall(), new Wall(), new Wall(), new Wall(), new Wall(), new Wall()}    };
    
    
    
    void initializeBoard(MapObject [][] theBoard){
        board = theBoard;
        height = theBoard.length;
        width = theBoard[0].length;
        for(int i = 0; i<height; i++){
            for(int j = 0; j<width; j++){
                if(board[i][j] instanceof StartTile){
                    startTile = (StartTile) board[i][j];
                }
                if(board[i][j] instanceof EndTile){
                    endTile = (EndTile) board[i][j];
                }
                if(board[i][j] instanceof EnemyGhost){
                    characterList.add((EnemyGhost) board[i][j]);
                }
                if(board[i][j] instanceof Rewards){
                    staticsList.add(new Rewards(i, j));
                }
                if(board[i][j] instanceof Traps){
                    staticsList.add(new Traps(i, j));
                }

            }
            
        }
        // System.out.println("X "+startTile.getX()+" Y "+startTile.getY());
        mainCharacter = new MainCharacter(startTile.getX(), startTile.getY(), 1, 1, 1, startTile);
        board[startTile.getX()][startTile.getY()] = mainCharacter;
        characterList.add(mainCharacter);
    }
    void printBoard(){
        System.out.println("Printing board");
        for(int i = 0; i<board.length; i++){
            for(int j = 0;  j<board[i].length; j++){
                if(board[i][j] instanceof Wall){
                    System.out.print("[ ]");
                }
                else if(board[i][j] instanceof Room){
                    System.out.print("   ");
                }
                else if(board[i][j] instanceof StartTile){
                    System.out.print(" O ");
                }
                else if(board[i][j] instanceof EndTile){
                    System.out.print(" X ");
                }
                else if(board[i][j] instanceof MainCharacter){
                    System.out.print(" M ");
                }
                else if(board[i][j] instanceof EnemyGhost){
                    System.out.print(" n ");
                }
                else if(board[i][j] instanceof Traps){
                    System.out.print(" - ");
                }
                else if(board[i][j] instanceof Rewards){
                    System.out.print(" + ");
                }
            }
            System.out.println();
        }
    }
    
    




}
