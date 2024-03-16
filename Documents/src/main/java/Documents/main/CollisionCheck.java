package Documents.main;

import Documents.tile.TileFactory;
import Documents.entity.Entity;


public class CollisionCheck {
	GamePanel gp;

	public CollisionCheck(GamePanel gp) {
		this.gp = gp;
	}


	public void tileCheck(Entity entity) {
		int left = entity.xPos + entity.detectionArea.x;
		int right = entity.xPos + entity.detectionArea.x + entity.detectionArea.width;
		int top = entity.yPos + entity.detectionArea.y;
		int bottom = entity.yPos + entity.detectionArea.y + entity.detectionArea.height;

		int leftCol = left/gp.tileSize;
		int rightCol = right/gp.tileSize;
		int topRow = top/gp.tileSize;
		int bottomRow = bottom/gp.tileSize;

		int tile1, tile2;

		switch(entity.direction) {
		case "up":
			topRow = (top - entity.vel)/gp.tileSize;
			tile1 = gp.tileFactory.getTileMapNum(leftCol, topRow);
			tile2 = gp.tileFactory.getTileMapNum(rightCol, topRow);
			if(gp.tileFactory.getTile(tile1).collision || gp.tileFactory.getTile(tile2).collision) {
			    entity.isCollision = true;
			}
			break;
		case "down":
			bottomRow = (bottom + entity.vel)/gp.tileSize;
			tile1 = gp.tileFactory.getTileMapNum(leftCol, bottomRow);
			tile2 = gp.tileFactory.getTileMapNum(rightCol, bottomRow);
			if(gp.tileFactory.getTile(tile1).collision || gp.tileFactory.getTile(tile2).collision) {
			    entity.isCollision = true;
			}
			break;
		case "left":
			leftCol = (left - entity.vel)/gp.tileSize;
			tile1 = gp.tileFactory.getTileMapNum(leftCol, topRow);
			tile2 = gp.tileFactory.getTileMapNum(leftCol, bottomRow);
			if(gp.tileFactory.getTile(tile1).collision || gp.tileFactory.getTile(tile2).collision) {
			    entity.isCollision = true;
			}
			break;
		case "right":
			rightCol = (right + entity.vel)/gp.tileSize;
			tile1 = gp.tileFactory.getTileMapNum(rightCol, topRow);
			tile2 = gp.tileFactory.getTileMapNum(rightCol, bottomRow);
			if(gp.tileFactory.getTile(tile1).collision || gp.tileFactory.getTile(tile2).collision) {
			    entity.isCollision = true;
			}
			break;
		} 
	}
}