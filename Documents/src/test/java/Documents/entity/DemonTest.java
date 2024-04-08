package Documents.entity;
import Documents.main.GamePanel;
import Documents.tile.TileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemonTest {

    private GamePanel gamePanel;
    private Demon demon;
    private static int left;

    @BeforeEach
    public void setUp() {
        gamePanel = new GamePanel();
        demon = new Demon(gamePanel);
        gamePanel.tileSize = 48;
        left = demon.wxPos + demon.detectionArea.x;
    }

    @Test
    public void testUpdateDistance(){
        int initialXPos = 0;
        demon.wxPos = initialXPos;
        demon.vel = 5;
        demon.direction = "right";
        demon.update();
        assertEquals(initialXPos + demon.vel, demon.wxPos);
    }

    @Test
    public void testUpdateWithCollision(){
        demon.vel = 5;
        demon.direction = "left";
        gamePanel.tileFactory.getTile(left).collision = true;
        demon.wyPos = 10;
        demon.update();
        assertEquals(10, demon.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionMovementNone(){
        int initialXPos = 10;
        int initialYPos = 10;
        demon.wxPos = initialXPos;
        demon.wyPos = initialYPos;
        demon.vel = 0;
        demon.direction = "up"; // Direction is set but should not matter due to zero velocity
        demon.isCollision = false;
        demon.update();
        assertEquals(initialXPos, demon.wxPos);
        assertEquals(initialYPos, demon.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionDirectionNone(){
        int initialXPos = 10;
        int initialYPos = 10;
        demon.wxPos = initialXPos;
        demon.wyPos = initialYPos;
        demon.vel = 5;
        demon.direction = ""; // Direction is set but should not matter due to zero velocity
        demon.isCollision = false;
        demon.update();
        assertEquals(initialXPos, demon.wxPos);
        assertEquals(initialYPos, demon.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionUp(){
        demon.vel = 5;
        demon.direction = "up";
        demon.wyPos = 10;
        demon.isCollision = false;
        demon.update();
        assertEquals(5, demon.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionDown(){
        demon.vel = 5;
        demon.direction = "down";
        demon.wyPos = 10;
        demon.isCollision = false;
        demon.update();
        assertEquals(15, demon.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionLeft(){
        demon.vel = 5;
        demon.direction = "left";
        demon.wxPos = 10;
        demon.isCollision = false;
        demon.update();
        assertEquals(5, demon.wxPos);
    }

    @Test
    public void testUpdateWithoutCollisionRight(){
        demon.vel = 5;
        demon.direction = "right";
        demon.wxPos = 10;
        demon.isCollision = false;
        demon.update(); // same as above for all these cases
        assertEquals(15, demon.wxPos);
    }

    @Test
    public void testUpdateSpriteCountSame() {
        demon.direction = "up";
        demon.spriteCount = 1;
        demon.update(); // doubling everything
        assertEquals(2, demon.spriteCount);
    }

    @Test
    public void testUpdateSpriteCountIncrement() {
        demon.direction = "up";
        demon.spriteCount = 11;
        demon.spriteNum = 1;
        demon.update();
        assertEquals(0, demon.spriteCount);
    }

    @Test
    public void testUpdateSpriteNumToggles() {
        demon.direction = "up";
        demon.spriteCount = 15;
        demon.spriteNum = 1;
        demon.update();
        assertEquals(2, demon.spriteNum);
        demon.direction = "up";
        demon.spriteCount = 15;
        demon.spriteNum = 2;
        demon.update();
        assertEquals(1, demon.spriteNum);
        demon.direction = "up";
        demon.spriteCount = 15;
        demon.spriteNum = 3;
        demon.update();
        assertEquals(3, demon.spriteNum);
    }
}

