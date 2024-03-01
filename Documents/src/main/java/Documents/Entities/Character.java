public abstract class Character {
    protected int x, y; // Position of the character

    public Character(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract boolean checkCollision(Character other);

}