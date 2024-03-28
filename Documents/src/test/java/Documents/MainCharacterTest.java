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
}