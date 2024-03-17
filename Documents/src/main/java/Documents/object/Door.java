package Documents.object;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Door extends ObjectFactory {
	public Door() {
		this.type = "door";

		try {
			img = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}