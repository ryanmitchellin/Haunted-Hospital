package com.Documents;

import java.util.*;

import com.Documents.Entities.Character;
import com.Documents.Entities.MainCharacter;
import com.Documents.FixedObject.MapObject;
import com.Documents.Entities.StaticCharacter;


public class Stage {
    List<Character> characterList = new ArrayList();
    List<MapObject> wallList = new ArrayList();
    List<StaticCharacter> rewardList = new ArrayList();
    MainCharacter mainCharacter;
    int width = 0;
    int height = 0;

    int getWidth(){ return width; }

    int getHeight(){ return height; }

    Character getMainCharacter(){ return mainCharacter; }

    List<Character> getCharacterList(){ return characterList; }

    List<MapObject> getWallList(){ return wallList; }

    

}
