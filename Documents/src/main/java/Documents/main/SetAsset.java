package Documents.main;

import Documents.main.GamePanel;
//list of object class
import Documents.object.KeyCard;
import Documents.object.Door;
import Documents.object.Stair;

public class SetAsset {
	GamePanel gp;

	public SetAsset (GamePanel gp) {
		this.gp = gp;
	}

	public void setObj() {
		//setting the keycard information that will be displayed on screen
		gp.obj[0] = new KeyCard(gp);
		gp.obj[0].worldX = 38 * gp.tileSize;
		gp.obj[0].worldY = 27 * gp.tileSize;

		gp.obj[1] = new KeyCard(gp);
		gp.obj[1].worldX = 19 * gp.tileSize;
		gp.obj[1].worldY = 5 * gp.tileSize;

		gp.obj[2] = new KeyCard(gp);
		gp.obj[2].worldX = 37 * gp.tileSize;
		gp.obj[2].worldY = 12* gp.tileSize;

		gp.obj[3] = new KeyCard(gp);
		gp.obj[3].worldX = 17 * gp.tileSize;
		gp.obj[3].worldY = 28* gp.tileSize;


		//door
		gp.obj[4] = new Door(gp);
		gp.obj[4].worldX = 11 * gp.tileSize;
		gp.obj[4].worldY = 10* gp.tileSize;

		//start
		gp.obj[5] = new Stair(gp);
		gp.obj[5].worldX = 11 * gp.tileSize;
		gp.obj[5].worldY = 9* gp.tileSize;
	}
}