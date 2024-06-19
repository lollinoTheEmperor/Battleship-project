import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class Player_ImplTest {
    Ship_Impl ship=new Ship_Impl(3,"ship","1");
    ShipManager shipManager = new ShipManager();
    BoardStart myBoard=new BoardStart("myBoard");
    BoardStart opponentsBoard=new BoardStart("opponentsBoard");
    BoardFeedback myFeedback=new BoardFeedback();
    Player_Impl me=new Player_Impl("me",myBoard,myFeedback, shipManager);

    @BeforeEach
    public void setUpBeforeClass() throws Exception {
        shipManager.addShip(ship);
        // TODO check method is it already called previously ?
        //  opponentsBoard.putWater();
        opponentsBoard.placeShip(0,0,0,ship);
        me.setOpponentsBoard(opponentsBoard);
    }

    @Test
    public void bo() {
        assertTrue(me.attack(0,0));
    }
}
