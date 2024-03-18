package Documents.main;

import Documents.object.KeyCard;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat; 

public class UserInterface {
	GamePanel gp;
	Graphics2D g2; //maybe rename
	Font arialFont_40,arialFont_80;
	//BufferedImage cardKeyImg; (commented out #13)

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
		//KeyCard keyCard = new KeyCard(gp); //commented out #13
		//cardKeyImg = keyCard.img; //commented out #13
	}

	public void displayMessage(String text) {
		msg = text;
		printMessage = true;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;

		g2.setFont(arial_40);
		g2.setColor(Color.white);

		if (gp.gameState == gp.playState) {
			// Do playState stuff later
		}
		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
	}
	public void drawPauseScreen() {

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "PAUSED";
		int x = getXCenterText(text);
		int y = gp.screenHeight/2;

		g2.drawString(text, x, y);
	}
	public int getXCenterText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}

	}
