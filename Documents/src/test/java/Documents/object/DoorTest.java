package Documents.object;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Documents.main.GamePanel;

public class DoorTest {

    private GamePanel gp;
    private Door door;

    @BeforeAll
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
