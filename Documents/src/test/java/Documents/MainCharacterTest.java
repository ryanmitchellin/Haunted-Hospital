package Documents.entity;

import Documents.main.GamePanel;
import Documents.main.KeyControl;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainCharacterTest {

    private MainCharacter mainCharacter;
    private GamePanel gamePanel;
    private KeyControl keyControl;

    @Before
    public void setUp() {
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        mainCharacter = new MainCharacter(gamePanel, keyControl);
    }

    @Test
    public void testUpdate() {
        keyControl.upPressed = true;
        mainCharacter.update();
        assertEquals("up", mainCharacter.direction);

        keyControl.downPressed = true;
        mainCharacter.update();
        assertEquals("down", mainCharacter.direction);

        keyControl.leftPressed = true;
        mainCharacter.update();
        assertEquals("left", mainCharacter.direction);

        keyControl.rightPressed = true;
        mainCharacter.update();
        assertEquals("right", mainCharacter.direction);
    }
}