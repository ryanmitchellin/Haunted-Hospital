import org.junit.Before;
import org.junit.Test;
import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

public class KeyControlTest {

    private KeyControl keyControl;
    private GamePanel gamePanel;

    @Before
    public void setUp() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        gamePanel.gameState = gamePanel.playState;
    }

    @Test
    public void testUpKeyPressed() {
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_UP, 'U');
        keyControl.keyPressed(keyEvent);
        assertTrue(keyControl.upPressed);
    }

    @Test
    public void testUpKeyReleased() {
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_UP, 'U');
        keyControl.keyPressed(keyEvent);
        assertFalse(keyControl.upPressed);
    }
}