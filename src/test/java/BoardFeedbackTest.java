import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardFeedbackTest {
    BoardFeedback bf1 = new BoardFeedback();
    BoardStart bs1 = new BoardStart("Board 1");
    ShipManager all_ship = new ShipManager();
    @BeforeEach
    public void setUp() {
        Ship_Impl s1 = new Ship_Impl(5, "sumberge", "1");
        Ship_Impl s2 = new Ship_Impl(5, "sumberge", "2");
        Ship_Impl s3 = new Ship_Impl(4, "sumberge", "3");
        all_ship.addShip(s1);
        all_ship.addShip(s2);
        all_ship.addShip(s3);
        bs1.placeShip(1,1,1,s1);
        bs1.placeShip(2,2,0,s2);
        bs1.placeShip(9,2,1,s3);
    }

    @Test
    public void addFeedBack_hit() {
        assertEquals(true,bf1.addFeedBack(bs1,2,2,all_ship));
    }

    @Test
    public void addFeedBack_hit_2() {
        assertEquals(true,bf1.addFeedBack(bs1,3,2,all_ship));
    }

    @Test
    public void addFeedBack_miss() {
        assertEquals(false,bf1.addFeedBack(bs1,0,0,all_ship));
    }

    @Test
    public void addFeedBack_hit_already() {
        bf1.addFeedBack(bs1,2,2,all_ship);
        assertEquals(true,bf1.addFeedBack(bs1,2,2,all_ship));
    }

    @Test
    public void addFeedBack_outside_board() {
        assertEquals(false,bf1.addFeedBack(bs1,10,10,all_ship));
    }
}