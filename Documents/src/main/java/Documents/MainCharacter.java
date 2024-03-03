package com.Documents;


import java.awt.event.KeyEvent;
public class MainCharacter extends DynamicCharacter {
    private int health;
    private int count;

    public MainCharacter(int x, int y, double movementSpeed, int health, int count, MapObject prevTile) {
        super(x, y, movementSpeed, Tile.M, prevTile);
        this.health = health;
        this.count = count;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void move(int dx, int dy) {
        // Implement main character movement
        x += dx * movementSpeed;
        y += dy * movementSpeed;
    }

   @Override
    public boolean checkCollision(Character other) {
        if (other instanceof EnemyGhost) {
            health = 0;
            return true;
        } else if (other instanceof Rewards) {
            count += 10;
            return true;
        } else if (other instanceof Traps) {
            count -= 10;
            return true;
        }
        return false;
    }

    // Arrow keyboard click movement
    public void moveByKey(KeyEvent e, int maxX, int maxY) {
    int keyCode = e.getKeyCode();
    switch (keyCode) {
        case KeyEvent.VK_UP:
            if (y > 0) { // Check if moving up will keep the character within the bounds
                move(0, -1);
            }
            break;
        case KeyEvent.VK_DOWN:
            if (y < maxY - 1) { // Check if moving down will keep the character within the bounds
                move(0, 1);
            }
            break;
        case KeyEvent.VK_LEFT:
            if (x > 0) { // Check if moving left will keep the character within the bounds
                move(-1, 0);
            }
            break;
        case KeyEvent.VK_RIGHT:
            if (x < maxX - 1) { // Check if moving right will keep the character within the bounds
                move(1, 0);
            }
            break;
        default:
            break;
    }
}

}