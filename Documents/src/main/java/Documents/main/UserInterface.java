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

	int msgCount = 0;

	//printing
	public boolean printMessage = false;
	public String msg = "";
	public boolean gameDone = false;
	public String currentDialogue = "";

	public UserInterface(GamePanel gp) {
		this.gp = gp;
		arialFont_40 = new Font("Arial", Font.PLAIN, 40);
		arialFont_80 = new Font("Arial", Font.BOLD, 80);
	}

	public void displayMessage(String text) {
		msg = text;
		printMessage = true;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;

		g2.setFont(arial_40);
		g2.setColor(Color.white);

		//play state
		if (gp.gameState == gp.playState) {
			// Do playState stuff later
		}
		//pause state
		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		//dialogue state
		if (gp.gameState == gp.dialogueStateState) {
			drawDialogueScreen();
		}
	}
	public void drawPauseScreen() {

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "PAUSED";
		int x = getXCenterText(text);
		int y = gp.screenHeight/2;

		g2.drawString(text, x, y);
	}

	public void draw drawDialogueScreen(){
		//Window
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;

		drawSubWindow(x, y, width, height);
	}
	public void drawSubWindow (int x, int y, int width, int height){
		Color c = new Color(0,0,0, 220);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);

		c = new Color(255,255,255);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	public int getXCenterText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}

	}
