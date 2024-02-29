public class MainCharacter extends DynamicCharacter {
    public MainCharacter(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int dx, int dy) {
        // Implement main character movement
    }

    @Override
    public boolean checkCollision(Character other) {
        // Implement collision detection for main character
        return false;
    }

}
