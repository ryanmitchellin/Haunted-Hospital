package Documents.object;

import Documents.main.GamePanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Stair extends ObjectFactory {
	GamePanel gp;

	public Stair(GamePanel gp) {
		this.gp = gp;
		this.type = "stair";

		try {
			img = ImageIO.read(getClass().getResourceAsStream("/objects/stair.png"));
			tools.scaleImg(img, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
		isCollision = true;
	}

}