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
		tiles = new Tile[30];
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

			tiles[2] = new Tile();
			tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/002.png"));
			tiles[2].collision = true;

			tiles[3] = new Tile();
			tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/003.png"));
			tiles[3].collision = true;

			tiles[4] = new Tile();
			tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/004.png"));
			tiles[4].collision = true;

			tiles[5] = new Tile();
			tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/005.png"));
			tiles[5].collision = true;

			tiles[6] = new Tile();
			tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/006.png"));
			tiles[6].collision = true;

			tiles[7] = new Tile();
			tiles[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/007.png"));
			tiles[7].collision = true;

			tiles[8] = new Tile();
			tiles[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/008.png"));
			tiles[8].collision = true;

			tiles[9] = new Tile();
			tiles[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/009.png"));
			tiles[9].collision = true;

			tiles[10] = new Tile();
			tiles[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/010.png"));
			tiles[10].collision = true;

			tiles[11] = new Tile();
			tiles[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/011.png"));
			tiles[11].collision = true;

			tiles[12] = new Tile();
			tiles[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/012.png"));
			tiles[12].collision = true;

			tiles[13] = new Tile();
			tiles[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/013.png"));
			tiles[13].collision = true;

			tiles[14] = new Tile();
			tiles[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/014.png"));
			tiles[14].collision = true;

			tiles[15] = new Tile();
			tiles[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/015.png"));

			tiles[16] = new Tile();
			tiles[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/016.png"));
			tiles[16].collision = true;

			tiles[17] = new Tile();
			tiles[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/017.png"));
			tiles[17].collision = true;

			tiles[18] = new Tile();
			tiles[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/018.png"));
			tiles[18].collision = true;

			tiles[19] = new Tile();
			tiles[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/019.png"));

			tiles[20] = new Tile();
			tiles[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/020.png"));
			tiles[20].collision = true;

			tiles[21] = new Tile();
			tiles[21].image = ImageIO.read(getClass().getResourceAsStream("/tiles/021.png"));
			tiles[21].collision = true;

			tiles[22] = new Tile();
			tiles[22].image = ImageIO.read(getClass().getResourceAsStream("/tiles/022.png"));
			tiles[22].collision = true;

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

				while(column < gp.maxWCol) {
					//splitting line and getting tile type number from .txt
					String numbers[] = line.split(" ");
					//convert string to number
					int num = Integer.parseInt(numbers[column]);

					tileMapNum[column][row] = num;
					column++;
				}
				//only accept max number of col that is preset in gamepanel
				if(column == gp.maxWCol) {
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

			if(column == gp.maxWCol) {
				column = 0;
				row++;
			}

		}
	}
}