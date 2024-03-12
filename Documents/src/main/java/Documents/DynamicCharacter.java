package Documents;


public abstract class DynamicCharacter extends Character {
    protected double movementSpeed; // Movement speed of the character
    MapObject tilePrev; //What used to be on the tile the character is now on
    public DynamicCharacter(int x, int y, double movementSpeed, MapObject tile) {
        super(x, y);
        
        tilePrev = tile;
        this.movementSpeed = movementSpeed;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public MapObject getPrevTile(){
        return tilePrev;
    }
    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }



    public abstract void move(int dx, int dy, Board board);

}