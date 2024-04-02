package Documents.object;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Documents.main.GamePanel;

public class BloodStainTest {

    private GamePanel gp;
    private Bloodstain bloodstain;

    @BeforeAll
    public void setUp() {
        gp = new GamePanel();
        bloodstain = new Bloodstain(gp);
    }

    @Test
    public void testConstructor() {
        assertEquals("bloodstain", bloodstain.type);
        assertNotNull(bloodstain.img);
    }
}
