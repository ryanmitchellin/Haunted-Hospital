package Documents.main;

import Documents.main.GamePanel;
//list of object class
import Documents.object.KeyCard;
import Documents.object.Door;

public class SetAsset {
	GamePanel gp;

	public SetAsset (GamePanel gp) {
		this.gp = gp;
	}

	public void setObj() {
		//setting the keycard information that will be displayed on screen
		gp.obj[0] = new KeyCard();
		gp.obj[0].worldX = 36 * gp.tileSize;
		gp.obj[0].worldY = 31 * gp.tileSize;

		gp.obj[1] = new KeyCard();
		gp.obj[1].worldX = 15 * gp.tileSize;
		gp.obj[1].worldY = 8 * gp.tileSize;

		gp.obj[2] = new KeyCard();
		gp.obj[2].worldX = 37 * gp.tileSize;
		gp.obj[2].worldY = 12* gp.tileSize;

		//door
		gp.obj[3] = new Door();
		gp.obj[3].worldX = 4 * gp.tileSize;
		gp.obj[3].worldY = 10* gp.tileSize;
	}
}