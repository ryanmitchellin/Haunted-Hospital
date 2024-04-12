package Documents.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.IOException;
import Documents.main.GamePanel;

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

	/** Sprite count for the animation */
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

	/** Counting for the locking the action */
	public int actionLockCount = 0;

	/** Testing variable for debugging */
	int test;

	/** array to store dialgoues */
	String dialogues[] = new String[20];

	/** index of the dialogues */
	int dialogueIndex = 0;

	/** 
	 * Constructor for the Entity class which constructs new entity with reference
	 * to the GamePanel for interaction
	 * @param gp the game panel instance to which this entity belongs to
	 */
	public Entity (GamePanel gp) {
		this.gp = gp;
	}

	/**
	 * Sets the next action for the entity
	 */
	public void setAction(){}

	/**
	 * displaying the next dialogue from the dialogue array above
	 */
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

	/** 
	 * Checking collision with the tiles, objects, main character, and other entities
	 * it updates the collision flag
	 */
	public void checkCollision() {
		isCollision = false;
		gp.checkCollision.tileCheck(this);
		gp.checkCollision.objCheck(this, false);
		gp.checkCollision.playerCheck(this);
		gp.checkCollision.entityCheck(this, gp.monster);
	}

	/** 
	 * updating the entity's location (position) and the state, including movement and animation
	 */
	public void update(){
		setAction();
		checkCollision();

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
	/**
	 * Drawing the entity and its current position on Graphic 2D context
	 * 
	 * @param g2 is the Graphic 2D context on which to draw the entity
	 *
	 */
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

	/**
	 * loads an image from the specified path and scales it to the game panel's tile size
	 * 
	 * @param imgPath is the path to the image file
	 * @return returning scaled image
	 */
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

	/**
	 * searching the path towards a specified goal using the pathfinding
	 * 
	 * @param goalColumn is the destination column on the map
	 * @param goalRow is the destination row on the map
	 */
	public void searchPath(int goalColumn, int goalRow) {
		int startColumn = (wxPos + detectionArea.x)/gp.tileSize;
		int startRow = (wyPos + detectionArea.y)/gp.tileSize;

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