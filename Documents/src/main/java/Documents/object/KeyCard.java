package Documents.object;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class KeyCard extends ObjectFactory {
	public KeyCard() {
		this.type = "keyCard";

		try {
			img = ImageIO.read(getClass().getResourceAsStream("/objects/cardkey.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}