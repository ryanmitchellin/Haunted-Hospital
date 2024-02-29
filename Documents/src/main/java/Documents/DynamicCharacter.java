public abstract class DynamicCharacter extends Character {
    public DynamicCharacter(int x, int y) {
        super(x, y);
    }

    public abstract void move(int dx, int dy);

}
