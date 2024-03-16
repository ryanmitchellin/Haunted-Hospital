package Documents.main;
import Documents.entity.MainCharacter;
import Documents.tile.TileFactory;


import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class GamePanel extends JPanel implements Runnable{
	//Screening setting

	final int originalTileSize = 16; //Tile will be 16x16 as default
	final int scale = 3; // 16*3 Scaling to 48 pixel

	//public to ensure the MainCharacter has access to the the tileSize
	public final int tileSize = originalTileSize * scale; //48 pixel per tile
	
	//setting the screen size 4:3 default
	public final int maxCol = 24;
	public final int maxRow = 18;
	public final int screenWidth = tileSize * maxCol; // Width of the screen : 1152 pixels
	public final int screenHeight = tileSize * maxRow; // Height of the screen : 864 pixels

	//setting world map
	public final int maxWCol = 40;
	public final int maxWRow = 36;
	public final int worldWidth = tileSize * maxWCol;
	public final int worldHeight = tileSize * maxWRow;

	//Frames per second
	int FPS = 60;
	
	TileFactory tileFactory = new TileFactory(this);	
	KeyControl keyControl = new KeyControl();
	Thread gameThread;
	public CollisionCheck checkCollision = new CollisionCheck(this);
	public MainCharacter mainCharacter = new MainCharacter(this,keyControl);

	//constructor
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //setting the size of panel
		this.setBackground(Color.black); //setting default background as black
		this.setDoubleBuffered(true);

		//key control method
		this.addKeyListener(keyControl);
		this.setFocusable(true);
	}

	//when start the thread it will auto run this
	public void gameStartThread() {
		//passing gamePanel class to thread constructor
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double displayInterval = 1000000000/FPS; //1second / 60FPS = 0.16 second/frame
		double nextDrawTime = System.nanoTime() + displayInterval;

		//game core : if the game thread exist it will run
		while(gameThread != null) {
			//testing purpose to check if the loop is working
			//System.out.println("The game running");

			update();
			repaint();

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;

				if(remainingTime <= 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);
				nextDrawTime += displayInterval ; 

			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		} 
	}

	public void update() {
		//movement for the character
		mainCharacter.move();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;
		tileFactory.draw(g2);
		//displayment of the player 
		mainCharacter.draw(g2);
		g2.dispose();
	}

}