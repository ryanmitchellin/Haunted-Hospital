package Documents.entity;

import Documents.SearchAI.*;
import Documents.main.GamePanel;
import Documents.tile.TileFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityTest {

    private static GamePanel gamePanel;
    private static Entity entity;
    private static int left, right, top, bottom;
    private static int leftCol, rightCol, topRow, bottomRow;
    private static int left1, left2, right1, right2, top1, top2, bottom1, bottom2;
    private static Graphics2D g2; 

    @BeforeAll
    public static void setUp() {
        gamePanel = new GamePanel();
        entity = new Entity(gamePanel);
        entity.dialogues = new String[]{"Hello", "Goodbye", null};
        gamePanel.tileSize = 48;
        gamePanel.tileFactory = new TileFactory(gamePanel);

        left = entity.wxPos + entity.detectionArea.x;
        right = entity.wxPos + entity.detectionArea.x + entity.detectionArea.width;
        top = entity.wyPos + entity.detectionArea.y;
        bottom = entity.wyPos + entity.detectionArea.y + entity.detectionArea.height;

        leftCol = left/gamePanel.tileSize;
        rightCol = right/gamePanel.tileSize;
        topRow = top/gamePanel.tileSize;
        bottomRow = bottom/gamePanel.tileSize;

        leftCol = (left - entity.vel)/gamePanel.tileSize;
        left1 = gamePanel.tileFactory.getTileMapNum(leftCol, topRow);
        left2 = gamePanel.tileFactory.getTileMapNum(leftCol, bottomRow);

        rightCol = (right + entity.vel)/gamePanel.tileSize;
        right1 = gamePanel.tileFactory.getTileMapNum(rightCol, topRow);
        right2 = gamePanel.tileFactory.getTileMapNum(rightCol, bottomRow);

        topRow = (top - entity.vel)/gamePanel.tileSize;
        top1 = gamePanel.tileFactory.getTileMapNum(leftCol, topRow);
        top2 = gamePanel.tileFactory.getTileMapNum(rightCol, topRow);

        bottomRow = (bottom + entity.vel)/gamePanel.tileSize;
        bottom1 = gamePanel.tileFactory.getTileMapNum(leftCol, topRow);
        bottom2 = gamePanel.tileFactory.getTileMapNum(rightCol, topRow);
        
        g2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB).createGraphics();
        
    }

    @Test
    public void testEntityNull() {
        assertNotNull(entity.gp);
    }

    @Test
    public void testEntityDefaultPosition() {
        GamePanel gamePanelDefault = new GamePanel();
        Entity entityDefault = new Entity(gamePanelDefault);
        assertEquals(0, entityDefault.wxPos);
        assertEquals(0, entityDefault.wyPos);
    }

    @Test
    public void testSpeakNonNull(){
        entity.dialogueIndex = 0;
        entity.speak();
        assertEquals("Hello", gamePanel.ui.currentDialogue);
        assertEquals(1, entity.dialogueIndex);
    }

    @Test
    public void testSpeakNull(){
        entity.dialogueIndex = 2;
        entity.speak();
        assertEquals("Hello", gamePanel.ui.currentDialogue);
        assertEquals(1, entity.dialogueIndex);
    }

    @Test
    public void testSpeakUp(){
        gamePanel.mainCharacter.direction = "up";
        entity.speak();
        assertEquals("down", entity.direction);
    }

    @Test
    public void testSpeakDown(){
        gamePanel.mainCharacter.direction = "down";
        entity.speak();
        assertEquals("up", entity.direction);
    }

    @Test
    public void testSpeakLeft(){
        gamePanel.mainCharacter.direction = "left";
        entity.speak();
        assertEquals("right", entity.direction);
    }

    @Test
    public void testSpeakRight(){
        gamePanel.mainCharacter.direction = "right";
        entity.speak();
        assertEquals("left", entity.direction);
    }

    @Test
    public void testSpeakDefault(){
        gamePanel.mainCharacter.direction = "";
        entity.speak();
        assertEquals(null, entity.direction);
    }

    @Test
    public void testCheckCollision(){

    }

    @Test
    public void testUpdateDistance(){
        int initialXPos = entity.wxPos;
        int initialYPos = entity.wyPos;
        entity.vel = 5;
        entity.direction = "right";
        entity.update();
        assertEquals(initialXPos + entity.vel, entity.wxPos);
        entity.direction = "left";
        entity.update();
    }

    @Test
    public void testUpdateWithoutCollisionMovementNone(){
        int initialXPos = 10;
        int initialYPos = 10;
        entity.wxPos = initialXPos;
        entity.wyPos = initialYPos;
        entity.vel = 0;
        entity.direction = "up"; // Direction is set but should not matter due to zero velocity
        entity.isCollision = false;
        entity.update();
        assertEquals(initialXPos, entity.wxPos);
        assertEquals(initialYPos, entity.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionDirectionNone(){
        int initialXPos = 10;
        int initialYPos = 10;
        entity.wxPos = initialXPos;
        entity.wyPos = initialYPos;
        entity.vel = 5;
        entity.direction = ""; // Direction is set but should not matter due to zero velocity
        entity.isCollision = false;
        entity.update();
        assertEquals(initialXPos, entity.wxPos);
        assertEquals(initialYPos, entity.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionUp(){
        entity.vel = 5;
        entity.direction = "up";
        entity.wyPos = 10;
        entity.isCollision = false;
        entity.update();
        assertEquals(5, entity.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionDown(){
        entity.vel = 5;
        entity.direction = "down";
        entity.wyPos = 10;
        entity.isCollision = false;
        entity.update();
        assertEquals(15, entity.wyPos);
    }

    @Test
    public void testUpdateWithoutCollisionLeft(){
        entity.vel = 5;
        entity.direction = "left";
        entity.wxPos = 10;
        entity.isCollision = false;
        entity.update();
        assertEquals(5, entity.wxPos);
    }

    @Test
    public void testUpdateWithoutCollisionRight(){
        entity.vel = 5;
        entity.direction = "right";
        entity.wxPos = 10;
        entity.isCollision = false;
        entity.update();
        assertEquals(15, entity.wxPos);
    }

    @Test
    public void testUpdateWithCollision(){
        entity.vel = 5;
        entity.direction = "left";
        gamePanel.tileFactory.getTile(left).collision = true;
        entity.wyPos = 10;
        entity.update();
        assertEquals(10, entity.wyPos);
        gamePanel.tileFactory.getTile(left).collision = false;
    }

    @Test
    public void testUpdateSpriteCountSame() {
        entity.direction = "up";
        entity.spriteCount = 1;
        entity.update();
        assertEquals(2, entity.spriteCount);
    }

    @Test
    public void testUpdateSpriteCountIncrement() {
        entity.direction = "up";
        entity.spriteCount = 11;
        entity.spriteNum = 1;
        entity.update();
        assertEquals(0, entity.spriteCount);
    }

    @Test
    public void testUpdateSpriteNumToggles() {
        entity.direction = "up";
        entity.spriteCount = 15;
        entity.spriteNum = 1;
        entity.update();
        assertEquals(2, entity.spriteNum);
        entity.direction = "up";
        entity.spriteCount = 15;
        entity.spriteNum = 2;
        entity.update();
        assertEquals(1, entity.spriteNum);
        entity.direction = "up";
        entity.spriteCount = 15;
        entity.spriteNum = 3;
        entity.update();
        assertEquals(3, entity.spriteNum);
    }

    @Test
    public void testDraw(){
        //576
        //1296

        entity.wxPos = 936;
        
        entity.wyPos = 840;

        gamePanel.tileSize = 361;

        // System.out.println(entity.wyPos + " " + gamePanel.tileSize + " " + gamePanel.mainCharacter.wyPos +  " " + gamePanel.mainCharacter.screenY);

        entity.direction = "left";
        entity.spriteNum = 1;
        entity.draw(g2);
        entity.spriteNum = 2;
        entity.draw(g2);
        entity.direction = "right";
        entity.spriteNum = 1;
        entity.draw(g2);
        entity.spriteNum = 2;
        entity.draw(g2);
        entity.direction = "up";
        entity.spriteNum = 1;
        entity.draw(g2);
        entity.spriteNum = 2;
        entity.draw(g2);
        entity.direction = "down";
        entity.spriteNum = 1;
        entity.draw(g2);
        entity.spriteNum = 2;
        entity.draw(g2);
    }

    @Test
    public void testSetup(){


    }

    @Test
    public void testSearchPath(){

        

        int goalColumn = 25;
        int goalRow = 25;
        
        int successfulX = -1;
        int successfulY = -1;
        

        entity.wxPos = 625;
        entity.wyPos = 500;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("up", entity.direction);

        entity.wxPos = 0;
        entity.wyPos = 150;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("down", entity.direction);

        entity.wxPos = 400;
        entity.wyPos = 500;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("left", entity.direction);

        entity.wxPos = 0;
        entity.wyPos = 625;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("right", entity.direction);

        entity.wxPos = 400;
        entity.wyPos = 875;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("up", entity.direction);

        entity.wxPos = 400;
        entity.wyPos = 500;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("left", entity.direction);

        entity.wxPos = 0;
        entity.wyPos = 450;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("up", entity.direction);

        entity.wxPos = 450;
        entity.wyPos = 550;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("right", entity.direction);

        entity.wxPos = 25;
        entity.wyPos = 150;
        entity.searchPath(goalColumn, goalRow);
        assertEquals("down", entity.direction);
    
    
    }

}
