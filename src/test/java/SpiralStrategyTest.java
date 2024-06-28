import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpiralStrategyTest {
    private BoardFeedback board;
    private Heatmap_Impl heatmap;
    private SpiralStrategy strategy;

    @BeforeEach
    public void setUp() {
        ShipManager shipManager = new ShipManager();
        board = new BoardFeedback(10, 10);
        heatmap = new Heatmap_Impl(board, shipManager);
        strategy = new SpiralStrategy();
    }

    @Test
    public void testGetNextMove() {
        int[] expectedMove = {5, 5}; // Primo movimento a spirale dal centro
        int[] move = strategy.getNextMove(heatmap, board);
        assertArrayEquals(expectedMove, move);
    }
}