package Documents.main;

import Documents.object.KeyCard;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat; 

public class UserInterface {
	GamePanel gp;
	Font arialFont_40,arialFont_80;
	BufferedImage cardKeyImg;

	int msgCount = 0;

	//printing
	public boolean printMessage = false;
	public String msg = "";
	public boolean gameDone = false;
	double gameTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");


	public UserInterface(GamePanel gp) {
		this.gp = gp;
		arialFont_40 = new Font("Arial", Font.PLAIN, 40);
		arialFont_80 = new Font("Arial", Font.BOLD, 80);
		KeyCard keyCard = new KeyCard(gp);
		cardKeyImg = keyCard.img;
	}

	public void displayMessage(String text) {
		msg = text;
		printMessage = true;
	}

	public void draw(Graphics2D g2) {
		if(gameDone == false) {
			g2.setFont(arialFont_40);
			g2.setColor(Color.white);
			g2.drawImage(cardKeyImg, gp.tileSize/2, gp.tileSize/6, gp.tileSize, gp.tileSize, null);
			g2.drawString(" x " + gp.mainCharacter.keyNum + " / 4", 74, 50);
			gameTime += (double)1/60;
			g2.drawString("Time:" + dFormat.format(gameTime), gp.tileSize*11, 50);

			//displaying message
			if(printMessage == true) {
				g2.setFont(g2.getFont().deriveFont(20));
				g2.drawString(msg,gp.tileSize,gp.tileSize * 5); 

				msgCount++;

				if(msgCount > 120) {
					msgCount = 0;
					printMessage = false;
				}
			}
		} else {
			g2.setFont(arialFont_40);
			g2.setColor(Color.white);
			String text;
			int textLength;
			int x;
			int y;

			text = "You escaped from the Hospital!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2;
			g2.drawString(text,x,y);
			//time of the play
			text = "Your total play time is :" + dFormat.format(gameTime) + "!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*4);
			g2.drawString(text,x,y);

			g2.setFont(arialFont_80);
			g2.setColor(Color.yellow);
			text = "Congratulations!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*2);
			g2.drawString(text,x,y);

			gp.gameThread = null;

		}
	}
}