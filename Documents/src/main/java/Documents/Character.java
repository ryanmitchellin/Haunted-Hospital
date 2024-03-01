package com.Documents.Entities;

import com.Documents.FixedObject.MapObject;

public abstract class Character extends MapObject{
    protected int x, y; // Position of the character

    public Character(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract boolean checkCollision(Character other);

}