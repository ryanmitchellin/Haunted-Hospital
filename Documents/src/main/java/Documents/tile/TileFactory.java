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
		tileMapNum = new int[gp.maxWCol][gp.maxWRow];
		gettingTileImg();
		loadingMap("/maps/map01.txt");
	}

	public void gettingTileImg() {
		try {
			tiles[0] = new Tile();
			tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/000.png"));


			tiles[1] = new Tile();
			tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/001.png"));

			//grey wall center
			tiles[2] = new Tile();
			tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/002.png"));
			tiles[2].collision = true;

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

			while(column < gp.maxWCol && row < gp.maxWRow) {
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

		while(column < gp.maxWCol && row < gp.maxWRow) {
			//extracting tile map number which stored in tileMapNum[0][0]
			//use this as index for type of tile that needs to be printed on the map
			int tileNum = tileMapNum[column][row];

			int worldX = column * gp.tileSize;
			int worldY = row * gp.tileSize;
			int screenX = worldX - gp.mainCharacter.wxPos + gp.mainCharacter.screenX;
			int screenY = worldY - gp.mainCharacter.wyPos + gp.mainCharacter.screenY;

			//checking if the tile is within the boundary
			if(worldX + gp.tileSize > gp.mainCharacter.wxPos - gp.mainCharacter.screenX &&
			   worldX - gp.tileSize < gp.mainCharacter.wxPos + gp.mainCharacter.screenX &&
			   worldY + gp.tileSize > gp.mainCharacter.wyPos - gp.mainCharacter.screenY &&
			   worldY - gp.tileSize < gp.mainCharacter.wyPos + gp.mainCharacter.screenY) {
				g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			column++;

			if(column == gp.maxCol) {
				column = 0;
				row++;
			}

		}
	}
}