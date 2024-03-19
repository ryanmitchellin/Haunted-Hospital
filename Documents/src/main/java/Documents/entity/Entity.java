package Documents.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.IOException;

import Documents.SearchAI.Pathfinding;
import Documents.main.GamePanel;
import Documents.main.UtilityTools;


/**
 * Represents an entity inside the maze.
 */
public class Entity {
	/** The GamePanel instance associated with the entity. */
	GamePanel gp;

	/** The x-coordinate for the entity. */
	public int wxPos;

	/** The y-coordinate for the entity. */
	public int wyPos;

	/** The velocity for the entity. */
	public int vel;

	/** The image for the entity facing upward motion 1. */
	public BufferedImage upward1;

	/** The image for the entity facing upward motion 2. */
	public BufferedImage upward2;

	/** The image for the entity facing downward motion 1. */
	public BufferedImage downward1;

	/** The image for the entity facing downward motion 2. */
	public BufferedImage downward2;

	/** The image for the entity facing leftward motion 1. */
	public BufferedImage leftward1;

	/** The image for the entity facing leftward motion 2. */
	public BufferedImage leftward2;

	/** The image for the entity facing rightward motion 1. */
	public BufferedImage rightward1;

	/** Another image for the entity facing rightward motion 2. */
	public BufferedImage rightward2;

	/** The direction of the entity facing into. */
	public String direction;
	/** Indicates if the Entities are on a path towards the main character. */
	public boolean onPath = false;

	/** The count of sprites for the entity's movement animation. */
	public int spriteCount = 0;

	/** The number of sprites for the entity's movement animation. */
	public int spriteNum = 1;

	/** The area used for collision detection with tiles. */
	public Rectangle detectionArea  = new Rectangle (0, 0, 48, 48);

	/** The default x-coordinate for the detection area. */
	public int detectionDefaultX;

	/** The default y-coordinate for the detection area. */
	public int detectionDefaultY;

	/** Indicates the states whether the entity is currently collides with another entity. */
	public boolean isCollision = false;

	public int actionLockCount = 0;

	String dialogues[] = new String[20];
	int dialogueIndex = 0;

	public Entity (GamePanel gp) {
		this.gp = gp;
	}


	public void setAction(){}
	public void speak(){
		if(dialogues[dialogueIndex] == null){
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;

		switch(gp.mainCharacter.direction){
			case "up":
				direction = "down";
				break;
			case "down":
				direction = "up";
				break;
			case "left":
				direction = "right";
				break;
			case "right":
				direction = "left";
				break;
		}
	}
	public void checkCollision() {
		isCollision = false;
		gp.checkCollision.tileCheck(this);
		gp.checkCollision.objCheck(this, false);
		gp.checkCollision.entityCheck(this, gp.npc);
		gp.checkCollision.entityCheck(this, gp.monster);
		boolean contactPlayer = gp.checkCollision.playerCheck(this);

	}
	public void update(){
		setAction();
		checkCollision();

		isCollision = false;
		gp.checkCollision.tileCheck(this);
		gp.checkCollision.objCheck(this, false);
		gp.checkCollision.playerCheck(this);
		//if its false, character can move else cannot
		if(isCollision == false) {
			switch(direction) {
				case "up": this.wyPos -= this.vel; break;
				case "down": this.wyPos += this.vel; break;
				case "left": this.wxPos -= this.vel; break;
				case "right": this.wxPos += this.vel; break;
			}
		}

		//animation
		//the move() method gets called 60 times per second
		//the spritecount gets increments 1 per frame and every 20 frames the sprite image change
		spriteCount++;
		if(spriteCount > 10) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCount = 0;
		}
	}

	public void draw(Graphics2D g2){
		BufferedImage image = null;
		int screenX = wxPos - gp.mainCharacter.wxPos + gp.mainCharacter.screenX;
		int screenY = wyPos - gp.mainCharacter.wyPos + gp.mainCharacter.screenY;

		//checking if the tile is within the boundary
		if(wxPos + gp.tileSize > gp.mainCharacter.wxPos - gp.mainCharacter.screenX &&
				wxPos - gp.tileSize < gp.mainCharacter.wxPos + gp.mainCharacter.screenX &&
				wyPos + gp.tileSize > gp.mainCharacter.wyPos - gp.mainCharacter.screenY &&
				wyPos - gp.tileSize < gp.mainCharacter.wyPos + gp.mainCharacter.screenY) {
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
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
	public BufferedImage setup(String imgPath) {
		UtilityTools tools = new UtilityTools();
		BufferedImage img = null;

		try {
			img = ImageIO.read(getClass().getResourceAsStream(imgPath + ".png"));
			//ensure the image supports alpha (transparency)
			BufferedImage newImg = new BufferedImage(gp.tileSize, gp.tileSize, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = newImg.createGraphics();
			g2.drawImage(img, 0, 0, gp.tileSize, gp.tileSize, null);
			g2.dispose();
			img = newImg;
		}catch(IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	public void searchPath(int goalColumn, int goalRow) {
		int startColumn = (wxPos + detectionArea.x)/gp.tileSize;
		int startRow = (wxPos + detectionArea.y)/gp.tileSize;

		gp.pFinder.setNode(startColumn, startRow, goalColumn, goalRow);

		if(gp.pFinder.search() == true) {

			// Next wxPos and wyPos
			int nextX = gp.pFinder.pathList.get(0).column * gp.tileSize;
			int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

			// Entity's solidArea position
			int enLeftX = wxPos + detectionArea.x;
			int enRightX = wxPos + detectionArea.x + detectionArea.width;
			int enTopY = wyPos + detectionArea.y;
			int enBottomY = wyPos + detectionArea.y + detectionArea.height;

			if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "up";
			}
			else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "down";
			}
			else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
				if (enLeftX > nextX) {
					direction = "left";
				}
				if (enLeftX < nextX) {
					direction = "right";
				}
			}
			else if (enTopY > nextY && enLeftX > nextX) {
				//up or left
				direction = "up";
				checkCollision();
				;
				if (isCollision == true) {
					direction = "left";
				}
			}
			else if (enTopY > nextY && enLeftX < nextX) {
				direction = "up";
				checkCollision();
				if (isCollision == true) {
					direction = "right";
				}
			}
			else if (enTopY < nextY && enLeftX > nextX) {
				direction = "down";
				checkCollision();
				if(isCollision == true) {
					direction = "left";
				}
			}
			else if (enTopY < nextY && enLeftX < nextX) {
				direction = "down";
				checkCollision();
				if (isCollision == true) {
					direction = "right";
				}
			}

			int nextColumn = gp.pFinder.pathList.get(0).column;
			int nextRow = gp.pFinder.pathList.get(0).row;
			if(nextColumn == goalColumn && nextRow == goalRow) {
				onPath = false;
			}
		}
	}
}