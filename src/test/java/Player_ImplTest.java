import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class Player_ImplTest {
    Ship_Impl ship=new Ship_Impl(3,"ship","1");
    List<Ship_Impl> ships=new ArrayList<>();
    BoardStart opponentsBoard=new BoardStart("opponentsBoard");
    BoardFeedback myFeedback=new BoardFeedback("myFeedback");
    Player_Impl me=new Player_Impl("me",null,myFeedback,ships);

    @BeforeAll
    public void setUpBeforeClass() throws Exception {
        ships.add(ship);
        opponentsBoard.putWater();
        opponentsBoard.placeShip(0,0,0,ship);
        me.setOpponentsBoard(opponentsBoard);
    }

    @Test
    public void bo() {
        assertTrue(me.attack(0,0));
    }
}
