import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardStartTest {
    Ship_Impl s1 = new Ship_Impl(5, "sumberge", "nave1");
    Ship_Impl s2 = new Ship_Impl(5, "sumberge", "nave2");
    Ship_Impl s3 = new Ship_Impl(11, "sumberge", "nave3");
    BoardStart b1 = new BoardStart("Board 1");

    @Test
    public  void placeShip_correct() {
        assertEquals(true, b1.placeShip(2,2,1,s1));
    }

    @Test
    public  void placeShip_one_over_other() {
        b1.placeShip(2,2,1,s2);
        assertEquals(false, b1.placeShip(2,2,1,s1));
    }

    @Test
    public  void placeShip_outside() {
        assertEquals(false, b1.placeShip(10,10,1,s1));
    }

    @Test
    public  void placeShip_in_but_to_big() {
        assertEquals(false, b1.placeShip(0,0,1,s3));
    }

    @Test
    public  void placeShip_collide() {
        b1.placeShip(1,1,1,s1);
        assertEquals(false, b1.placeShip(1,2,1,s2));
    }
}