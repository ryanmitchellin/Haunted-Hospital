package Documents.entity;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.Rectangle;

public class Entity {
	public int wxPos;
	public int wyPos;
	public int vel;

	//for character image load
	public BufferedImage upward1,upward2,downward1,downward2,leftward1,leftward2,rightward1,rightward2;
	public String direction;
	//for animation count for the movement
	public int spriteCount = 0;
	public int spriteNum = 1;


	//collision for the tiles
	public Rectangle detectionArea;

	//
	public int detectionDefaultX;
	public int detectionDefaultY;

	public boolean isCollision = false;

}