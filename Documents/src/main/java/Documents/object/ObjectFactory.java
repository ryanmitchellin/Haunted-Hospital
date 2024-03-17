package Documents.object;
import Documents.main.GamePanel;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;


public class ObjectFactory {
	public BufferedImage img;
	public String type;
	public boolean isCollision = false;
	public int worldX;
	public int worldY;


	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX = worldX - gp.mainCharacter.wxPos + gp.mainCharacter.screenX;
		int screenY = worldY - gp.mainCharacter.wyPos + gp.mainCharacter.screenY;

		//checking if the tile is within the boundary
		if(worldX + gp.tileSize > gp.mainCharacter.wxPos - gp.mainCharacter.screenX &&
		   worldX - gp.tileSize < gp.mainCharacter.wxPos + gp.mainCharacter.screenX &&
		   worldY + gp.tileSize > gp.mainCharacter.wyPos - gp.mainCharacter.screenY &&
		   worldY - gp.tileSize < gp.mainCharacter.wyPos + gp.mainCharacter.screenY) {
			g2.drawImage(img, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
}