package Documents.main;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;


public class UtilityTools {
	public BufferedImage scaleImg(BufferedImage original, int width, int height) {
		BufferedImage scaledImg = new BufferedImage(width, height, original.getType());
		Graphics2D g2 = scaledImg.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();

		return scaledImg;
	}
}