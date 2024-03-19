package Documents.entity;

import Documents.main.GamePanel;
import Documents.entity.MainCharacter;

public class Ghost extends Monster {

    public Ghost(GamePanel gp) {
        super(gp);
        

        direction = "down";
        vel = 1;

        getGhostImage();
     

    }

    public void getGhostImage(){
        upward1 = setup("/ghost/upward1");
    	upward2 = setup("/ghost/upward2");
    	downward1 = setup("/ghost/down1");
    	downward2 = setup("/ghost/down2");
    	leftward1 = setup("/ghost/left1");
    	leftward2 = setup("/ghost/left2");
    	rightward1 = setup("/ghost/right1");
    	rightward2 = setup("/ghost/right2");
    }



    @Override
    public void update() {

        int mainX = gp.mainCharacter.getMainWxPos();
        int mainY = gp.mainCharacter.getMainWyPos();

        int distance = Math.abs(mainX - this.wxPos) + Math.abs(mainY - this.wyPos);

        if(distance>(Math.abs(mainX-(this.wxPos+1))+Math.abs(mainY-(this.wyPos)))){
            this.wxPos += this.vel;
        }
        else if(distance>(Math.abs(mainX-(this.wxPos-1))+Math.abs(mainY-(this.wyPos)))){
            this.wxPos -= this.vel;
        }

        if(distance>(Math.abs(mainX-(this.wxPos))+Math.abs(mainY-(this.wyPos+1)))){
            this.wyPos += this.vel;
        }
        else if(distance>(Math.abs(mainX-(this.wxPos))+Math.abs(mainY-(this.wyPos-1)))){
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
