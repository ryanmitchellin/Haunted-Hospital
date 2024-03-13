package Documents;


public class Traps extends StaticCharacter {
    public Traps(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean checkCollision(MapObject other) {
        // Implement collision detection for traps

        if (other instanceof MainCharacter) {
            return true;
        }
        return false;
    }

}
