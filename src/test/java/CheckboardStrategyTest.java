import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckboardStrategyTest {
    private BoardFeedback board;
    private Heatmap_Impl heatmap;
    private CheckboardStrategy strategy;

    @BeforeEach
    public void setUp() {
        ShipManager shipManager = new ShipManager();
        board = new BoardFeedback(10, 10);
        heatmap = new Heatmap_Impl(board, shipManager);
        strategy = new CheckboardStrategy();
    }

    @Test
    public void testGetNextMove() {
        int[] expectedMove = {0, 0};
        int[] move = strategy.getNextMove(heatmap, board);
        assertArrayEquals(expectedMove, move);
    }
}