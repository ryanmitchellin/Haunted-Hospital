package Documents.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Documents.main.GamePanel;

public class Bloodstain extends ObjectFactory{
    GamePanel gp;

    public Bloodstain(GamePanel gp) {
		this.gp = gp;
		this.type = "bloodstain";

		try {
			img = ImageIO.read(getClass().getResourceAsStream("/objects/trap.png"));
			tools.scaleImg(img, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
