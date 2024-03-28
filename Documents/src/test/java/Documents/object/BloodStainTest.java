package Documents.object;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Documents.main.GamePanel;

public class BloodstainTest {

    private GamePanel gp;
    private Bloodstain bloodstain;

    @Before
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
