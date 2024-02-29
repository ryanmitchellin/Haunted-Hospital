public class EnemyGhost extends DynamicCharacter {
    public EnemyGhost(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int dx, int dy) {
        // Implement enemy ghost movement
    }

    @Override
    public boolean checkCollision(Character other) {
        // Implement collision detection for enemy ghosts
        return false;
    }

}
