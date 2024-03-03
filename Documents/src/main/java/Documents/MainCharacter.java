package com.Documents;


import java.awt.event.KeyEvent;
public class MainCharacter extends DynamicCharacter {
    private int health;
    private int count;

    public MainCharacter(int x, int y, double movementSpeed, int health, int count, MapObject prevTile) {
        super(x, y, movementSpeed, prevTile);
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
    public void move(int dx, int dy, Board board) {
        // Implement main character movement
        
        MapObject temp = super.getPrevTile();

        int tempX = getX();
        int tempY = getY();
        setX((int) (getX()+ dx * movementSpeed));
        setY((int) (getY()+ dy * movementSpeed));
        if(checkCollision((board.board[getX()][getY()]))){
            tilePrev = new Room();
        }
        else{
            tilePrev = board.board[getX()][getY()];
        }
        this.tilePrev = board.board[getX()][getY()];
        board.board[getX()][getY()]=this;
        
        board.board[tempX][tempY]=temp;
    }

   @Override
    public boolean checkCollision(MapObject other) {
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
    public void moveByKey(KeyEvent e, Board board) {
    int keyCode = e.getKeyCode();
    switch (keyCode) {
        case KeyEvent.VK_UP:
            if (getY() > 0&&board.board[getX()][getY()-1] instanceof Wall) { // Check if moving up will keep the character within the bounds
                move(0, 1, board);
            }
            break;
        case KeyEvent.VK_DOWN:
            if (getY() < board.board.length - 1&&board.board[getX()][getY()+1] instanceof Wall) { // Check if moving down will keep the character within the bounds
                move(0, -1, board);
            }
            break;
        case KeyEvent.VK_LEFT:
            if (getX() > 0&&board.board[getX()-1][getY()] instanceof Wall) { // Check if moving left will keep the character within the bounds
                move(1, 0, board);
            }
            break;
        case KeyEvent.VK_RIGHT:
            if (getX() < board.board[0].length - 1&&board.board[getX()+1][getY()] instanceof Wall) { // Check if moving right will keep the character within the bounds
                move(-1, 0, board);
            }
            break;
        default:
            break;
    }
}

}