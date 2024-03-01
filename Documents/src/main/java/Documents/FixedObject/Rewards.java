package com.phase2;

public class Rewards extends StaticCharacter {
    public Rewards(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean checkCollision(Character other) {
        // Implement collision detection for rewards
        return false;
    }

}