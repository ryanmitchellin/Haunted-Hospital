package Documents.object;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Documents.main.GamePanel;

public class KeyCardTest {

    private GamePanel gp;
    private KeyCard keyCard;

    @BeforeAll
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
