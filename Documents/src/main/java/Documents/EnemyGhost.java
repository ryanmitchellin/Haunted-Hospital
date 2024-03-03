package com.Documents;

public class EnemyGhost extends DynamicCharacter {


    public EnemyGhost(int x, int y, double movementSpeed, MapObject prevTile) {
        super(x, y, movementSpeed, Tile.G, prevTile);
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