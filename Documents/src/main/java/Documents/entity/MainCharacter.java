package Documents.entity;


import Documents.main.GamePanel;
import Documents.main.KeyControl;
import Documents.main.UtilityTools;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;


public class MainCharacter extends Entity {
	GamePanel gp;
	KeyControl keyControl;
	public final int screenX;
	public final int screenY;

	//key count
	public int keyNum = 0;

	public MainCharacter(GamePanel gp, KeyControl keyControl) {
		super(gp);
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
    	upward1 = setup("/mainCharacter/boy_up_1");
    	upward2 = setup("/mainCharacter/boy_up_2");
    	downward1 = setup("/mainCharacter/boy_down_1");
    	downward2 = setup("/mainCharacter/boy_down_2");
    	leftward1 = setup("/mainCharacter/boy_left_1");
    	leftward2 = setup("/mainCharacter/boy_left_2");
    	rightward1 = setup("/mainCharacter/boy_right_1");
    	rightward2 = setup("/mainCharacter/boy_right_2");
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

			// npc collision
			int npcIndex = gp.checkCollision.entityCheck(this,gp.npc); //XXX
			interactNpc(npcIndex);

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
				gp.soundEffectObj(1);
				keyNum++;
				//null to remove the object
				gp.obj[i] = null;
				gp.ui.displayMessage("Key Card collected!");
				break;
			case "door":
				if(keyNum == 4) {
					gp.soundEffectObj(2);
					gp.obj[i] = null;
					keyNum = 0;
					gp.ui.displayMessage("The door is now open!");
					break;
				} else {
					gp.ui.displayMessage("You need 4 card keys to open");
				}
			case "stair":
				gp.ui.gameDone = true;
				gp.musicStop();
				//gp.soundEffectObj();
				break;
			}
			
		}
	}

	public void interactNpc(int i){
		if(i != 999){
			if(gp.keyControl.enterPressed == true){
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		gp.keyControl.enterPressed == false;
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
		g2.drawImage(image, screenX, screenY, null);
	}
}



