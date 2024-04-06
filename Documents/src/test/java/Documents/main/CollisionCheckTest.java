package Documents.main;

import org.junit.jupiter.api.Test;

import Documents.entity.*;
import Documents.object.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class CollisionCheckTest {


    private static CollisionCheck collisionCheck;
    private static GamePanel gp;
    private static KeyControl keyControl;
    private static MainCharacter mainCharacter;

    private static int left, right, top, bottom;
    private static int leftCol, rightCol, topRow, bottomRow;
    private static int left1, left2, right1, right2, top1, top2, bottom1, bottom2;


    @BeforeAll
    public static void setup(){
        gp = new GamePanel();
        collisionCheck = new CollisionCheck(gp);
        keyControl = new KeyControl(gp);
        mainCharacter = new MainCharacter(gp, keyControl);
        left = mainCharacter.wxPos + mainCharacter.detectionArea.x;
        right = mainCharacter.wxPos + mainCharacter.detectionArea.x + mainCharacter.detectionArea.width;
        top = mainCharacter.wyPos + mainCharacter.detectionArea.y;
        bottom = mainCharacter.wyPos + mainCharacter.detectionArea.y + mainCharacter.detectionArea.height;

        leftCol = left/gp.tileSize;
        rightCol = right/gp.tileSize;
        topRow = top/gp.tileSize;
        bottomRow = bottom/gp.tileSize;

        leftCol = (left - mainCharacter.vel)/gp.tileSize;
        left1 = gp.tileFactory.getTileMapNum(leftCol, topRow);
        left2 = gp.tileFactory.getTileMapNum(leftCol, bottomRow);

        rightCol = (right + mainCharacter.vel)/gp.tileSize;
        right1 = gp.tileFactory.getTileMapNum(rightCol, topRow);
        right2 = gp.tileFactory.getTileMapNum(rightCol, bottomRow);

        topRow = (top - mainCharacter.vel)/gp.tileSize;
        top1 = gp.tileFactory.getTileMapNum(leftCol, topRow);
        top2 = gp.tileFactory.getTileMapNum(rightCol, topRow);

        bottomRow = (bottom + mainCharacter.vel)/gp.tileSize;
        bottom1 = gp.tileFactory.getTileMapNum(leftCol, topRow);
        bottom2 = gp.tileFactory.getTileMapNum(rightCol, topRow);


    }

    

    @Test
    public void tileCheckLeftTest1(){
        
        mainCharacter.direction = "left";
        gp.tileFactory.getTile(left1).collision = true;
        collisionCheck.tileCheck(mainCharacter);
        assertEquals(true, mainCharacter.isCollision);
        gp.tileFactory.getTile(left1).collision = false;
        mainCharacter.isCollision = false;
    }

    @Test
    public void tileCheckLeftTest2(){

        mainCharacter.direction = "left";
        gp.tileFactory.getTile(left2).collision = true;
        collisionCheck.tileCheck(mainCharacter);
        assertEquals(true, mainCharacter.isCollision);
        gp.tileFactory.getTile(left2).collision = false;
        mainCharacter.isCollision = false;
    }
    

    @Test
    public void tileCheckLeftTest3(){
        mainCharacter.direction = "left";
        collisionCheck.tileCheck(mainCharacter);
        assertEquals(false, mainCharacter.isCollision);
    }

    @Test
    public void tileCheckRightTest1(){
        
        mainCharacter.direction = "right";
        gp.tileFactory.getTile(right1).collision = true;
        collisionCheck.tileCheck(mainCharacter);
        assertEquals(true, mainCharacter.isCollision);
        gp.tileFactory.getTile(right1).collision = false;
        mainCharacter.isCollision = false;
    }

    @Test
    public void tileCheckRightTest2(){
        
        mainCharacter.direction = "right";
        gp.tileFactory.getTile(right2).collision = true;
        collisionCheck.tileCheck(mainCharacter);
        assertEquals(true, mainCharacter.isCollision);
        gp.tileFactory.getTile(right2).collision = false;
        mainCharacter.isCollision = false;
    }

    @Test
    public void tileCheckRightTest3(){
        
        mainCharacter.direction = "right";
        collisionCheck.tileCheck(mainCharacter);
        assertEquals(false, mainCharacter.isCollision);
    }

    @Test
    public void tileCheckUpTest1(){
        
        mainCharacter.direction = "up";
        gp.tileFactory.getTile(top1).collision = true;
        collisionCheck.tileCheck(mainCharacter);
        assertEquals(true, mainCharacter.isCollision);
        gp.tileFactory.getTile(top1).collision = false;
        mainCharacter.isCollision = false;
    }

    @Test
    public void tileCheckUpTest2(){
        
        mainCharacter.direction = "up";
        gp.tileFactory.getTile(top2).collision = true;
        collisionCheck.tileCheck(mainCharacter);
        assertEquals(true, mainCharacter.isCollision);
        gp.tileFactory.getTile(top2).collision = false;
        mainCharacter.isCollision = false;
    }

    @Test
    public void tileCheckUpTest3(){
        
        mainCharacter.direction = "up";
        collisionCheck.tileCheck(mainCharacter);
        assertEquals(false, mainCharacter.isCollision);
    }

    @Test
    public void tileCheckDownTest1(){
        
        mainCharacter.direction = "down";
        gp.tileFactory.getTile(bottom1).collision = true;
        collisionCheck.tileCheck(mainCharacter);
        assertEquals(true, mainCharacter.isCollision);
        gp.tileFactory.getTile(bottom1).collision = false;
        mainCharacter.isCollision = false;
    }

    @Test
    public void tileCheckDownTest2(){
        
        mainCharacter.direction = "down";
        gp.tileFactory.getTile(bottom2).collision = true;
        collisionCheck.tileCheck(mainCharacter);
        assertEquals(true, mainCharacter.isCollision);
        gp.tileFactory.getTile(bottom2).collision = false;
        mainCharacter.isCollision = false;
    }

    @Test
    public void tileCheckDownTest3(){
        
        mainCharacter.direction = "down";
        collisionCheck.tileCheck(mainCharacter);
        assertEquals(false, mainCharacter.isCollision);
    }

    @Test
    public void objCheckLeft1(){

        gp.obj[0] = new Bloodstain(gp);
        gp.obj[0].worldX = left;
        gp.obj[0].worldY = mainCharacter.wyPos;
        gp.obj[0].isCollision = true;
        mainCharacter.direction = "left";
        collisionCheck.objCheck(mainCharacter, true);
        assertEquals(true, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.obj[0] = null;
        

    }

    @Test
    public void objCheckLeft2(){

        gp.obj[0] = new Bloodstain(gp);
        gp.obj[0].worldX = left;
        gp.obj[0].worldY = mainCharacter.wyPos;
        gp.obj[0].isCollision = false;
        mainCharacter.direction = "left";
        collisionCheck.objCheck(mainCharacter, true);
        assertEquals(false, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.obj[0] = null;
        

    }

    @Test
    public void objCheckLeft3(){

        gp.obj[0] = new Bloodstain(gp);
        gp.obj[0].worldX = 0;
        gp.obj[0].worldY = 0;
        gp.obj[0].isCollision = true;
        mainCharacter.direction = "left";
        collisionCheck.objCheck(mainCharacter, true);
        assertEquals(false, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.obj[0] = null;
        

    }

    @Test
    public void objCheckLeft4(){

        gp.obj[0] = new Bloodstain(gp);
        gp.obj[0].worldX = left;
        gp.obj[0].worldY = mainCharacter.wyPos;
        gp.obj[0].isCollision = true;
        mainCharacter.direction = "left";
        collisionCheck.objCheck(mainCharacter, false);
        assertEquals(true, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.obj[0] = null;
        

    }


    @Test
    public void objCheckRight1(){

        gp.obj[0] = new Bloodstain(gp);
        gp.obj[0].worldX = right;
        gp.obj[0].worldY = top;
        gp.obj[0].isCollision = true;
        mainCharacter.direction = "right";
        collisionCheck.objCheck(mainCharacter, true);
        assertEquals(true, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.obj[0] = null;
        

    }

    @Test
    public void objCheckRight2(){

        gp.obj[0] = new Bloodstain(gp);
        gp.obj[0].worldX = right;
        gp.obj[0].worldY = mainCharacter.wyPos;
        gp.obj[0].isCollision = false;
        mainCharacter.direction = "right";
        collisionCheck.objCheck(mainCharacter, true);
        assertEquals(false, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.obj[0] = null;
        

    }

    @Test
    public void objCheckRight3(){

        gp.obj[0] = new Bloodstain(gp);
        gp.obj[0].worldX = 0;
        gp.obj[0].worldY = 0;
        gp.obj[0].isCollision = true;
        mainCharacter.direction = "right";
        collisionCheck.objCheck(mainCharacter, true);
        assertEquals(false, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.obj[0] = null;
        

    }

    @Test
    public void objCheckRight4(){

        gp.obj[0] = new Bloodstain(gp);
        gp.obj[0].worldX = right;
        gp.obj[0].worldY = mainCharacter.wyPos;
        gp.obj[0].isCollision = true;
        mainCharacter.direction = "right";
        collisionCheck.objCheck(mainCharacter, false);
        assertEquals(true, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.obj[0] = null;
        

    }

    @Test
    public void objCheckUp1(){

        gp.obj[0] = new Bloodstain(gp);
        gp.obj[0].worldX = mainCharacter.wxPos;
        gp.obj[0].worldY = top;
        gp.obj[0].isCollision = true;
        mainCharacter.direction = "up";
        collisionCheck.objCheck(mainCharacter, true);
        assertEquals(true, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.obj[0] = null;
        

    }

    @Test
    public void objCheckUp2(){

        gp.obj[0] = new Bloodstain(gp);
        gp.obj[0].worldX = mainCharacter.wxPos;
        gp.obj[0].worldY = top;
        gp.obj[0].isCollision = false;
        mainCharacter.direction = "up";
        collisionCheck.objCheck(mainCharacter, true);
        assertEquals(false, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.obj[0] = null;
        

    }

    @Test
    public void objCheckUp3(){

        gp.obj[0] = new Bloodstain(gp);
        gp.obj[0].worldX = 0;
        gp.obj[0].worldY = 0;
        gp.obj[0].isCollision = true;
        mainCharacter.direction = "up";
        collisionCheck.objCheck(mainCharacter, true);
        assertEquals(false, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.obj[0] = null;
        

    }

    @Test
    public void objCheckUp4(){

        gp.obj[0] = new Bloodstain(gp);
        gp.obj[0].worldX = mainCharacter.wxPos;
        gp.obj[0].worldY = top;
        gp.obj[0].isCollision = true;
        mainCharacter.direction = "up";
        collisionCheck.objCheck(mainCharacter, false);
        assertEquals(true, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.obj[0] = null;
        

    }


    @Test
    public void objCheckDown1(){

        gp.obj[0] = new Bloodstain(gp);
        gp.obj[0].worldX = mainCharacter.wxPos;
        gp.obj[0].worldY = bottom;
        gp.obj[0].isCollision = true;
        mainCharacter.direction = "down";
        collisionCheck.objCheck(mainCharacter, true);
        assertEquals(true, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.obj[0] = null;
        

    }

    @Test
    public void objCheckDown2(){

        gp.obj[0] = new Bloodstain(gp);
        gp.obj[0].worldX = mainCharacter.wxPos;
        gp.obj[0].worldY = bottom;
        gp.obj[0].isCollision = false;
        mainCharacter.direction = "down";
        collisionCheck.objCheck(mainCharacter, true);
        assertEquals(false, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.obj[0] = null;
        

    }

    @Test
    public void objCheckDown3(){

        gp.obj[0] = new Bloodstain(gp);
        gp.obj[0].worldX = 0;
        gp.obj[0].worldY = 0;
        gp.obj[0].isCollision = true;
        mainCharacter.direction = "down";
        collisionCheck.objCheck(mainCharacter, true);
        assertEquals(false, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.obj[0] = null;
        

    }

    @Test
    public void objCheckDown4(){

        gp.obj[0] = new Bloodstain(gp);
        gp.obj[0].worldX = mainCharacter.wxPos;
        gp.obj[0].worldY = bottom;
        gp.obj[0].isCollision = true;
        mainCharacter.direction = "down";
        collisionCheck.objCheck(mainCharacter, false);
        assertEquals(true, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.obj[0] = null;
        

    }

    @Test
    public void entityCheckLeft1(){

        gp.monster[0] = new Ghost(gp);
        gp.monster[0].wxPos = left;
        gp.monster[0].wyPos = mainCharacter.wyPos;
        gp.monster[0].isCollision = true;
        mainCharacter.direction = "left";
        collisionCheck.entityCheck(mainCharacter, gp.monster);
        assertEquals(true, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.monster[0] = null;
    }

    @Test
    public void entityCheckLeft2(){

        gp.monster[0] = new Ghost(gp);
        gp.monster[0].wxPos = 0;
        gp.monster[0].wyPos = 0;
        gp.monster[0].isCollision = true;
        mainCharacter.direction = "left";
        collisionCheck.entityCheck(mainCharacter, gp.monster);
        assertEquals(false, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.monster[0] = null;
    }

    @Test
    public void entityCheckRight1(){

        gp.monster[0] = new Ghost(gp);
        gp.monster[0].wxPos = right;
        gp.monster[0].wyPos = mainCharacter.wyPos;
        gp.monster[0].isCollision = true;
        mainCharacter.direction = "right";
        collisionCheck.entityCheck(mainCharacter, gp.monster);
        assertEquals(true, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.monster[0] = null;
    }

    @Test
    public void entityCheckRight2(){

        gp.monster[0] = new Ghost(gp);
        gp.monster[0].wxPos = 0;
        gp.monster[0].wyPos = 0;
        gp.monster[0].isCollision = true;
        mainCharacter.direction = "right";
        collisionCheck.entityCheck(mainCharacter, gp.monster);
        assertEquals(false, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.monster[0] = null;
    }

    @Test
    public void entityCheckUp1(){

        gp.monster[0] = new Ghost(gp);
        gp.monster[0].wxPos = mainCharacter.wxPos;
        gp.monster[0].wyPos = top;
        gp.monster[0].isCollision = true;
        mainCharacter.direction = "up";
        collisionCheck.entityCheck(mainCharacter, gp.monster);
        assertEquals(true, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.monster[0] = null;
    }

    @Test
    public void entityCheckUp2(){

        gp.monster[0] = new Ghost(gp);
        gp.monster[0].wxPos = 0;
        gp.monster[0].wyPos = 0;
        gp.monster[0].isCollision = true;
        mainCharacter.direction = "up";
        collisionCheck.entityCheck(mainCharacter, gp.monster);
        assertEquals(false, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.monster[0] = null;
    }

    // @Test
    // public void entityCheckLeft1(){

    //     gp.monster[0] = new Ghost(gp);
    //     gp.monster[0].wxPos = left;
    //     gp.monster[0].wyPos = top;
    //     gp.monster[0].isCollision = true;
    //     mainCharacter.direction = "left";
    //     collisionCheck.entityCheck(mainCharacter, gp.monster);
    //     assertEquals(true, mainCharacter.isCollision);
    //     mainCharacter.isCollision = false;
    //     gp.monster[0] = null;
    // }

    @Test
    public void entityCheckDown1(){

        gp.monster[0] = new Ghost(gp);
        gp.monster[0].wxPos = mainCharacter.wxPos;
        gp.monster[0].wyPos = bottom;
        gp.monster[0].isCollision = true;
        mainCharacter.direction = "down";
        collisionCheck.entityCheck(mainCharacter, gp.monster);
        assertEquals(true, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.monster[0] = null;
    }
    

    @Test
    public void entityCheckDown2(){

        gp.monster[0] = new Ghost(gp);
        gp.monster[0].wxPos = 0;
        gp.monster[0].wyPos = 0;
        gp.monster[0].isCollision = true;
        mainCharacter.direction = "down";
        collisionCheck.entityCheck(mainCharacter, gp.monster);
        assertEquals(false, mainCharacter.isCollision);
        mainCharacter.isCollision = false;
        gp.monster[0] = null;
    }

    
}