package Documents.object;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Documents.main.GamePanel;

public class DoorTest {

    private GamePanel gp;
    private Door door;

    @Before
    public void setUp() {
        gp = new GamePanel();
        door = new Door(gp);
    }

    @Test
    public void testConstructor() {
        assertEquals("door", door.type);
        assertNotNull(door.img);
    }
}
