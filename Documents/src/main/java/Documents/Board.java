package com.Documents;

import java.util.ArrayList;
import java.util.List;

public class Board{

    MapObject [][] board = new MapObject[getWidth()][getHeight()];
    List<Character> characterList = new ArrayList();
    List<StaticCharacter> rewardList = new ArrayList();
    List<Wall> wallList = new ArrayList();
    MainCharacter mainCharacter;
    int width = 0;
    int height = 0;

    int getWidth(){ return width; }

    int getHeight(){ return height; }

    Character getMainCharacter(){ return mainCharacter; }

    List<Character> getCharacterList(){ return characterList; }
    
    List<Wall> getWallList(){ return wallList; }


}
