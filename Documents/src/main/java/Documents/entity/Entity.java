package Documents.entity;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.Rectangle;

public class Entity {
	GamePanel gp;
	public int wxPos;
	public int wyPos;
	public int vel;

	//for character image load
	public BufferedImage upward1,upward2,downward1,downward2,leftward1,leftward2,rightward1,rightward2;
	public String direction;
	//for animation count for the movement
	public int spriteCount = 0;
	public int spriteNum = 1;


	//collision for the tiles
	public Rectangle detectionArea  = new Rectangle (0, 0, 48, 48);

	//
	public int detectionDefaultX;
	public int detectionDefaultY;

	public boolean isCollision = false;

	public Entity (GamePanel gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2){ //XXX
		BufferedImage image = null;
		int screenX = worldX - gp.mainCharacter.wxPos + gp.mainCharacter.screenX;
		int screenY = worldY - gp.mainCharacter.wyPos + gp.mainCharacter.screenY;

		//checking if the tile is within the boundary
		if(worldX + gp.tileSize > gp.mainCharacter.wxPos - gp.mainCharacter.screenX &&
		   worldX - gp.tileSize < gp.mainCharacter.wxPos + gp.mainCharacter.screenX &&
		   worldY + gp.tileSize > gp.mainCharacter.wyPos - gp.mainCharacter.screenY &&
		   worldY - gp.tileSize < gp.mainCharacter.wyPos + gp.mainCharacter.screenY) {
			switch(direction) {
				case "up":
					if(spriteNum == 1) {
						image = upward1;
					}
					if(spriteNum == 2) {
						image = upward2;
					}
					break;
				case "down":
					if(spriteNum == 1) {
						image = downward1;
					}
					if(spriteNum == 2) {
						image = downward2;
					}
					break;
				case "left":
					if(spriteNum == 1) {
						image = leftward1;
					}
					if(spriteNum == 2) {
						image = leftward2;
					}
					break;
				case "right":
					if(spriteNum == 1) {
						image = rightward1;
					}
					if(spriteNum == 2) {
						image = rightward2;
					}
					break;
		}
			g2.drawImage(img, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
}