package Documents.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyControl implements KeyListener{
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	boolean checkDrawTime = false;

	public KeyControl(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();//return int keyCode associated with the key in event

		// Title State
		if (gp.gameState == gp.titleState) {
			if (code == KeyEvent.VK_UP) {
				gp.ui.commandingNumber--;
				if (gp.ui.commandingNumber < 0) {
					gp.ui.commandingNumber = 2;
				}
			}
			if (code == KeyEvent.VK_DOWN) {
				gp.ui.commandingNumber++;
				if (gp.ui.commandingNumber > 2) {
					gp.ui.commandingNumber = 0;
				}
			}
			if (code == KeyEvent.VK_ENTER) {
				if (gp.ui.commandingNumber == 0) {
					gp.gameState = gp.playState;
					gp.musicPlay(0);
				}
				if (gp.ui.commandingNumber == 1) {
					// add later
				}
				if (gp.ui.commandingNumber == 2) {
					System.exit(0);
				}
			}
		}
		//play state
		if(gp.gameState == gp.playState){
			switch(code) {
			case KeyEvent.VK_UP:
				upPressed = true;
				break;
			case KeyEvent.VK_DOWN:
				downPressed = true;
				break;
			case KeyEvent.VK_LEFT:
				leftPressed = true;
				break;
			case KeyEvent.VK_RIGHT:
				rightPressed = true;
				break;
			case KeyEvent.VK_P:
				gp.gameState = gp.stopState;
				break;
			case KeyEvent.VK_SPACE:
				enterPressed = true;
				break;
				
			default:
				break;
			}
		}
		// pause state
		else if(gp.gameState == gp.stopState){
			if(code == KeyEvent.VK_P){
				gp.gameState = gp.playState;
			}
		}
		//dialogue state
		else if(gp.gameState == gp.dialogueState){
			if(code == KeyEvent.VK_SPACE){
				gp.gameState = gp.playState;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();//return int keyCode associated with the key in event

		switch(code) {
		case KeyEvent.VK_UP:
			upPressed = false;
			break;
		case KeyEvent.VK_DOWN:
			downPressed = false;
			break;
		case KeyEvent.VK_LEFT:
			leftPressed = false;
			break;
		case KeyEvent.VK_RIGHT:
			rightPressed = false;
			break;
		default:
			break;
		}
	}
}