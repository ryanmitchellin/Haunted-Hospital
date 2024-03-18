package Documents.tile;

import Documents.main.GamePanel;
import Documents.main.UtilityTools;
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
		tiles = new Tile[50];
		tileMapNum = new int[gp.maxWCol][gp.maxWRow];

		gettingTileImg();
		loadingMap("/maps/map01.txt");
	}

	public void gettingTileImg() {
		setup(0, "voidspace", false);
		setup(1, "rightsideWallEnd", true);
		setup(2, "blueFloor", false);
		setup(3, "upWallRight", true);
		setup(4, "orangeWallRight", true);
		setup(5, "leftsideWallEnd", true);
		setup(6, "cornerRightBottomToUp", true);
		setup(7, "bottomWallCenter", true);
		setup(8, "connector1", true);
		setup(9, "connector2", true);
		setup(10, "blueWallCenter", true);
		setup(11, "blueWallLeft", true);
		setup(12, "upWallCen", true);
		setup(13, "roomFloor", false);
		setup(14, "leftsideWall", true);
		setup(15, "orangeWallCenter", true);
		setup(16, "blueWallRight", true);
		setup(17, "upWallLeft", true);
		setup(18, "orangeWallLeft", true);
		setup(19, "grayFloor", false);
		setup(20, "rightsideWall", true);
		setup(21, "cornerLeftBottomToup", true);

		
	}
	public void setup(int i, String imgPath,boolean collision) {
		UtilityTools tools = new UtilityTools();
		try {
			tiles[i] = new Tile();
		    BufferedImage originalImage = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imgPath + ".png"));
		    //ensure the image supports alpha (transparency)
		    BufferedImage newImg = new BufferedImage(gp.tileSize, gp.tileSize, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2 = newImg.createGraphics();
		    g2.drawImage(originalImage, 0, 0, gp.tileSize, gp.tileSize, null);
		    g2.dispose();
		    tiles[i].image = newImg;
		    tiles[i].collision = collision;
		} catch(IOException e) {
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
				g2.drawImage(tiles[tileNum].image, screenX, screenY, null);
			}
			column++;

			if(column == gp.maxWCol) {
				column = 0;
				row++;
			}

		}
	}
}