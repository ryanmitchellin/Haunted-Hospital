package Documents.object;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Documents.main.GamePanel;

public class KeyCardTest {

    private GamePanel gp;
    private KeyCard keycard;

    @Before
    public void setUp() {
        gp = new GamePanel();
        keyCard = new KeyCard(gp);
    }

    @Test
    public void testConstructor() {
        assertEquals("keyCard", keyCard.type);
        assertNotNull(keyCard.img);
    }
}
