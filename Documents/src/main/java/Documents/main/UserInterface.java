package Documents.main;

import Documents.object.KeyCard;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat; 
import java.awt.BasicStroke;

/**
 * The UserInterface class handles the display of the game's user interface elements.
 * It includes the key card image and the number of keys collected by the main character.
 */
public class UserInterface {
	/** The GamePanel associated with this user interface. */
	GamePanel gp;

	Graphics2D g2; //maybe rename
	Font arialFont_40,arialFont_80;

	/** The image representing the key card in the user interface. */
	BufferedImage cardKeyImg;

	int msgCount = 0;

	//printing
	public boolean printMessage = false;
	public String msg = "";
	public boolean gameDone = false;
	public String currentDialogue = "";
	public static double gameTime;
	public static double score = 100;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	public int commandingNumber = 0;

	/**
     * Constructs a new UserInterface object with the specified GamePanel.
     * @param gp The GamePanel associated with this user interface.
     */
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

	/**
     * Draws the user interface elements inside the maze.
     * @param g2 The graphics context to draw on.
     */
	public void draw(Graphics2D g2) {
		this.g2 = g2;

		g2.setFont(arialFont_40);
		g2.setColor(Color.white);

		// Title State
		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		//play state
		if (gp.gameState == gp.playState) {
			g2.setFont(arialFont_40);
			g2.setColor(Color.white);
			g2.drawImage(cardKeyImg, gp.tileSize/2, gp.tileSize/6, gp.tileSize, gp.tileSize, null);
			g2.drawString(" x " + gp.mainCharacter.keyNum + " / 4", 74, 50);
			gameTime += (double)1/60;
			g2.drawString("Time:" + dFormat.format(gameTime), gp.tileSize*11, 50);
			g2.drawString("Score:" + (int) (score-gameTime), gp.tileSize*11, 100);

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
		}
		//pause state
		if (gp.gameState == gp.stopState) {
			drawPauseScreen();
		}
		//dialogue state
		if (gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
	}

	public void drawTitleScreen() {

		g2.setColor(new Color(0,0,0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		// Title Name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "Ghost Castle";
		int x = getXCenterText(text);
		int y = gp.tileSize*3;

		// Shadow
		g2.setColor(Color.gray);
		g2.drawString(text,x+5,y+5);

		// Main color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);

		// Boy Image
		x = gp.screenWidth/2- (gp.tileSize*2)/2;
		y += gp.tileSize*2;
		g2.drawImage(gp.mainCharacter.downward1, x, y, gp.tileSize*2, gp.tileSize*2, null);

		// Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

		text = "NEW GAME";
		x = getXCenterText(text);
		y += gp.tileSize*4;
		g2.drawString(text,x,y);
		if (commandingNumber == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}

		text = "LOAD GAME";
		x = getXCenterText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandingNumber == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}

		text = "QUIT";
		x = getXCenterText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandingNumber == 2) {
			g2.drawString(">", x-gp.tileSize, y);
		}
	}

	public void drawPauseScreen() {

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "PAUSED";
		int x = getXCenterText(text);
		int y = gp.screenHeight/2;

		g2.drawString(text, x, y);
	}

	public void drawDialogueScreen(){
		//Window
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*5;

		drawSubWindow(x, y, width, height);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		x += gp.tileSize;
		y += gp.tileSize;
		//g2.drawString(currentDialogue, x, y);

		for(String line: currentDialogue.split("\n")){
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	public void drawSubWindow (int x, int y, int width, int height){
		Color c = new Color(0,0,0, 220);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);

		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	public int getXCenterText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}

	}
