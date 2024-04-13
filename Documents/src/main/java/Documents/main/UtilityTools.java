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


	public static int spriteCountCalculations(int spriteCount, int spriteNum) {
		spriteCount++;
		if (spriteCount > 10) {
			spriteNum = (spriteNum == 1) ? 2 : 1;
			spriteCount = 0; // Reset the sprite count
		}
		return spriteNum; // Return the updated sprite number
	}

}