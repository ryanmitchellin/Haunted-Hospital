package Documents.entity;

/*
The sprites used in the project : 

MainCharacter
	# 16x16 RPG characters `v3.0`

	16x16px RPG character sprite sheet for up to down games.

	This asset pack has been downloaded from https://route1rodent.itch.io/16x16-rpg-character-sprite-sheet

	Adapted from opengameart.org 's "[NES-Style RPG Characters](https://opengameart.org/content/nes-style-rpg-characters)" and "[More NES-style RPG Characters](https://opengameart.org/content/more-nes-style-rpg-characters)", with additional content made by [@route1rodent](https://route1rodent.itch.io).

	## License

	16x16 RPG character sprite sheet (c) by @route1rodent

	"16x16 RPG character sprite sheet" is licensed under a
	Creative Commons Attribution-ShareAlike 3.0 Unported License (CC BY-SA 3.0).

	You should have received a copy of the license along with this
	work. If not, see http://creativecommons.org/licenses/by-sa/3.0/.


	## Credits

	Maintained by @route1rodent:

	- itch.io: https://route1rodent.itch.io
	- Twitter: https://twitter.com/route1rodent
	- Github: https://github.com/itsjavi
	- Blog: https://blog.itsjavi.com

Mapping Tile design

**/

import Documents.main.GamePanel;
import Documents.main.KeyControl;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;


public class MainCharacter extends Entity {
	GamePanel gp;
	KeyControl keyControl;
	public final int screenX;
	public final int screenY;

	//key count
	int keyNum = 0;

	public MainCharacter(GamePanel gp, KeyControl keyControl) {
		this.gp = gp;
		this.keyControl = keyControl;

		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);

		detectionArea = new Rectangle();
		detectionArea.x = 8;
		detectionArea.y = 16;

		detectionDefaultX = detectionArea.x;
		detectionDefaultY = detectionArea.y;

		detectionArea.width = 32;
		detectionArea.height = 32;

		settingDefaultValue();
		getMainCharacterImg();
	}

	public void settingDefaultValue() {
		wxPos = gp.tileSize * 20 - gp.tileSize/2;
		wyPos = gp.tileSize * 18 - gp.tileSize/2;
		vel = 4;

		direction = "down";
	}

	public void getMainCharacterImg() {
    	try {
        	upward1 = ImageIO.read(getClass().getResourceAsStream("/mainCharacter/boy_up_1.png"));
        	upward2 = ImageIO.read(getClass().getResourceAsStream("/mainCharacter/boy_up_2.png"));
        	downward1 = ImageIO.read(getClass().getResourceAsStream("/mainCharacter/boy_down_1.png"));
        	downward2 = ImageIO.read(getClass().getResourceAsStream("/mainCharacter/boy_down_2.png"));
    	    leftward1 = ImageIO.read(getClass().getResourceAsStream("/mainCharacter/boy_left_1.png"));
	        leftward2 = ImageIO.read(getClass().getResourceAsStream("/mainCharacter/boy_left_2.png"));
	        rightward1 = ImageIO.read(getClass().getResourceAsStream("/mainCharacter/boy_right_1.png"));
	        rightward2 = ImageIO.read(getClass().getResourceAsStream("/mainCharacter/boy_right_2.png")); 

    	} catch(IOException e) {
    		//checking if the image has been loaded correctly
    		//throw error
        	e.printStackTrace();
    	}
	}


	//method update for player movement depending on the keypressed
	public void move() {
		if(keyControl.upPressed == true || keyControl.downPressed == true ||
			keyControl.leftPressed == true || keyControl.rightPressed == true) {
			if(keyControl.upPressed == true) {
				direction = "up";
			}else if(keyControl.downPressed == true) {
				direction = "down";
			}else if(keyControl.leftPressed == true) {
				direction = "left";
			}else if(keyControl.rightPressed == true) {
				direction = "right";
			}

			//checking collision with the wall tile
			isCollision = false;
			gp.checkCollision.tileCheck(this);

			//obj collision
			int objIndex = gp.checkCollision.objCheck(this,true);
			pickUpObj(objIndex);


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
	}

	public void pickUpObj(int i) {
		//if index is not 999 it means the collision has happened
		if(i !=  999) {
			String objName = gp.obj[i].type;

			//depending on obj reaction
			switch(objName) {
			case "keyCard":
				keyNum++;
				//null to remove the object
				gp.obj[i] = null;
				break;
			case "door":
				if(keyNum == 3) {
					gp.obj[i] = null;
					keyNum = 0;
					break;
				}
			}
			
		}
	}

	public void draw(Graphics2D g2) {
		BufferedImage image = null;

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
		//image observer
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}



