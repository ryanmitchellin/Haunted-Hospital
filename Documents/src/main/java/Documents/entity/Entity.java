package Documents.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.IOException;
import Documents.main.GamePanel;
import Documents.main.UtilityTools;

/**
 * Represents an entity inside the maze.
 */
public class Entity {
	/** The GamePanel instance associated with the entity. */
	GamePanel gp;

	/** The x-coordinate for the entity. */
	public int worldXPos;

	/** The y-coordinate for the entity. */
	public int worldYPos;

	/** The velocity for the entity. */
	public int velocity;

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

	public int spriteCount = 0;

	/** The number of sprites for the entity's movement animation. */
	public int spriteNum = 1;

	/** The area used for collision detection with tiles. */
	public Rectangle detectionArea  = new Rectangle (0, 0, 40, 40);

	/** The default x-coordinate for the detection area. */
	public int detectionDefaultX;

	/** The default y-coordinate for the detection area. */
	public int detectionDefaultY;

	/** Indicates the states whether the entity is currently collides with another entity. */
	public boolean isCollision = false;

	public int actionLockCount = 0;
	int test;

	String dialogues[] = new String[20];
	int dialogueIndex = 0;

	public Entity (GamePanel gp) {
		this.gp = gp;
	}


	public void setAction(){

	}

	public void checkCollision() {
		isCollision = false;
		gp.checkCollision.tileCheck(this);
		gp.checkCollision.objCheck(this, false);
		gp.checkCollision.playerCheck(this);
		gp.checkCollision.entityCheck(this, gp.monster);
	}

	public void update(){
		setAction();
		checkCollision();

		//if its false, character can move else cannot
		if(isCollision == false) {
			switch(direction) {
				case "up": this.worldYPos -= this.velocity; break;
				case "down": this.worldYPos += this.velocity; break;
				case "left": this.worldXPos -= this.velocity; break;
				case "right": this.worldXPos += this.velocity; break;
			}
		}
		spriteNum = UtilityTools.spriteCountCalculations(spriteCount, spriteNum);

		//animation
		//the move() method gets called 60 times per second
		//the spritecount gets increments 1 per frame and every 20 frames the sprite image change
	}

	public void draw(Graphics2D g2){
		BufferedImage image = null;
		int screenX = worldXPos - gp.mainCharacter.worldXPos + gp.mainCharacter.screenX;
		int screenY = worldYPos - gp.mainCharacter.worldYPos + gp.mainCharacter.screenY;

		//checking if the tile is within the boundary
		if(worldXPos + gp.tileSize > gp.mainCharacter.worldXPos - gp.mainCharacter.screenX &&
				worldXPos - gp.tileSize < gp.mainCharacter.worldXPos + gp.mainCharacter.screenX &&
				worldYPos + gp.tileSize > gp.mainCharacter.worldYPos - gp.mainCharacter.screenY &&
				worldYPos - gp.tileSize < gp.mainCharacter.worldYPos + gp.mainCharacter.screenY) {
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
		int startColumn = (worldXPos + detectionArea.x)/gp.tileSize;
		int startRow = (worldYPos + detectionArea.y)/gp.tileSize;

		gp.pFinder.setNode(startColumn, startRow, goalColumn, goalRow);

		if(gp.pFinder.search() == true) {

			// Next worldXPos and wyPos
			int nextX = gp.pFinder.pathList.get(0).column * gp.tileSize;
			int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;


			// Entity's solidArea position
			int enLeftX = worldXPos + detectionArea.x;
			int enRightX = worldXPos + detectionArea.x + detectionArea.width;
			int enTopY = worldYPos + detectionArea.y;
			int enBottomY = worldYPos + detectionArea.y + detectionArea.height;

			// System.out.println(enTopY + " " + nextY);
			// System.out.println(enBottomY + " " + (nextY+gp.tileSize));
			// System.out.println(enLeftX + " " + nextX);
			// System.out.println(enRightX + " " + (nextX+gp.tileSize));
			// System.out.println(gp.tileSize + " " + detectionArea.height);
			// System.out.println(detectionArea.y + " " + detectionArea.x);
			


			if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				test = 1;
				direction = "up";
			}
			else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				test = 2;
				direction = "down";
			}
			else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
				if (enLeftX > nextX) {
					test = 3;
					direction = "left";
				}
				if (enLeftX < nextX) {
					test = 4;
					direction = "right";
				}
			}
			else if (enTopY > nextY && enLeftX > nextX) {
				//up or left
				test = 5;
				direction = "up";
				checkCollision();
				if (isCollision == true) {
					test = 6;
					direction = "left";
				}
			}
			else if (enTopY > nextY && enLeftX < nextX) {
				direction = "up";
				test = 7;
				checkCollision();
				if (isCollision == true) {
					test = 8;
					direction = "right";
				}
			}
			else if (enTopY < nextY && enLeftX > nextX) {
				direction = "down";
				test = 9;
				checkCollision();
				if(isCollision == true) {
					test = 10;
					direction = "left";
				}
			}
			else if (enTopY < nextY && enLeftX < nextX) {
				direction = "down";
				test = 11;
				checkCollision();
				if (isCollision == true) {
					test = 12;
					direction = "right";
				}
			}

			// int nextColumn = gp.pFinder.pathList.get(0).column;
			// int nextRow = gp.pFinder.pathList.get(0).row;
			// if(nextColumn == goalColumn && nextRow == goalRow) {
			// 	onPath = false;
			// }
		}
	}
}