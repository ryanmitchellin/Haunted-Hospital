package Documents.entity;

import Documents.main.GamePanel;
import Documents.tile.TileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GhostTest {

        private GamePanel gamePanel;
        private Ghost ghost;

        @BeforeEach
        public void setUp() {
            gamePanel = new GamePanel();
            ghost = new Ghost(gamePanel);
            gamePanel.tileSize = 48;
        }

        @Test
        public void testUpdateDistance(){
            int initialXPos = 0;
            ghost.wxPos = initialXPos;
            ghost.vel = 5;
            ghost.direction = "right";
            ghost.update();
            assertEquals(initialXPos + ghost.vel, ghost.wxPos);
        }

        @Test
        public void testUpdateMovementNone(){
            int initialXPos = 10;
            int initialYPos = 10;
            ghost.wxPos = initialXPos;
            ghost.wyPos = initialYPos;
            ghost.vel = 0;
            ghost.update();
            assertEquals(initialXPos, ghost.wxPos);
            assertEquals(initialYPos, ghost.wyPos);
        }

        @Test
        public void testUpdateUp(){
            ghost.vel = 5;
            ghost.wxPos = 10;
            ghost.wyPos = 10;
            gamePanel.mainCharacter.wxPos = 10;
            gamePanel.mainCharacter.wyPos = 5;
            ghost.update();
            assertEquals(5, ghost.wyPos);
        }

        @Test
        public void testUpdateDown(){
            ghost.vel = 5;
            ghost.wxPos = 10;
            ghost.wyPos = 10;
            gamePanel.mainCharacter.wxPos = 10;
            gamePanel.mainCharacter.wyPos = 15;
            ghost.update();
            assertEquals(15, ghost.wyPos);
        }

        @Test
        public void testUpdateLeft(){
            ghost.vel = 5;
            ghost.wxPos = 10;
            ghost.wyPos = 10;
            gamePanel.mainCharacter.wxPos = 5;
            gamePanel.mainCharacter.wyPos = 10;
            ghost.update();
            assertEquals(5, ghost.wxPos);
        }

        @Test
        public void testUpdateRight(){
            ghost.vel = 5;
            ghost.wxPos = 10;
            ghost.wyPos = 10;
            gamePanel.mainCharacter.wxPos = 15;
            gamePanel.mainCharacter.wyPos = 10;
            ghost.update();
            assertEquals(15, ghost.wxPos);
        }

        @Test
        public void testUpdateWithoutDistanceExceeding1(){
            ghost.vel = 5;
            ghost.direction = "right";
            ghost.wxPos = 2500;
            ghost.wyPos = 2500;
            gamePanel.mainCharacter.wxPos = 1250;
            gamePanel.mainCharacter.wyPos = 1250;
            ghost.onPath = false;
            ghost.isCollision = false;
            ghost.update();
            assertFalse(ghost.onPath);
        }

        @Test
        public void testUpdateWithoutDistanceExceeding2(){
            ghost.vel = 5;
            ghost.direction = "right";
            ghost.wxPos = 2500;
            ghost.wyPos = 2500;
            gamePanel.mainCharacter.wxPos = 1250;
            gamePanel.mainCharacter.wyPos = 1250;
            ghost.onPath = true;
            ghost.isCollision = false;
            ghost.update();
            assertTrue(ghost.onPath);
        }

        @Test
        public void testUpdateSpriteCountSame() {
            ghost.direction = "up";
            ghost.spriteCount = 1;
            ghost.update(); // doubling everything
            assertEquals(2, ghost.spriteCount);
        }

        @Test
        public void testUpdateSpriteCountIncrement() {
            ghost.direction = "up";
            ghost.spriteCount = 11;
            ghost.spriteNum = 1;
            ghost.update();
            assertEquals(0, ghost.spriteCount);
        }

        @Test
        public void testUpdateSpriteNumToggles() {
            ghost.direction = "up";
            ghost.spriteCount = 15;
            ghost.spriteNum = 1;
            ghost.update();
            assertEquals(2, ghost.spriteNum);
            ghost.direction = "up";
            ghost.spriteCount = 15;
            ghost.spriteNum = 2;
            ghost.update();
            assertEquals(1, ghost.spriteNum);
            ghost.direction = "up";
            ghost.spriteCount = 15;
            ghost.spriteNum = 3;
            ghost.update();
            assertEquals(3, ghost.spriteNum);
        }

}
