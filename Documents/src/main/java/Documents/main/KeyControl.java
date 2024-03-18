package Documents.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyControl implements KeyListener{
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	boolean checkDrawTime = false;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();//return int keyCode associated with the key in event

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
			case KeyEvent.VK_PAUSE:
				gp.gameState = gp.pauseState;
				break;
			case KeyEvent.VK_ENTER:
				enterPressed = true;
				break;
				
			default:
				break;
			}
		}
		// pause state
		else if(gp.gameState == gp.pauseState){
			if(code == KeyEvent.VK_PAUSE){
				gp.gameState = gp.playState;
			}
		}
		//dialogue state
		else if(gp.gameState == gp.dialogueState){
			if(code == KeyEvent.VK_ENTER){
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