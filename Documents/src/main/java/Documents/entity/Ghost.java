package Documents.entity;

import Documents.main.GamePanel;

public class Ghost extends Monster {

    public Ghost(GamePanel gp) {
        super(gp);
        



    }

    public void getGhostImage(){
        upward1 = setup("/npc/boy_up_1");
    	upward2 = setup("/npc/boy_up_2");
    	downward1 = setup("/npc/boy_down_1");
    	downward2 = setup("/npc/boy_down_2");
    	leftward1 = setup("/npc/boy_left_1");
    	leftward2 = setup("/npc/boy_left_2");
    	rightward1 = setup("/npc/boy_right_1");
    	rightward2 = setup("/npc/boy_right_2");



    }

    @Override
    public void update() {

        int mainX = MainCharacter.getWxPos();
         int mainY = MainCharacter.getWyPos();

        int distance = Math.abs(mainX - this.wxPos) + Math.abs(mainY - this.wyPos);

        if(distance>(Math.abs(mainX-(this.wxPos+1))+Math.abs(mainY-(this.wyPos)))){
            this.wxPos += this.vel;
        }
        else if(distance>(Math.abs(mainX-(this.wxPos))+Math.abs(mainY-(this.wyPos+1)))){
            this.wyPos += this.vel;
        }
        else if(distance>(Math.abs(mainX-(this.wxPos-1))+Math.abs(mainY-(this.wyPos)))){
            this.wxPos -= this.vel;
        }
        else if(distance>(Math.abs(mainX-(this.wxPos+1))+Math.abs(mainY-(this.wyPos)))){
            this.wyPos -= this.vel;
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
