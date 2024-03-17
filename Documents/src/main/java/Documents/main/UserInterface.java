package Documents.main;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;

public class UserInterface {
	GamePanel gp;
	Font arialFont;


	public UserInterface(GamePanel gp) {
		this.gp = gp;
		arialFont = new Font("Arial", Font.PLAIN, 40);
	}

	public void draw(Graphics2D g2) {
		g2.setFont(arialFont);
		g2.setColor(Color.white);
		g2.drawString("Card Key #" + gp.mainCharacter.keyNum, 50, 50);
	}
}