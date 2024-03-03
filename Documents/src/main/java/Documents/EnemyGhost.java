package com.Documents;

public class EnemyGhost extends DynamicCharacter {


    public EnemyGhost(int x, int y, double movementSpeed) {
        super(x, y, movementSpeed, new Room());
    }

    @Override
    public void move(int dx, int dy, Board board) {
        // Implement enemy ghost movement using AI path finding
    }

    @Override
    public boolean checkCollision(MapObject other) {
        // Implement collision detection for enemy ghosts
        
        return false;
    }

}