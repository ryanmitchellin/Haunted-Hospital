package Documents.main;

import Documents.main.GamePanel;
//list of object class
import Documents.object.KeyCard;
import Documents.object.Door;
import Documents.object.Stair;
import Documents.entity.Npc;


/**
 * The SetAsset class is responsible for initializing maze assets such as key cards and doors.
 * It sets the world positions for these assets to be displayed in the maze.
 */
public class SetAsset {
	/** The GamePanel instance associated with the set assets. */
	GamePanel gp;

	/**
     * Constructs a SetAsset object with the specified GamePanel instance.
     * @param gp The GamePanel instance to associate with the SetAsset object.
     */
	public SetAsset (GamePanel gp) {
		this.gp = gp;

	}

	/**
     * Initializes the key cards and door objects in the game.
     * Sets their world positions based on tile coordinates.
     */
	public void setObj() {
		//setting the keycard information that will be displayed on screen
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

	public void setNpc() {

		gp.npc[0] = new Npc(gp);
		gp.npc[0].wxPos = gp.tileSize*22 - gp.tileSize/2;
		gp.npc[0].wyPos = gp.tileSize*18 - gp.tileSize/2;

	}
}