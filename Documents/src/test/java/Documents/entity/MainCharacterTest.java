package Documents.entity;

import Documents.main.GamePanel;
import Documents.main.KeyControl;
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
    private static int left, right, top, bottom;
    private static int leftCol, rightCol, topRow, bottomRow;
    private static int left1, left2, right1, right2, top1, top2, bottom1, bottom2;
    private static Graphics2D g2;

    @BeforeAll
    public static void setUp() {
        gamePanel = new GamePanel();
        mainCharacter = new MainCharacter(gamePanel, keyControl);
        mainCharacter.dialogues = new String[]{"Hello", "Goodbye", null};
        gamePanel.tileSize = 48;
        gamePanel.tileFactory = new TileFactory(gamePanel);

        left = mainCharacter.wxPos + mainCharacter.detectionArea.x;
        right = mainCharacter.wxPos + mainCharacter.detectionArea.x + mainCharacter.detectionArea.width;
        top = mainCharacter.wyPos + mainCharacter.detectionArea.y;
        bottom = mainCharacter.wyPos + mainCharacter.detectionArea.y + mainCharacter.detectionArea.height;

        leftCol = left/gamePanel.tileSize;
        rightCol = right/gamePanel.tileSize;
        topRow = top/gamePanel.tileSize;
        bottomRow = bottom/gamePanel.tileSize;

        leftCol = (left - mainCharacter.vel)/gamePanel.tileSize;
        left1 = gamePanel.tileFactory.getTileMapNum(leftCol, topRow);
        left2 = gamePanel.tileFactory.getTileMapNum(leftCol, bottomRow);

        rightCol = (right + mainCharacter.vel)/gamePanel.tileSize;
        right1 = gamePanel.tileFactory.getTileMapNum(rightCol, topRow);
        right2 = gamePanel.tileFactory.getTileMapNum(rightCol, bottomRow);

        topRow = (top - mainCharacter.vel)/gamePanel.tileSize;
        top1 = gamePanel.tileFactory.getTileMapNum(leftCol, topRow);
        top2 = gamePanel.tileFactory.getTileMapNum(rightCol, topRow);

        bottomRow = (bottom + mainCharacter.vel)/gamePanel.tileSize;
        bottom1 = gamePanel.tileFactory.getTileMapNum(leftCol, topRow);
        bottom2 = gamePanel.tileFactory.getTileMapNum(rightCol, topRow);

        g2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB).createGraphics();

    }

    @Test
    public void testMainCharacterNull() {
        assertNotNull(mainCharacter.gp);
    }


    @Test
    public void testCheckCollision(){

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
    public void testInteractNpcFalse() {
        int i = 500;
        gamePanel.keyControl.enterPressed = false;
        mainCharacter.interactNpc(i);
        assertFalse(gamePanel.keyControl.enterPressed);
    }

    @Test
    public void testInteractNpcTrue() {
        int i = 0;
        gamePanel.keyControl.enterPressed = true;
        //mainCharacter.interactNpc(i);
        //assertEquals(gamePanel.gameState, gamePanel.dialogueState);
    }

    @Test
    public void testInteractNpcTrueFalse() {
        int i = 999;
        gamePanel.keyControl.enterPressed = true;
        mainCharacter.interactNpc(i);
        assertFalse(gamePanel.keyControl.enterPressed);
    }

    @Test
    public void testUpdateDistance(){
        int initialXPos = mainCharacter.wxPos;
        int initialYPos = mainCharacter.wyPos;
        mainCharacter.vel = 5;
        mainCharacter.direction = "right";
        mainCharacter.update();
        assertEquals(initialXPos + mainCharacter.vel, mainCharacter.wxPos);
        mainCharacter.direction = "left";
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
    public void testUpdateWithoutCollisionUp(){
        mainCharacter.vel = 5;
        mainCharacter.direction = "up";
        mainCharacter.wyPos = 10;
        mainCharacter.isCollision = false;
        mainCharacter.update();
        assertEquals(5, mainCharacter.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionDown(){
        mainCharacter.vel = 5;
        mainCharacter.direction = "down";
        mainCharacter.wyPos = 10;
        mainCharacter.isCollision = false;
        mainCharacter.update();
        assertEquals(15, mainCharacter.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionLeft(){
        mainCharacter.vel = 5;
        mainCharacter.direction = "left";
        mainCharacter.wxPos = 10;
        mainCharacter.isCollision = false;
        mainCharacter.update();
        assertEquals(5, mainCharacter.wxPos);
    }

    @Test
    public void testUpdateWithoutCollisionRight(){
        mainCharacter.vel = 5;
        mainCharacter.direction = "right";
        mainCharacter.wxPos = 10;
        mainCharacter.isCollision = false;
        mainCharacter.update();
        assertEquals(15, mainCharacter.wxPos);
    }

    @Test
    public void testUpdateWithCollision(){
        mainCharacter.vel = 5;
        mainCharacter.direction = "left";
        gamePanel.tileFactory.getTile(left).collision = true;
        mainCharacter.wyPos = 10;
        mainCharacter.update();
        assertEquals(10, mainCharacter.wyPos);
        gamePanel.tileFactory.getTile(left).collision = false;
    }

    @Test
    public void testUpdateSpriteCountSame() {
        mainCharacter.direction = "up";
        mainCharacter.spriteCount = 1;
        mainCharacter.update();
        assertEquals(2, mainCharacter.spriteCount);
    }

    @Test
    public void testUpdateSpriteCountIncrement() {
        mainCharacter.direction = "up";
        mainCharacter.spriteCount = 11;
        mainCharacter.spriteNum = 1;
        mainCharacter.update();
        assertEquals(0, mainCharacter.spriteCount);
    }

    @Test
    public void testUpdateSpriteNumToggles() {
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
    public void testSetup(){


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