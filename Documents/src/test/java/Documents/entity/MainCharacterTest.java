package Documents.entity;

import Documents.main.GamePanel;
import Documents.main.KeyControl;
import Documents.main.UserInterface;
import Documents.tile.TileFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class MainCharacterTest {

    private static KeyControl keyControl;
    private static GamePanel gamePanel;
    private static MainCharacter mainCharacter;
    private static int left;
    private static Graphics2D g2;

    @BeforeAll
    public static void setUp() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        mainCharacter = new MainCharacter(gamePanel, keyControl);
        mainCharacter.dialogues = new String[]{"Hello", "Goodbye", null};
        gamePanel.tileSize = 48;
        gamePanel.tileFactory = new TileFactory(gamePanel);
        keyControl.upPressed = false;
        keyControl.downPressed = false;
        keyControl.leftPressed = false;
        keyControl.rightPressed = false;
        left = mainCharacter.wxPos + mainCharacter.detectionArea.x;
        g2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB).createGraphics();

    }

    @Test
    public void testUpdateStallHigh(){
        keyControl.upPressed = true;
        mainCharacter.stall = 40;
        mainCharacter.update();
        assertEquals(39, mainCharacter.stall);
        keyControl.upPressed = false;
    }

    @Test
    public void testUpdateStallZeroUp(){
        mainCharacter.stall = 0;
        int initialXPos = 10;
        int initialYPos = 10;
        mainCharacter.wxPos = initialXPos;
        mainCharacter.wyPos = initialYPos;
        keyControl.upPressed = true;
        mainCharacter.direction = "up";
        mainCharacter.vel = 5;
        mainCharacter.update();
        assertEquals(initialYPos-5, mainCharacter.wyPos);
        mainCharacter.direction = "down";
        mainCharacter.update();
        keyControl.upPressed = false;
    }

    @Test
    public void testUpdateStallZeroDown(){
        mainCharacter.stall = 0;
        int initialXPos = 10;
        int initialYPos = 10;
        mainCharacter.wxPos = initialXPos;
        mainCharacter.wyPos = initialYPos;
        keyControl.downPressed = true;
        mainCharacter.direction = "down";
        mainCharacter.vel = 5;
        mainCharacter.update();
        assertEquals(initialYPos+5, mainCharacter.wyPos);
        mainCharacter.direction = "up";
        mainCharacter.update();
        keyControl.downPressed = false;
    }

    @Test
    public void testUpdateStallZeroLeft(){
        mainCharacter.stall = 0;
        int initialXPos = 10;
        int initialYPos = 10;
        mainCharacter.wxPos = initialXPos;
        mainCharacter.wyPos = initialYPos;
        keyControl.leftPressed = true;
        mainCharacter.direction = "left";
        mainCharacter.vel = 5;
        mainCharacter.update();
        assertEquals(initialYPos-5, mainCharacter.wxPos);
        mainCharacter.direction = "right";
        mainCharacter.update();
        keyControl.leftPressed = false;
    }

    @Test
    public void testUpdateStallZeroRight(){
        keyControl.rightPressed = true;
        keyControl.upPressed = false;
        keyControl.downPressed = false;
        keyControl.leftPressed = false;
        mainCharacter.stall = 0;
        int initialXPos = 10;
        int initialYPos = 10;
        mainCharacter.wxPos = initialXPos;
        mainCharacter.wyPos = initialYPos;
        mainCharacter.direction = "right";
        mainCharacter.vel = 5;
        mainCharacter.update();
        assertEquals(initialXPos+5, mainCharacter.wxPos);
        mainCharacter.direction = "left";
        mainCharacter.update();
        keyControl.rightPressed = false;
    }

    @Test
    public void testUpdateUpPressed(){
        keyControl.upPressed = true;
        keyControl.downPressed = false;
        keyControl.rightPressed = false;
        keyControl.leftPressed = false;
        mainCharacter.update();
        assertEquals("up", mainCharacter.direction);
        keyControl.upPressed = false;
        mainCharacter.update();
    }

    @Test
    public void testUpdateDownPressed(){
        keyControl.downPressed = true;
        keyControl.upPressed = false;
        keyControl.rightPressed = false;
        keyControl.leftPressed = false;
        mainCharacter.update();
        assertEquals("down", mainCharacter.direction);
        keyControl.downPressed = false;
    }

    @Test
    public void testUpdateLeftPressed(){
        keyControl.leftPressed = true;
        keyControl.upPressed = false;
        keyControl.rightPressed = false;
        keyControl.downPressed = false;
        UserInterface.score = 0;
        mainCharacter.update();
        assertEquals("left", mainCharacter.direction);
        keyControl.leftPressed = false;
        mainCharacter.update();
    }

    @Test
    public void testUpdateRightPressed(){
        keyControl.rightPressed = true;
        keyControl.upPressed = false;
        keyControl.downPressed = false;
        keyControl.leftPressed = false;
        UserInterface.score = 100;
        mainCharacter.update();
        assertEquals("right", mainCharacter.direction);
        keyControl.rightPressed = false;
        mainCharacter.update();
    }

    @Test
    public void testUpdateWithoutCollisionMovementNone(){
        int initialXPos = 10;
        int initialYPos = 10;
        mainCharacter.wxPos = initialXPos;
        mainCharacter.wyPos = initialYPos;
        mainCharacter.vel = 0;
        mainCharacter.direction = "up"; // Direction is set but should not matter due to zero velocity
        mainCharacter.isCollision = false;
        mainCharacter.update();
        assertEquals(initialXPos, mainCharacter.wxPos);
        assertEquals(initialYPos, mainCharacter.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionDirectionNone(){
        int initialXPos = 10;
        int initialYPos = 10;
        mainCharacter.wxPos = initialXPos;
        mainCharacter.wyPos = initialYPos;
        mainCharacter.vel = 5;
        mainCharacter.direction = ""; // Direction is set but should not matter due to zero velocity
        mainCharacter.isCollision = false;
        mainCharacter.update();
        assertEquals(initialXPos, mainCharacter.wxPos);
        assertEquals(initialYPos, mainCharacter.wyPos);
    }

    @Test
    public void testUpdateSpriteCountSame() {
        keyControl.upPressed = true;
        mainCharacter.direction = "up";
        mainCharacter.spriteCount = 1;
        mainCharacter.update();
        assertEquals(2, mainCharacter.spriteCount);
    }

    @Test
    public void testUpdateSpriteCountIncrement() {
        keyControl.upPressed = true;
        mainCharacter.direction = "up";
        mainCharacter.spriteCount = 11;
        mainCharacter.spriteNum = 1;
        mainCharacter.update();
        assertEquals(0, mainCharacter.spriteCount);
    }

    @Test
    public void testUpdateSpriteNumToggles() {
        keyControl.upPressed = true;
        mainCharacter.direction = "up";
        mainCharacter.spriteCount = 15;
        mainCharacter.spriteNum = 1;
        mainCharacter.update();
        assertEquals(2, mainCharacter.spriteNum);
        mainCharacter.direction = "up";
        mainCharacter.spriteCount = 15;
        mainCharacter.spriteNum = 2;
        mainCharacter.update();
        assertEquals(1, mainCharacter.spriteNum);
        mainCharacter.direction = "up";
        mainCharacter.spriteCount = 15;
        mainCharacter.spriteNum = 3;
        mainCharacter.update();
        assertEquals(3, mainCharacter.spriteNum);
    }

    @Test
    public void testMainCharacterNull() {
        assertNotNull(mainCharacter.gp);
    }

    @Test
    public void testInteractMob() {
        int i = 10;
        mainCharacter.interactMob(i);
        assertEquals(gamePanel.gameState, gamePanel.deathState);
    }

    @Test
    public void testInteractMobFail() {
        int i = 999;
        mainCharacter.interactMob(i);
        assertNotEquals(gamePanel.gameState, gamePanel.deathState);
    }

    @Test
    public void testGetMainWxPos(){
        int testX = mainCharacter.getMainWxPos();
        assertEquals(testX, mainCharacter.wxPos);
    }

    @Test
    public void testGetMainWyPos() {
        int testY = mainCharacter.getMainWyPos();
        assertEquals(testY, mainCharacter.wyPos);
    }

    @Test
    public void testDraw(){
        //576
        //1296

        mainCharacter.wxPos = 936;

        mainCharacter.wyPos = 840;

        gamePanel.tileSize = 361;

        mainCharacter.direction = "left";
        mainCharacter.spriteNum = 1;
        mainCharacter.draw(g2);
        mainCharacter.spriteNum = 2;
        mainCharacter.draw(g2);
        mainCharacter.direction = "right";
        mainCharacter.spriteNum = 1;
        mainCharacter.draw(g2);
        mainCharacter.spriteNum = 2;
        mainCharacter.draw(g2);
        mainCharacter.direction = "up";
        mainCharacter.spriteNum = 1;
        mainCharacter.draw(g2);
        mainCharacter.spriteNum = 2;
        mainCharacter.draw(g2);
        mainCharacter.direction = "down";
        mainCharacter.spriteNum = 1;
        mainCharacter.draw(g2);
        mainCharacter.spriteNum = 2;
        mainCharacter.draw(g2);
    }
}