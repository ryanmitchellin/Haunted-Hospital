package Documents.entity;

import Documents.main.GamePanel;
import java.awt.*;
import java.util.Random;

public class Demon extends Monster {

	public Random random = new Random();
	public int randomI;


    public Demon(GamePanel gp) {
		super(gp);
		direction = "down";
		this.velocity = 2;

		getDemonImage();

		detectionArea = new Rectangle();
		detectionArea.x = 8;
		detectionArea.y = 8;

		detectionArea.width = 8;
		detectionArea.height = 8;
    }
    

    public void getDemonImage(){
        upward1 = setup("/demon/boy_up_1");
    	upward2 = setup("/demon/boy_up_2");
    	downward1 = setup("/demon/boy_down_1");
    	downward2 = setup("/demon/boy_down_2");
    	leftward1 = setup("/demon/boy_left_1");
    	leftward2 = setup("/demon/boy_left_2");
    	rightward1 = setup("/demon/boy_right_1");
    	rightward2 = setup("/demon/boy_right_2");
    }

	public void setAction() {

		if (onPath) {
			int goalColumn = (gp.mainCharacter.worldXPos + gp.mainCharacter.detectionArea.x) / gp.tileSize;
			int goalRow = (gp.mainCharacter.worldYPos + gp.mainCharacter.detectionArea.y) / gp.tileSize;

			searchPath(goalColumn, goalRow);
		}
	}

	@Override
	public void update() {
		super.update();
		//System.out.println("updating demon");
		int mainX = Math.abs(worldXPos - gp.mainCharacter.worldXPos);
		int mainY = Math.abs(worldYPos - gp.mainCharacter.worldYPos);
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

		setAction();
	}

}