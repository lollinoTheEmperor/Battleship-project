import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
public class Ship_ImplTest {
    Ship_Impl ship1 = new Ship_Impl(5, "sumberge", "nave1");
    Ship_Impl ship2 = new Ship_Impl(5, "sumberge", "nave2");
    Ship_Impl ship3 = new Ship_Impl(3, "sumberge", "nave3");
    Ship_Impl ship4 = new Ship_Impl(1, "sumberge", "nave3");
    @Test
    public void takeDamage_shouldReduceHealth() {
        ship1.takeDamage();
        assertEquals(4, ship1.getHp());
    }
    @Test
    public void shouldReduceto3(){
        ship2.takeDamage();
        ship2.takeDamage();
        assertEquals(3, ship2.getHp());
    }
    @Test
    public void shouldReducetoZero(){
        ship3.takeDamage();
        ship3.takeDamage();
        ship3.takeDamage();
        assertEquals(0, ship3.getHp());
    }
    @Test
    public void shouldStayZero(){
        ship4.takeDamage();
        ship4.takeDamage();
        ship4.takeDamage();
        assertEquals(0, ship4.getHp());
    }

}