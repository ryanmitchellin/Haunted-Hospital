package Documents.main;

/*
    The png used for tiles were created by Jea Oh Lee (301351043)

    List of created *.png used in tile:
    voidspace
    rightsideWallEnd
    blueFloor
    upWallRight
    orangeWallRight
    leftsideWallEnd
    cornerRightBottomToUp
    bottomWallCenter
    connector1
    connector2
    blueWallCenter
    blueWallLeft
    upWallCen
    roomFloor
    leftsideWall
    orangeWallCenter
    blueWallRight
    upWallLeft
    orangeWallLeft
    grayFloor
    rightsideWallcornerLeftBottomToup

    List of created *.png used in object:
    door
    stair
    cardkey

*/

import javax.swing.JFrame;
import java.awt.Dimension;


public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame ();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("CMPT 276 Project Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        //to make the window to be the sized fit of the preferred size and the layouts of
        //its subcomponents GamePanel
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setGame();
        gamePanel.gameStartThread();
    }
}