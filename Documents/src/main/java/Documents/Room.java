package com.Documents;

public class Room extends MapObject{
    private int _roomNr;
    private static int _roomCnt = 1;
    
 
    Room(){
        super(Tile.R);
        _roomNr = _roomCnt++;
        
        // System.out.println("creating Room #" + _roomNr);

    }


    public String toString(){
        return "Room #" + new Integer(_roomNr).toString();
    }
}
