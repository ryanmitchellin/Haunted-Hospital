package Documents.object;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Documents.main.GamePanel;

public class StairTest {

    private GamePanel gp;
    private Stair stair;

    @Before
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
