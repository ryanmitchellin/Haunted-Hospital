package com.Documents;

public class Rewards extends StaticCharacter {
    public Rewards(int x, int y) {
        super(x, y, Tile.Re);
    }

    @Override
    public boolean checkCollision(Character other) {
        // Implement collision detection for rewards
        return false;
    }

}