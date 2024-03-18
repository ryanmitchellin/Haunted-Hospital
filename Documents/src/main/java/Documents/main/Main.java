package Documents.main;

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