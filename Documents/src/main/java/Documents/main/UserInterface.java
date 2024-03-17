package Documents.main;

import Documents.object.KeyCard;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class UserInterface {
	GamePanel gp;
	Font arialFont;
	BufferedImage cardKeyImg;


	public UserInterface(GamePanel gp) {
		this.gp = gp;
		arialFont = new Font("Arial", Font.PLAIN, 40);
		KeyCard keyCard = new KeyCard();
		cardKeyImg = keyCard.img;
	}

	public void draw(Graphics2D g2) {
		g2.setFont(arialFont);
		g2.setColor(Color.white);
		g2.drawImage(cardKeyImg, gp.tileSize/2, gp.tileSize/6, gp.tileSize, gp.tileSize, null);
		g2.drawString(" x " + gp.mainCharacter.keyNum + " / 3", 74, 50);
	}
}