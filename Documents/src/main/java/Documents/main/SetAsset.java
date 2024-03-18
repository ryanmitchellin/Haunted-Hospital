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

	}

	public void setNpc() {
		gp.npc[0] = new Npc(gp);
		gp.npc[0].worldX = gp.tileSize*21;
		gp.npc[0].worldY = gp.tileSize*21;

	}
}