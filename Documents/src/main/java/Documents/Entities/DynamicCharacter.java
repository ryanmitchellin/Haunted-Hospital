package com.phase2;

public abstract class DynamicCharacter extends Character {
    protected double movementSpeed; // Movement speed of the character

    public DynamicCharacter(int x, int y, double movementSpeed) {
        super(x, y);
        this.movementSpeed = movementSpeed;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public abstract void move(int dx, int dy);

}