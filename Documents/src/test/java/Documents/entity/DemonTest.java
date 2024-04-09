package Documents.entity;
import Documents.main.GamePanel;
import Documents.tile.TileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    public void testUpdateWithoutDistanceExceeding1(){
        demon.vel = 5;
        demon.direction = "right";
        demon.wxPos = 2500;
        demon.wyPos = 2500;
        gamePanel.mainCharacter.wxPos = 1250;
        gamePanel.mainCharacter.wyPos = 1250;
        demon.onPath = false;
        demon.isCollision = false;
        demon.update();
        assertFalse(demon.onPath);
    }

    @Test
    public void testUpdateWithoutDistanceExceeding2(){
        demon.vel = 5;
        demon.direction = "right";
        demon.wxPos = 2500;
        demon.wyPos = 2500;
        gamePanel.mainCharacter.wxPos = 1250;
        gamePanel.mainCharacter.wyPos = 1250;
        demon.onPath = true;
        demon.isCollision = false;
        demon.update();
        assertTrue(demon.onPath);
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

    @Test
    public void testSetActionOnPathTrue() {
        demon.direction = "up";
        demon.onPath = true;
        demon.wxPos = 100;
        demon.wyPos = 100;
        gamePanel.mainCharacter.wxPos = 250;
        gamePanel.mainCharacter.wyPos = 250;
        demon.setAction();
        assertEquals("down", demon.direction);
    }

    @Test
    public void testSetActionOnPathFalseActionTrue1() {
        demon.direction = "up";
        demon.onPath = false;
        demon.actionLockCount = 119;
        demon.setAction();
        assertEquals(0, demon.actionLockCount);
        if (demon.randomI <= 25) {
            assertEquals("up", demon.direction);
        }
        if (demon.randomI > 25 && demon.randomI <= 50) {
            assertEquals("down", demon.direction);
        }
        if (demon.randomI > 50 && demon.randomI <= 75) {
            assertEquals("left", demon.direction);
        }
        if (demon.randomI > 75 && demon.randomI <= 100) {
            assertEquals("right", demon.direction);
        }
    }

    @Test
    public void testSetActionOnPathFalseActionTrue2() {
        demon.direction = "up";
        demon.onPath = false;
        demon.actionLockCount = 119;
        demon.setAction();
        assertEquals(0, demon.actionLockCount);
        if (demon.randomI <= 25) {
            assertEquals("up", demon.direction);
        }
        if (demon.randomI > 25 && demon.randomI <= 50) {
            assertEquals("down", demon.direction);
        }
        if (demon.randomI > 50 && demon.randomI <= 75) {
            assertEquals("left", demon.direction);
        }
        if (demon.randomI > 75 && demon.randomI <= 100) {
            assertEquals("right", demon.direction);
        }
    }

    @Test
    public void testSetActionOnPathFalseActionTrue3() {
        demon.direction = "up";
        demon.onPath = false;
        demon.actionLockCount = 119;
        demon.setAction();
        assertEquals(0, demon.actionLockCount);
        if (demon.randomI <= 25) {
            assertEquals("up", demon.direction);
        }
        if (demon.randomI > 25 && demon.randomI <= 50) {
            assertEquals("down", demon.direction);
        }
        if (demon.randomI > 50 && demon.randomI <= 75) {
            assertEquals("left", demon.direction);
        }
        if (demon.randomI > 75 && demon.randomI <= 100) {
            assertEquals("right", demon.direction);
        }
    }

    @Test
    public void testSetActionOnPathFalseActionTrue4() {
        demon.direction = "up";
        demon.onPath = false;
        demon.actionLockCount = 119;
        demon.setAction();
        assertEquals(0, demon.actionLockCount);
        if (demon.randomI <= 25) {
            assertEquals("up", demon.direction);
        }
        if (demon.randomI > 25 && demon.randomI <= 50) {
            assertEquals("down", demon.direction);
        }
        if (demon.randomI > 50 && demon.randomI <= 75) {
            assertEquals("left", demon.direction);
        }
        if (demon.randomI > 75 && demon.randomI <= 100) {
            assertEquals("right", demon.direction);
        }
    }

    @Test
    public void testSetActionOnPathFalseActionTrue5() {
        demon.direction = "up";
        demon.onPath = false;
        demon.actionLockCount = 119;
        demon.setAction();
        assertEquals(0, demon.actionLockCount);
        if (demon.randomI <= 25) {
            assertEquals("up", demon.direction);
        }
        if (demon.randomI > 25 && demon.randomI <= 50) {
            assertEquals("down", demon.direction);
        }
        if (demon.randomI > 50 && demon.randomI <= 75) {
            assertEquals("left", demon.direction);
        }
        if (demon.randomI > 75 && demon.randomI <= 100) {
            assertEquals("right", demon.direction);
        }
    }

    @Test
    public void testSetActionOnPathFalseActionTrue6() {
        demon.direction = "up";
        demon.onPath = false;
        demon.actionLockCount = 119;
        demon.setAction();
        assertEquals(0, demon.actionLockCount);
        if (demon.randomI <= 25) {
            assertEquals("up", demon.direction);
        }
        if (demon.randomI > 25 && demon.randomI <= 50) {
            assertEquals("down", demon.direction);
        }
        if (demon.randomI > 50 && demon.randomI <= 75) {
            assertEquals("left", demon.direction);
        }
        if (demon.randomI > 75 && demon.randomI <= 100) {
            assertEquals("right", demon.direction);
        }
    }

    @Test
    public void testSetActionOnPathFalseActionTrue7() {
        demon.direction = "up";
        demon.onPath = false;
        demon.actionLockCount = 119;
        demon.setAction();
        assertEquals(0, demon.actionLockCount);
        if (demon.randomI <= 25) {
            assertEquals("up", demon.direction);
        }
        if (demon.randomI > 25 && demon.randomI <= 50) {
            assertEquals("down", demon.direction);
        }
        if (demon.randomI > 50 && demon.randomI <= 75) {
            assertEquals("left", demon.direction);
        }
        if (demon.randomI > 75 && demon.randomI <= 100) {
            assertEquals("right", demon.direction);
        }
    }

    @Test
    public void testSetActionOnPathFalseActionTrue8() {
        demon.direction = "up";
        demon.onPath = false;
        demon.actionLockCount = 119;
        demon.setAction();
        assertEquals(0, demon.actionLockCount);
        if (demon.randomI <= 25) {
            assertEquals("up", demon.direction);
        }
        if (demon.randomI > 25 && demon.randomI <= 50) {
            assertEquals("down", demon.direction);
        }
        if (demon.randomI > 50 && demon.randomI <= 75) {
            assertEquals("left", demon.direction);
        }
        if (demon.randomI > 75 && demon.randomI <= 100) {
            assertEquals("right", demon.direction);
        }
    }

    @Test
    public void testSetActionOnPathFalseActionTrue9() {
        demon.direction = "up";
        demon.onPath = false;
        demon.actionLockCount = 119;
        demon.setAction();
        assertEquals(0, demon.actionLockCount);
        if (demon.randomI <= 25) {
            assertEquals("up", demon.direction);
        }
        if (demon.randomI > 25 && demon.randomI <= 50) {
            assertEquals("down", demon.direction);
        }
        if (demon.randomI > 50 && demon.randomI <= 75) {
            assertEquals("left", demon.direction);
        }
        if (demon.randomI > 75 && demon.randomI <= 100) {
            assertEquals("right", demon.direction);
        }
    }

    @Test
    public void testSetActionOnPathFalseActionTrue10() {
        demon.direction = "up";
        demon.onPath = false;
        demon.actionLockCount = 119;
        demon.setAction();
        assertEquals(0, demon.actionLockCount);
        if (demon.randomI <= 25) {
            assertEquals("up", demon.direction);
        }
        if (demon.randomI > 25 && demon.randomI <= 50) {
            assertEquals("down", demon.direction);
        }
        if (demon.randomI > 50 && demon.randomI <= 75) {
            assertEquals("left", demon.direction);
        }
        if (demon.randomI > 75 && demon.randomI <= 100) {
            assertEquals("right", demon.direction);
        }
    }

    @Test
    public void testSetActionOnPathFalseActionFalse() {
        demon.direction = "up";
        demon.onPath = false;
        demon.actionLockCount = 0;
        demon.setAction();
        assertEquals(1, demon.actionLockCount);
    }
}

