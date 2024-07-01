import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameBoard_ImplTest {
    GameBoard_Impl g1 = new GameBoard_Impl();

    @Test
    public void getCell_that_exist() {
        assertEquals("water", g1.getCell(1,1));
    }

    @Test
    public void getCell_that_doesnt_exist_x_y() {
        assertEquals(null, g1.getCell(10,10));
    }
    @Test
    public void getCell_that_doesnt_exist_x() {
        assertEquals(null, g1.getCell(10,2));
    }
    @Test
    public void getCell_that_doesnt_exist_y() {
        assertEquals(null, g1.getCell(2,10));
    }
}