package Documents.entity;

import Documents.main.GamePanel;

public class Demon extends Monster {

    public Demon(GamePanel gp) {
        super(gp);
        //TODO Auto-generated constructor stub
    }
    

    public void getDemonImageß(){
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
