package Documents.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Documents.main.GamePanel;

public class Candy extends ObjectFactory{
    GamePanel gp;

    public Candy(GamePanel gp) {
		this.gp = gp;
		this.type = "candy";

		try {
			img = ImageIO.read(getClass().getResourceAsStream("/objects/reward.png"));
			tools.scaleImg(img, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
