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
}