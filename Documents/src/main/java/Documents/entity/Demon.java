package Documents.entity;

import Documents.main.GamePanel;
import java.awt.*;
import java.util.Random;

public class Demon extends Monster {

    public Demon(GamePanel gp) {
		super(gp);
		//TODO Auto-generated constructor stub
		direction = "down";
		this.vel = 2;

		getDemonImage();

		detectionArea = new Rectangle();
		detectionArea.x = 8;
		detectionArea.y = 16;


		detectionArea.width = 18;
		detectionArea.height = 18;
    }
    

    public void getDemonImage(){
        upward1 = setup("/npc/boy_up_1");
    	upward2 = setup("/npc/boy_up_2");
    	downward1 = setup("/npc/boy_down_1");
    	downward2 = setup("/npc/boy_down_2");
    	leftward1 = setup("/npc/boy_left_1");
    	leftward2 = setup("/npc/boy_left_2");
    	rightward1 = setup("/npc/boy_right_1");
    	rightward2 = setup("/npc/boy_right_2");
    }

	public void setAction() {

		if (onPath) {
			int goalColumn = (gp.mainCharacter.wxPos + gp.mainCharacter.detectionArea.x) / gp.tileSize;
			int goalRow = (gp.mainCharacter.wyPos + gp.mainCharacter.detectionArea.y) / gp.tileSize;

			searchPath(goalColumn, goalRow);
		} else {
			actionLockCount++;

			if (actionLockCount == 120) {
				Random random = new Random();
				int i = random.nextInt(100) + 1;

				if (i <= 25) {
					direction = "up";
				}
				if (i > 25 && i <= 50) {
					direction = "down";
				}
				if (i > 50 && i <= 75) {
					direction = "left";
				}
				if (i > 75 && i <= 100) {
					direction = "right";
				}
				actionLockCount = 0;
			}
		}
	}

	@Override
	public void update() {
		super.update();
		//System.out.println("updating demon");
		int mainX = Math.abs(wxPos - gp.mainCharacter.wxPos);
		int mainY = Math.abs(wyPos - gp.mainCharacter.wyPos);
		int tDist = (mainX + mainY)/gp.tileSize;

		if (onPath == false && tDist <  50) {
			int i = new Random().nextInt(100)+1;
			if (i > 50) {
				onPath = true;
			}
		}/**
		 if (onPath == true && tDist > 20) {
		 onPath = false;
		 }
		 */

	}

}
