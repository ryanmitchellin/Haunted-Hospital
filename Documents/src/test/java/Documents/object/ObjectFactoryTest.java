package Documents.object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Documents.main.GamePanel;

public class ObjectFactoryTest {

    @Test
    public void testDrawObjectWithinBoundary() {
        ObjectFactory objectFactory;
        GamePanel gp;
        objectFactory = new ObjectFactory();
        gp = new GamePanel();
        // pre-setup
        objectFactory.worldX = 50;
        objectFactory.worldY = 50;
        gp.mainCharacter.wxPos = 10;
        gp.mainCharacter.wyPos = 10;
        // gp.mainCharacter.screenX = 5;
        // gp.mainCharacter.screenY = 5;
        gp.tileSize = 20;
        objectFactory.img = new BufferedImage(48, 48, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = (Graphics2D) objectFactory.img.getGraphics();

        objectFactory.draw(g2, gp);

        // boolean isDraw = objectFactory.worldX + gp.tileSize > gp.mainCharacter.wxPos - gp.mainCharacter.screenX && 
        //                  objectFactory.worldX - gp.tileSize < gp.mainCharacter.wxPos + gp.mainCharacter.screenX &&
        //                  objectFactory.worldY + gp.tileSize > gp.mainCharacter.wyPos - gp.mainCharacter.screenY &&
        //                  objectFactory.worldY - gp.tileSize < gp.mainCharacter.wyPos + gp.mainCharacter.screenY;
        
        // assertTrue(isDraw);
    }
}
