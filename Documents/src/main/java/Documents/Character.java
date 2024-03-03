package com.Documents;

public abstract class Character extends MapObject{
    protected int x, y; // Position of the character

    public Character(int x, int y, Tile tile) {
        super(x, y, tile);
    }

    public abstract boolean checkCollision(Character other);

}