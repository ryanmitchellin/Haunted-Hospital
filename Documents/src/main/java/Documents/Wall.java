package com.Documents.FixedObject;
/**
 * Child class Wall of parent MapSite. A wall blocks off one side of a room
 */
public class Wall extends MapObject{
    private int _wallNr;
    private static int _wallCnt = 1;
    /** 
     * <p> Constructor function to create one wall
     */
    Wall(){
        _wallNr = _wallCnt++;
        System.out.println("creating Wall #" + new Integer(_wallNr).toString());
    }

    public String toString(){
        return "Wall #" + new Integer(_wallNr).toString();
    }

}
