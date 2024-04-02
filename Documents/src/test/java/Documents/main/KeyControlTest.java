package Documents.main;

import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class KeyControlTest {

    private KeyControl keyControl;
    private GamePanel gamePanel;

    @Test
    public void testUpKeyPressed() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_UP, 'U');
        keyControl.keyPressed(keyEvent);
        assertTrue(keyControl.upPressed);
    }

    @Test
    public void testUpKeyReleased() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_UP, 'U');
        keyControl.keyPressed(keyEvent);
        keyControl.upPressed = false; //Testing only
        assertFalse(keyControl.upPressed);
    }

    @Test
    public void testDownKeyPressed() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_DOWN, 'D');
        keyControl.keyPressed(keyEvent);
        assertTrue(keyControl.downPressed);
    }

    @Test
    public void testDownKeyReleased() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_DOWN, 'D');
        keyControl.keyPressed(keyEvent);
        keyControl.downPressed = false; //Testing only
        assertFalse(keyControl.downPressed);
    }

    @Test
    public void testLeftKeyPressed() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_LEFT, 'L');
        keyControl.keyPressed(keyEvent);
        assertTrue(keyControl.leftPressed);
    }

    @Test
    public void testLeftKeyReleased() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_LEFT, 'L');
        keyControl.keyPressed(keyEvent);
        keyControl.leftPressed = false; //Testing only
        assertFalse(keyControl.leftPressed);
    }

    @Test
    public void testRightKeyPressed() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_RIGHT, 'R');
        keyControl.keyPressed(keyEvent);
        assertTrue(keyControl.rightPressed);
    }

    @Test
    public void testRightKeyReleased() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_RIGHT, 'R');
        keyControl.keyPressed(keyEvent);
        keyControl.rightPressed = false; //Testing only
        assertFalse(keyControl.rightPressed);
    }
}