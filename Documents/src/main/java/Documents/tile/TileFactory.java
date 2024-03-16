package Documents.tile;

import Documents.main.GamePanel;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;



public class TileFactory {
	GamePanel gp;
	Tile[] tiles;
	int tileMapNum[][];

	public TileFactory(GamePanel gp) {
		this.gp = gp;
		tiles = new Tile[20];
		tileMapNum = new int[gp.maxCol][gp.maxRow];
		gettingTileImg();
		loadingMap("/maps/map01.txt");
	}

	public void gettingTileImg() {
		try {
			tiles[0] = new Tile();
			//outside the map (the gray area)
			tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/outside.png"));


			tiles[1] = new Tile();
			//main flooring
			tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor.png"));

			tiles[2] = new Tile();
			//wall left
			tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wallleft.png"));
			tiles[2].collision = true;

			tiles[3] = new Tile();
			//wall right
			tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wallright.png"));
			tiles[3].collision = true;

			tiles[4] = new Tile();
			//wall left connector
			tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wallConnectLeft.png"));
			tiles[4].collision = true;

			tiles[5] = new Tile();
			//wall right connector
			tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wallConnectRight.png"));
			tiles[5].collision = true;

			tiles[6] = new Tile();
			//wall bottom
			tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bottomWall.png"));
			tiles[6].collision = true;

			tiles[7] = new Tile();
			//wall bottom Right Dot
			tiles[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bottomWAlltoRight.png"));
			tiles[7].collision = true;

			tiles[8] = new Tile();
			//wall bottom Left Dot 
			tiles[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bottomWallLeft.png"));
			tiles[8].collision = true;

			tiles[10] = new Tile();
			//wall bottom Left Dot 
			tiles[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/leftWall.png"));
			tiles[10].collision = true;

			tiles[11] = new Tile();
			//wall bottom Left Dot 
			tiles[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/centerWall.png"));
			tiles[11].collision = true;

			tiles[12] = new Tile();
			//wall bottom Left Dot 
			tiles[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rightWall.png"));
			tiles[12].collision = true;

		} catch(IOException e) {
    		//checking if the image has been loaded correctly
    		//throw error
        	e.printStackTrace();
    	}
	}
	//loading map from the txt file
	public void loadingMap(String fileName) {
		try {
			InputStream is = getClass().getResourceAsStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int column = 0;
			int row = 0;

			while(column < gp.maxCol && row < gp.maxRow) {
				String line = br.readLine();//read a line of .txt

				while(column < gp.maxCol) {
					//splitting line and getting tile type number from .txt
					String numbers[] = line.split(" ");
					//convert string to number
					int num = Integer.parseInt(numbers[column]);

					tileMapNum[column][row] = num;
					column++;
				}
				//only accept max number of col that is preset in gamepanel
				if(column == gp.maxCol) {
					column = 0;
					//go to next row if the col is maxed
					row++;
				}
			}	


		}catch(Exception e) {
			//failed to read text file for map
		}
	}

    
    public Tile getTile(int index) {
        return tiles[index];
    }

    public int getTileMapNum(int col, int row) {
         return tileMapNum[col][row];
    }

	public void draw(Graphics2D g2) {
		int column = 0;
		int row = 0;
		int xPos = 0;
		int yPos = 0;

		while(column < gp.maxCol && row < gp.maxRow) {
			//extracting tile map number which stored in tileMapNum[0][0]
			//use this as index for type of tile that needs to be printed on the map
			int tileNum = tileMapNum[column][row];

			g2.drawImage(tiles[tileNum].image, xPos, yPos, gp.tileSize, gp.tileSize, null);
			column++;
			xPos += gp.tileSize;

			if(column == gp.maxCol) {
				column = 0;
				xPos = 0;
				row++;
				yPos += gp.tileSize;
			}

		}
	}
}