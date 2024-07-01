import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Heatmap_ImplTest {

    private BoardFeedback boardFeedback;
    private Heatmap_Impl heatmap;
    private ShipManager shipManager;
    private BoardStart playerBoard;

    @BeforeEach
    public void setUp() {
        boardFeedback = new BoardFeedback(10, 10);
        shipManager = new ShipManager();
        heatmap = new Heatmap_Impl(boardFeedback, shipManager);
        // Populate the board with ship
        playerBoard = new BoardStart("PlayerBoard", 10, 10);
        shipManager.addShip(new Ship_Impl(2, "Destroyer", "1")); // Nave di lunghezza 2
        shipManager.addShip(new Ship_Impl(3, "Submarine", "2")); // Nave di lunghezza 3

        playerBoard.placeShip(0, 0, 0, shipManager.getShipById("1"));
        playerBoard.placeShip(2, 2, 1, shipManager.getShipById("2"));
    }

    @Test
    public void testUpdateHeatMap_initialState() {
        heatmap.updateheatMap();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertTrue(heatmap.getHeatmapValue(i, j) > 0, "Heatmap value at (" + i + ", " + j + ") should be greater than 0");
            }
        }
    }

    @Test
    public void testUpdateHeatMap_withHits() {
        // Simula colpi
        assertTrue(boardFeedback.addFeedBack(playerBoard, 2, 2, shipManager));
        heatmap.updateheatMap();

        // check if probability is higher
        assertEquals(0, heatmap.getHeatmapValue(2, 2), "Heatmap value at (2, 2) should be 0 because already hitted");
        assertTrue(heatmap.getHeatmapValue(2, 1) > 1, "Heatmap value at (2, 1) should be greater than 1");
        assertTrue(heatmap.getHeatmapValue(2, 3) > 3, "Heatmap value at (2, 3) should be greater than 3");
        assertTrue(heatmap.getHeatmapValue(1, 2) > 1, "Heatmap value at (1, 2) should be greater than 1");
        assertTrue(heatmap.getHeatmapValue(3, 2) > 3, "Heatmap value at (3, 2) should be greater than 3");
    }

    @Test
    public void testGetBestMove() {
        heatmap.updateheatMap();
        int[] bestMove = heatmap.getBestMove();
        assertNotNull(bestMove);
        assertEquals(2, bestMove.length);
    }

    @Test
    public void testGetBestMove_AfterHits() {
        assertTrue(boardFeedback.addFeedBack(playerBoard, 2, 2, shipManager));
        heatmap.updateheatMap();
        int[] bestMove = heatmap.getBestMove();
        assertNotNull(bestMove);
        assertEquals(2, bestMove.length);
        System.out.println(bestMove[0]);
        System.out.println(bestMove[1]);
    }
}