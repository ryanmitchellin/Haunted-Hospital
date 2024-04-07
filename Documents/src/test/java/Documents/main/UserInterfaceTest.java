package Documents.main;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import Documents.entity.*;
import Documents.object.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

import Documents.entity.MainCharacter;

import Documents.main.UtilityTools;
import java.awt.image.BufferedImage;

public class UserInterfaceTest {
    
     private static CollisionCheck collisionCheck;
    private static GamePanel gp;
    private static KeyControl keyControl;
    private static MainCharacter mainCharacter;
    private static SetAsset setAsset;
    private static UserInterface ui;
    private static Graphics2D g2;
    @BeforeAll
    public static void setup(){
        gp = new GamePanel();
        collisionCheck = new CollisionCheck(gp);
        keyControl = new KeyControl(gp);
        mainCharacter = new MainCharacter(gp, keyControl);
        setAsset = new SetAsset(gp);
        ui = new  UserInterface(gp);
        g2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB).createGraphics();

    }

    @Test
    public void displayMessageTest(){
        ui.displayMessage("Hello World!");
        assertNotNull(ui.msg);
        assertTrue(ui.printMessage);

    }

    @Test
    public void drawTest(){
        
        
        gp.gameState = gp.titleState;
        ui.draw(g2);
        gp.gameState = gp.deathState;
        ui.draw(g2);
        gp.gameState = gp.winState;
        ui.draw(g2);
        gp.gameState = gp.playState;
        ui.draw(g2);
        ui.printMessage = true;
        ui.msgCount = 119;
        gp.gameState = gp.playState;
        ui.draw(g2);
        gp.gameState = gp.stopState;
        ui.draw(g2);
        gp.gameState = gp.dialogueState;
        ui.draw(g2);

    }
    @Test
    public void drawTitleScreenTest(){
        ui.commandingNumber = 0;
        ui.drawTitleScreen();
        ui.commandingNumber = 1;
        ui.drawTitleScreen();

    }

    @Test
    public void drawDeathScreenTest(){
        ui.commandingNumber = 0;
        ui.spriteCount = 0;
        ui.drawDeathScreen();
        ui.spriteCount = 10;
        ui.spriteNum = 1;
        ui.drawDeathScreen();
        ui.spriteNum = 2;
        ui.drawDeathScreen();
        ui.spriteNum = 3;
        ui.drawDeathScreen();
        ui.spriteNum = 4;
        ui.drawDeathScreen();
        ui.spriteNum = 5;
        ui.drawDeathScreen();
        ui.spriteNum = 6;
        ui.drawDeathScreen();
        ui.commandingNumber = 1;
        ui.drawDeathScreen();

    }

    @Test
    public void drawWinScreenTest(){
        ui.commandingNumber = 0;
        ui.spriteCount = 0;
        ui.drawWinScreen();
        ui.spriteCount = 10;
        ui.spriteNum = 1;
        ui.drawWinScreen();
        ui.spriteNum = 2;
        ui.drawWinScreen();
        ui.spriteNum = 3;
        ui.drawWinScreen();
        ui.spriteNum = 4;
        ui.drawWinScreen();
        ui.spriteNum = 5;
        ui.drawWinScreen();
        ui.commandingNumber = 1;
        ui.drawWinScreen();
    }

}
