package Documents.tile;
import Documents.main.GamePanel;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TileTest {
    private TileFactory tileFactory;
    private GamePanel gp;

    @BeforeEach
    void setUp() {
        gp = new GamePanel();
        gp.maxWCol = 10;
        gp.maxWRow = 10;
        gp.tileSize = 32;

        tileFactory = new TileFactory(gp);
    }

    @Test
    void testTileFactoryNotNull() {
        assertNotNull(tileFactory, "Tile factory should be instantiated!");
    }

    @Test
    void testTileImageLoading() {
        Tile tile = tileFactory.getTile(0);

        //checking and verifying tile index 0 (containing image and has been loaded)
        assertNotNull(tile,"tile at index 0 should not be null");
        assertNotNull(tile,"tile at index 0 should have image");

        //checking if the tile collision set is correct
        assertFalse(tile.collision, "tile at index 0 should not be collidable");
    }

    @Test
    void testLoading() {
        assertThrows(IllegalArgumentException.class,() -> {
            tileFactory.loadingMap("map/noneextisting.txt");
        }, "Illegal argument exception error should be tshrown");
    }

    @Test
    void testingForNonExistingImg() {
        assertThrows(RuntimeException.class, () -> {
            tileFactory.setup(0, "nonexsitingimage", false);
        }, "Expected runtimeexception to be thrown due to incorrect image import");
    }
} 