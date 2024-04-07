package Documents.main;

import org.junit.jupiter.api.Test;

import Documents.entity.*;
import Documents.object.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

public class SetAssetTest {

    private static CollisionCheck collisionCheck;
    private static GamePanel gp;
    private static KeyControl keyControl;
    private static MainCharacter mainCharacter;
    private static SetAsset setAsset;
    

    @BeforeAll
    public void setup(){
        gp = new GamePanel();
        collisionCheck = new CollisionCheck(gp);
        keyControl = new KeyControl(gp);
        mainCharacter = new MainCharacter(gp, keyControl);
        setAsset = new SetAsset(gp);
    }

    @Test
    public void setObjTest(){
        setAsset.setObj();
        for(int i = 0; i<8; i++){
            assertEquals(true, gp.obj[i] instanceof KeyCard);
        }
        assertEquals(true, gp.obj[8] instanceof Door);
        assertEquals(true, gp.obj[9] instanceof Stair);
    }

    @Test
    public void setGhostTest(){
        setAsset.setGhost();
        assertEquals(true, gp.monster[0] instanceof Ghost);
        assertEquals(true, gp.monster[1] instanceof Ghost);
    }
}
