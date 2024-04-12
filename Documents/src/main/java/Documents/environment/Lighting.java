package Documents.environment;

import Documents.main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

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

        Color color[] = new Color[5];
        float[] fraction = new float[5];

        color[0] = new Color(0,0,0,0f);
        color[1] = new Color(0,0,0,0.25f);
        color[2] = new Color(0,0,0,0.5f);
        color[3] = new Color(0,0,0,0.98f);

        fraction[0] = 0f;
        fraction[1] = 0.25f;
        fraction[2] = 0.5f;
        fraction[3] = 0.75f;
        fraction[4] = 1f;

        // Create gradian
        RadialGradientPaint gradientPaint = new RadialGradientPaint(centerX, centerY, (circleSize/2), fraction, color);

        g2.setPaint();
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
