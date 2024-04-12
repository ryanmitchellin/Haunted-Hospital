package Documents.entity;

import Documents.main.GamePanel;

public class Monster extends Entity{
    public Monster(GamePanel gp){
        super(gp);
    }


    public void update() {
        //super.update(); taking this out for coverage.
        //if(isCollision == false) {
            switch(direction) {
                case "up": this.worldYPos -= this.velocity; break;
                case "down": this.worldYPos += this.velocity; break;
                case "left": this.worldXPos -= this.velocity; break;
                case "right": this.worldXPos += this.velocity; break;
            }
       // }
        //checking collision with the wall tile
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
