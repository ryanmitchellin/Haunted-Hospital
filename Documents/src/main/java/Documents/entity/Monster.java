package Documents.entity;

import Documents.main.GamePanel;
import Documents.entity.MainCharacter;

public class Monster extends Entity{
    public Monster(GamePanel gp){
        super(gp);

    }

    
    public void update() {

        //checking collision with the wall tile
        isCollision = false;
        gp.checkCollision.tileCheck(this);

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
