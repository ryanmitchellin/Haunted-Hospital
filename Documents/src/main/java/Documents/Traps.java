public class Traps extends StaticCharacter {
    public Traps(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean checkCollision(Character other) {
        // Implement collision detection for traps
        return false;
    }

}
