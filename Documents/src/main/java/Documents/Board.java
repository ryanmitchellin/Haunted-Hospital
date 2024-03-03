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
                                {new Wall(), new EnemyGhost(1, 1, 1, new Room()), new Room(), new Room(), new Room(), new Room(), new Room(), new Wall()},
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
                if(board[i][j].getTileType()==Tile.S){
                    startTile = (StartTile) board[i][j];
                }
                if(board[i][j].getTileType()==Tile.E){
                    endTile = (EndTile) board[i][j];
                }
                if(board[i][j].getTileType()==Tile.G){
                    characterList.add(new EnemyGhost(i, j, 1, new Room()));
                }
                if(board[i][j].getTileType()==Tile.Re){
                    staticsList.add(new Rewards(i, j));
                }
                if(board[i][j].getTileType()==Tile.T){
                    staticsList.add(new Traps(i, j));
                }

            }
            
        }
        mainCharacter = new MainCharacter(startTile.getX(), startTile.getY(), 1, 1, 1, startTile);
        board[startTile.getX()][startTile.getY()] = mainCharacter;
        characterList.add(mainCharacter);
    }
    void printBoard(){
        System.out.println("Printing board");
        for(int i = 0; i<board.length; i++){
            for(int j = 0;  j<board[i].length; j++){
                if(board[i][j].getTileType()==Tile.W){
                    System.out.print("[ ]");
                }
                else if(board[i][j].getTileType()==Tile.R){
                    System.out.print("   ");
                }
                else if(board[i][j].getTileType()==Tile.S){
                    System.out.print(" O ");
                }
                else if(board[i][j].getTileType()==Tile.E){
                    System.out.print(" X ");
                }
                else if(board[i][j].getTileType()==Tile.M){
                    System.out.print(" M ");
                }
                else if(board[i][j].getTileType()==Tile.G){
                    System.out.print(" n ");
                }
                else if(board[i][j].getTileType()==Tile.T){
                    System.out.print(" - ");
                }
                else if(board[i][j].getTileType()==Tile.Re){
                    System.out.print(" + ");
                }
            }
            System.out.println();
        }
    }
    
    




}
