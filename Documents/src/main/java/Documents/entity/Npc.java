package entity;

import main.GamePanel;

public class Npc extends Entity {
    public Npc (GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImg();
    }

    public void getImg() {
    	upward1 = setup("/npc/boy_up_1");
    	upward2 = setup("/npc/boy_up_2");
    	downward1 = setup("/npc/boy_down_1");
    	downward2 = setup("/npc/boy_down_2");
    	leftward1 = setup("/npc/boy_left_1");
    	leftward2 = setup("/npc/boy_left_2");
    	rightward1 = setup("/npc/boy_right_1");
    	rightward2 = setup("/npc/boy_right_2");
	}

    public void setAction(){

        actionLockCount++;
        if (actionLockCount == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1; //pick up num from 1 to 100

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