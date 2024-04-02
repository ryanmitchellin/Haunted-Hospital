package Documents.object;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Documents.main.GamePanel;

public class CandyTest {

    private GamePanel gp;
    private Candy candy;

    @BeforeAll
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
