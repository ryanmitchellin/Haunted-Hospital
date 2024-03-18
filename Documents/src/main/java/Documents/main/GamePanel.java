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


public class GamePanel extends JPanel implements Runnable{
	//Screening setting

	final int originalTileSize = 16; //Tile will be 16x16 as default
	final int scale = 3; // 16*3 Scaling to 48 pixel

	//public to ensure the MainCharacter has access to the the tileSize
	public final int tileSize = originalTileSize * scale; //48 pixel per tile
	
	//setting the screen size 4:3 default
	public final int maxCol = 16;
	public final int maxRow = 12;
	public final int screenWidth = tileSize * maxCol; // Width of the screen : 1152 pixels
	public final int screenHeight = tileSize * maxRow; // Height of the screen : 864 pixels

	//setting world map
	public final int maxWCol = 50;
	public final int maxWRow = 50;

	//Frames per second
	int FPS = 60;
	
	TileFactory tileFactory = new TileFactory(this);	
	public KeyControl keyControl = new KeyControl(this);
	Sound sound = new Sound();
	public CollisionCheck checkCollision = new CollisionCheck(this);
	public SetAsset setAsset = new SetAsset(this);
	public MainCharacter mainCharacter = new MainCharacter(this,keyControl);
	public UserInterface ui = new UserInterface(this);
	Thread gameThread;
	//object
	public ObjectFactory obj[] = new ObjectFactory[10];
	public Entity npc[] = new Entity[10];

	//for gamestate mangement
	public int gameState;
	public final int playState = 1;
	public final int stopState = 2;
	public final int dialogueState = 3;


	//constructor
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //setting the size of panel
		this.setBackground(Color.black); //setting default background as black
		this.setDoubleBuffered(true);

		//key control method
		this.addKeyListener(keyControl);
		this.setFocusable(true);
	}

	public void setGame() {
		setAsset.setObj();
		setAsset.setNpc();

		//map 1 music
		musicPlay(0);
		musicStop();
		gameState = playState;
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
		if(gameState == playState) {
			//movement for the character
			mainCharacter.move();

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

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;
		//tile
		tileFactory.draw(g2);
		//object
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}

		//maincharacter 
		mainCharacter.draw(g2);

		//npc
		for(int i = 0; i < npc.length; i++){ //XXX
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}

		//ui draw method
		ui.draw(g2);
		g2.dispose();
	}

	public void musicPlay(int i) {
		sound.fileSet(i);
		sound.play();
		sound.loop();
	}

	public void musicStop() {
		sound.stop();
	}

	public void soundEffectObj(int i) {
		sound.fileSet(i);
		sound.play();
	}
}