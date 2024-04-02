package Documents.object;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Documents.main.GamePanel;

public class StairTest {

    private GamePanel gp;
    private Stair stair;

    @BeforeAll
    public void setUp() {
        gp = new GamePanel();
        stair = new Stair(gp);
    }

    @Test
    public void testConstructor() {
        assertEquals("stair", stair.type);
        assertNotNull(stair.img);
    }
}
