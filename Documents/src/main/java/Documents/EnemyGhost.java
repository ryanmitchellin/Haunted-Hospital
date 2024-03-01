package com.Documents.Entities;

public class EnemyGhost extends DynamicCharacter {
    public EnemyGhost(int x, int y, double movementSpeed) {
        super(x, y, movementSpeed);
    }

    @Override
    public void move(int dx, int dy) {
        // Implement enemy ghost movement using AI path finding
    }

    @Override
    public boolean checkCollision(Character other) {
        // Implement collision detection for enemy ghosts
        return false;
    }

}