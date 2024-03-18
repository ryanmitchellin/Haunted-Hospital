package Documents.main;
import Documents.entity.MainCharacter;
import Documents.tile.TileFactory;
import Documents.object.ObjectFactory;
import Documents.entity.Entity;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * The main panel for the maze managing the game logic and rendering the maze.
 */
public class GamePanel extends JPanel implements Runnable{
	/** The original size of a 16 x 16 tile. */
	final int originalTileSize = 16;

	/** The scaling factor for tiles. */
	final int scale = 3;

	/** The size of a tile after scaling. */
	public final int tileSize = originalTileSize * scale; // Tile size : 16 * 3 = 48 pixels per tile
	
	/** The number of columns in the screen. (4 : 3 by default) */
	public final int maxCol = 24;

	/** The number of rows in the screen. (4 : 3 by default) */
	public final int maxRow = 18;

	/** The width of the screen. */
	public final int screenWidth = tileSize * maxCol; // Width of the screen : 1152 pixels

	/** The height of the screen. */
	public final int screenHeight = tileSize * maxRow; // Height of the screen : 864 pixels

	/** The number of columns in world map. */
	public final int maxWCol = 50;

	/** The number of rows in world map. */
	public final int maxWRow = 50;

	/** The number of frames per second for the game. */
	int FPS = 60;

	/** The tile factory for creating and managing tiles. */
	TileFactory tileFactory = new TileFactory(this);

	/** The key control for managing user input. */
	public KeyControl keyControl = new KeyControl(this);

	/** The sound manager for handling game sounds. */
	Sound sound = new Sound();

	/** The collision checker for detecting collisions in the maze. */
	public CollisionCheck checkCollision = new CollisionCheck(this);

	/** The asset manager for setting up the maze. */
	public SetAsset setAsset = new SetAsset(this);

	 /** The main character inside the maze. */
	public MainCharacter mainCharacter = new MainCharacter(this,keyControl);
	
	/** The user interface manager for rendering UI elements. */
	public UserInterface ui = new UserInterface(this);
	
	/** The thread for running the maze game. */
	Thread gameThread;
	
	/** The array of objects in the maze. */
	public ObjectFactory obj[] = new ObjectFactory[10];

	public Entity npc[] = new Entity[10];

	//for gamestate mangement
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int stopState = 2;
	public final int dialogueState = 3;

	/**
     * Constructs a new GamePanel with default settings.
     */
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //setting the size of panel
		this.setBackground(Color.black); //setting default background as black
		this.setDoubleBuffered(true);

		//key control method
		this.addKeyListener(keyControl);
		this.setFocusable(true);
	}

	/**
     * Initializes the game settings and assets.
     */
	public void setGame() {
		setAsset.setObj();
		setAsset.setNpc();

		//map 1 music
		musicPlay(0);
		musicStop();
		gameState = titleState;
	}

	/**
     * Starts the game thread.
     */
	public void gameStartThread() {
		//passing gamePanel class to thread constructor
		gameThread = new Thread(this);
		gameThread.start();
	}

	/**
	 * The main game loop controlling the game's update and rendering process.
	 * It runs continuously as long as the game thread is active, ensuring a consistent frame rate.
	 */
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

	/**
     * Updates the game logic reflecting towards the main character movement.
     */
	public void update() {
		if(gameState == playState) {
			//movement for the character
			mainCharacter.update();

			//npc
			for(int i = 0; i < npc.length; i++){
				if(npc[i] != null){
					npc[i].update();
				}
			}
		}
		if(gameState == stopState) {
			
		}
		
	}

	/**
	 * This method draws the game tiles, objects, main character, and UI components on the screen.
	 * @param g The Graphics object used for rendering.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;
		//tile
		tileFactory.draw(g2);

		// Title Screen
		if(gameState == titleState) {
			ui.draw(g2);
		}
		// Otherwise (in-game)
		else {
			//object
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}

			//main character
			mainCharacter.draw(g2);

			//npc
			for(int i = 0; i < npc.length; i++){ //XXX
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}

			//ui draw method
			ui.draw(g2);
			g2.dispose();
		}
	}

	/**
     * Plays the specified music track.
     * @param i The index of the music track to play.
     */
	public void musicPlay(int i) {
		sound.fileSet(i);
		sound.play();
		sound.loop();
	}

	/**
     * Stops the current music.
     */
	public void musicStop() {
		sound.stop();
	}

	/**
     * Plays a sound effect for the specified object.
     * @param i The index of the sound effect to play.
     */
	public void soundEffectObj(int i) {
		sound.fileSet(i);
		sound.play();
	}
}