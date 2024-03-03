package com.Documents;

public class EndTile extends MapObject {
    
    boolean locked;
    EndTile(int x, int y){
        super(x, y);
        locked = true;
    }
}
