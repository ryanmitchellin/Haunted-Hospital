package Documents.main;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class GamePanelTest {
    private GamePanel gamePanel;

    @BeforeEach
    void setup() {
        gamePanel = new GamePanel();
        gamePanel.gameState = gamePanel.playState;
        gamePanel.mainCharacter.keyNum = 5;
    }

    @Test
    void resetGameStateAndCharacterKeyCount() {
        gamePanel.resetGame();

        assertEquals(gamePanel.titleState, gamePanel.gameState, "it should go back to title state");
        assertEquals(0, gamePanel.mainCharacter.keyNum, "main character key should reset to 0");
        assertEquals(101, UserInterface.score, "score should reset");
    }

    
}