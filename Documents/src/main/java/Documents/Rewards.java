package com.Documents;

public class Rewards extends StaticCharacter {
    public Rewards(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean checkCollision(MapObject other) {
        // Implement collision detection for rewards
        return false;
    }

}