package Documents.entity;
import Documents.main.GamePanel;
import Documents.tile.TileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonsterTest {

    private GamePanel gamePanel;
    private Monster monster;
    private static int left;

    @BeforeEach
    public void setUp() {

        gamePanel = new GamePanel();
        monster = new Monster(gamePanel);
        gamePanel.tileSize = 48;
        left = monster.wxPos + monster.detectionArea.x;
    }

    @Test
    public void testUpdateDistance(){
        int initialXPos = 0;
        monster.wxPos = initialXPos;
        monster.vel = 5;
        monster.direction = "right";
        monster.update(); // note the update is called twice in monster due to super
        assertEquals(initialXPos + monster.vel, monster.wxPos);
    }

    @Test
    public void testUpdateWithCollision(){
        monster.vel = 5;
        monster.direction = "left";
        gamePanel.tileFactory.getTile(left).collision = true;
        monster.wyPos = 10;
        monster.update();
        assertEquals(10, monster.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionMovementNone(){
        int initialXPos = 10;
        int initialYPos = 10;
        monster.wxPos = initialXPos;
        monster.wyPos = initialYPos;
        monster.vel = 0;
        monster.direction = "up"; // Direction is set but should not matter due to zero velocity
        monster.isCollision = false;
        monster.update();
        assertEquals(initialXPos, monster.wxPos);
        assertEquals(initialYPos, monster.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionDirectionNone(){
        int initialXPos = 10;
        int initialYPos = 10;
        monster.wxPos = initialXPos;
        monster.wyPos = initialYPos;
        monster.vel = 5;
        monster.direction = ""; // Direction is set but should not matter due to zero velocity
        monster.isCollision = false;
        monster.update();
        assertEquals(initialXPos, monster.wxPos);
        assertEquals(initialYPos, monster.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionUp(){
        monster.vel = 5;
        monster.direction = "up";
        monster.wyPos = 10;
        monster.isCollision = false;
        monster.update();
        assertEquals(5, monster.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionDown(){
        monster.vel = 5;
        monster.direction = "down";
        monster.wyPos = 10;
        monster.isCollision = false;
        monster.update();
        assertEquals(15, monster.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionLeft(){
        monster.vel = 5;
        monster.direction = "left";
        monster.wxPos = 10;
        monster.isCollision = false;
        monster.update();
        assertEquals(5, monster.wxPos);
    }

    @Test
    public void testUpdateWithoutCollisionRight(){
        monster.vel = 5;
        monster.direction = "right";
        monster.wxPos = 10;
        monster.isCollision = false;
        monster.update(); // same as above for all these cases
        assertEquals(15, monster.wxPos);
    }

    @Test
    public void testUpdateSpriteCountSame() {
        monster.direction = "up";
        monster.spriteCount = 1;
        monster.update(); // doubling everything
        assertEquals(2, monster.spriteCount);
    }

    @Test
    public void testUpdateSpriteCountIncrement() {
        monster.direction = "up";
        monster.spriteCount = 11;
        monster.spriteNum = 1;
        monster.update();
        assertEquals(0, monster.spriteCount);
    }

    @Test
    public void testUpdateSpriteNumToggles() {
        monster.direction = "up";
        monster.spriteCount = 15;
        monster.spriteNum = 1;
        monster.update();
        assertEquals(2, monster.spriteNum);
        monster.direction = "up";
        monster.spriteCount = 15;
        monster.spriteNum = 2;
        monster.update();
        assertEquals(1, monster.spriteNum);
        monster.direction = "up";
        monster.spriteCount = 15;
        monster.spriteNum = 3;
        monster.update();
        assertEquals(3, monster.spriteNum);
    }
}
