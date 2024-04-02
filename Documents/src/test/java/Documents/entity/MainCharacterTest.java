package Documents.entity;

import Documents.main.GamePanel;
import Documents.main.KeyControl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainCharacterTest {

    @Test
    public void testUpdate() {
        MainCharacter mainCharacter;
        GamePanel gamePanel;
        KeyControl keyControl;
        gamePanel = new GamePanel();
        keyControl = new KeyControl(gamePanel);
        mainCharacter = new MainCharacter(gamePanel, keyControl);

        keyControl.upPressed = true;
        mainCharacter.update();
        assertEquals("up", mainCharacter.direction);
        keyControl.upPressed = false;

        keyControl.downPressed = true;
        mainCharacter.update();
        assertEquals("down", mainCharacter.direction);
        keyControl.downPressed = false;

        keyControl.leftPressed = true;
        mainCharacter.update();
        assertEquals("left", mainCharacter.direction);
        keyControl.leftPressed = false;

        keyControl.rightPressed = true;
        mainCharacter.update();
        assertEquals("right", mainCharacter.direction);
        keyControl.rightPressed = false;
    }
}