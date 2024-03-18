package Documents.entity;

import Documents.main.GamePanel;

public class Monster extends Entity{
    public Monster(GamePanel gp){
        super(gp);

    }

    public void getMonsterImage(){
        upward1 = setup("/npc/boy_up_1");
    	upward2 = setup("/npc/boy_up_2");
    	downward1 = setup("/npc/boy_down_1");
    	downward2 = setup("/npc/boy_down_2");
    	leftward1 = setup("/npc/boy_left_1");
    	leftward2 = setup("/npc/boy_left_2");
    	rightward1 = setup("/npc/boy_right_1");
    	rightward2 = setup("/npc/boy_right_2");



    }



    
}
