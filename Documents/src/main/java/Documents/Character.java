package Documents;


public abstract class Character extends MapObject{
    protected int x, y; // Position of the character

    public Character(int x, int y) {
        super(x, y);
        
    }

    public abstract boolean checkCollision(MapObject other);

}