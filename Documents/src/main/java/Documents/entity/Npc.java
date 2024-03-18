package entity;

import main.GamePanel;

public class Npc extends Entity {
    public Npc (GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        
    }
}