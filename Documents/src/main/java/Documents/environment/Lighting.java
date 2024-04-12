package Documents.environment;

import Documents.main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {

    GamePanel gamePanel;
    BufferedImage darkFilter;

    public Lighting(GamePanel gamePanel, int circleSize) {

        // Create buffered image
        darkFilter = new BufferedImage(gamePanel.screenWidth, gamePanel.screenHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = (Graphics2D) darkFilter.getGraphics();

        // Create rectangle area
        Area screenArea = new Area(new Rectangle2D.Double(0, 0, gamePanel.screenWidth, gamePanel.screenHeight));

        // Get the center x and y of the circle of light
        int centerX = gamePanel.mainCharacter.screenX + (gamePanel.tileSize) / 2;
        int centerY = gamePanel.mainCharacter.screenY + (gamePanel.tileSize) / 2;

        // Get the top left x and y of the circle of light
        double x = centerX - gamePanel.screenWidth / 2;
        double y = centerY - gamePanel.screenHeight / 2;

        // Create a light circle shape
        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);

        // Create a light circle area
        Area lightArea = new Area(circleShape);
        screenArea.subtract(new Area(lightArea));

        // Set colour
        g2.setColor(new Color(0, 0, 0, 0.95f));

        // Draw the screen without the light area
        g2.fill(screenArea);

        g2.dispose();
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(darkFilter, 0, 0, null);

    }
}
