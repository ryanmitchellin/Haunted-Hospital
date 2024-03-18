package Documents.main;

import Documents.tile.TileFactory;
import Documents.entity.Entity;


public class CollisionCheck {
	GamePanel gp;

	public CollisionCheck(GamePanel gp) {
		this.gp = gp;
	}

	public void tileCheck(Entity entity) {
		int left = entity.wxPos + entity.detectionArea.x;
		int right = entity.wxPos + entity.detectionArea.x + entity.detectionArea.width;
		int top = entity.wyPos + entity.detectionArea.y;
		int bottom = entity.wyPos + entity.detectionArea.y + entity.detectionArea.height;

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

	public int objCheck(Entity entity, boolean mainChar) {
		int index = 999;

		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				//get Entity's detection area pos
				entity.detectionArea.x = entity.wxPos + entity.detectionArea.x;
				entity.detectionArea.y = entity.wyPos + entity.detectionArea.y;

				//get the object's detection area pos
				gp.obj[i].detectionArea.x = gp.obj[i].worldX + gp.obj[i].detectionArea.x;
				gp.obj[i].detectionArea.y = gp.obj[i].worldY + gp.obj[i].detectionArea.y;

				switch(entity.direction) {
				case "up":
					entity.detectionArea.y -= entity.vel;
					if(entity.detectionArea.intersects(gp.obj[i].detectionArea)) {
						if(gp.obj[i].isCollision == true) {
							entity.isCollision = true;
						}
						if(mainChar == true) {
							index = i;
						}
					}
					break;
				case "down":
					entity.detectionArea.y += entity.vel;
					if(entity.detectionArea.intersects(gp.obj[i].detectionArea)) {
						if(gp.obj[i].isCollision == true) {
							entity.isCollision = true;
						}
						if(mainChar == true) {
							index = i;
						}
					}
					break;
				case "left":
					entity.detectionArea.x -= entity.vel;
					if(entity.detectionArea.intersects(gp.obj[i].detectionArea)) {
						if(gp.obj[i].isCollision == true) {
							entity.isCollision = true;
						}
						if(mainChar == true) {
							index = i;
						}
					}
					break;
				case "right":
					entity.detectionArea.x += entity.vel;
					if(entity.detectionArea.intersects(gp.obj[i].detectionArea)) {
						if(gp.obj[i].isCollision == true) {
							entity.isCollision = true;
						}
						if(mainChar == true) {
							index = i;
						}
					}
					break;
				}
				entity.detectionArea.x = entity.detectionDefaultX;
				entity.detectionArea.y = entity.detectionDefaultY;
				gp.obj[i].detectionArea.x = gp.obj[i].detectionDefaultX;
				gp.obj[i].detectionArea.y = gp.obj[i].detectionDefaultY;
			}
		}

		return index;
	}

	//npc or momster
	public int entityCheck(Entity entity, Entity[] target){
		int index = 999;

		for(int i = 0; i < target.length; i++) {
			if(target[i] != null) {
				//get Entity's detection area pos
				entity.detectionArea.x = entity.wxPos + entity.detectionArea.x;
				entity.detectionArea.y = entity.wyPos + entity.detectionArea.y;

				//get the object's detection area pos
				target[i].detectionArea.x = target[i].wxPos + target[i].detectionArea.x;
				target[i].detectionArea.y = target[i].wyPos + target[i].detectionArea.y;

				switch(entity.direction) {
				case "up":
					entity.detectionArea.y -= entity.vel;
					if(entity.detectionArea.intersects(target[i].detectionArea)) {
							entity.isCollision = true;
							index = i;
					}
					break;
				case "down":
					entity.detectionArea.y += entity.vel;
					if(entity.detectionArea.intersects(target[i].detectionArea)) {
						entity.isCollision = true;
							index = i;
					}
					break;
				case "left":
					entity.detectionArea.x -= entity.vel;
					if(entity.detectionArea.intersects(target[i].detectionArea)) {
						entity.isCollision = true;
							index = i;
					}
					break;
				case "right":
					entity.detectionArea.x += entity.vel;
					if(entity.detectionArea.intersects(target[i].detectionArea)) {
						entity.isCollision = true;
							index = i;
					}
					break;
				}
				entity.detectionArea.x = entity.detectionDefaultX;
				entity.detectionArea.y = entity.detectionDefaultY;
				target[i].detectionArea.x = target[i].detectionDefaultX;
				target[i].detectionArea.y = target[i].detectionDefaultY;
			}
		}

		return index;
	}

	public void playerCheck(Entity entity){
		//get Entity's detection area pos
		entity.detectionArea.x = entity.wxPos + entity.detectionArea.x;
		entity.detectionArea.y = entity.wyPos + entity.detectionArea.y;

		//get the object's detection area pos
		gp.mainCharacter.detectionArea.x = gp.mainCharacter.wxPos + gp.mainCharacter.detectionArea.x;
		gp.mainCharacter.detectionArea.y = gp.mainCharacter.wyPos + gp.mainCharacter.detectionArea.y;

		switch(entity.direction) {
		case "up":
			entity.detectionArea.y -= entity.vel;
			if(entity.detectionArea.intersects(gp.mainCharacter.detectionArea)) {
					entity.isCollision = true;
			}
			break;
		case "down":
			entity.detectionArea.y += entity.vel;
			if(entity.detectionArea.intersects(gp.mainCharacter.detectionArea)) {
				entity.isCollision = true;
			}
			break;
		case "left":
			entity.detectionArea.x -= entity.vel;
			if(entity.detectionArea.intersects(gp.mainCharacter.detectionArea)) {
				entity.isCollision = true;
			}
			break;
		case "right":
			entity.detectionArea.x += entity.vel;
			if(entity.detectionArea.intersects(gp.mainCharacter.detectionArea)) {
				entity.isCollision = true;
			}
			break;
		}
		entity.detectionArea.x = entity.detectionDefaultX;
		entity.detectionArea.y = entity.detectionDefaultY;
		gp.mainCharacter.detectionArea.x = gp.mainCharacter.detectionDefaultX;
		gp.mainCharacter.detectionArea.y = gp.mainCharacter.detectionDefaultY;
	}
}