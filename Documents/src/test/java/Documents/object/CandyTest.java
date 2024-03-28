package Documents.object;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Documents.main.GamePanel;

public class CandyTest {

    private GamePanel gp;
    private Candy candy;

    @Before
    public void setUp() {
        gp = new GamePanel();
        candy = new Candy(gp);
    }

    @Test
    public void testConstructor() {
        assertEquals("candy", candy.type);
        assertNotNull(candy.img);
    }
}
