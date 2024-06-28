import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class BotPlayerTest {
    private BoardFeedback board;
    private BoardStart opponentsBoard;
    private Heatmap_Impl heatmap;
    private CheckboardStrategy checkboardStrategy;
    private SpiralStrategy spiralStrategy;
    private BotPlayer botPlayer;

    @BeforeEach
    public void setUp() {
        ShipManager shipManager = new ShipManager();
        board = new BoardFeedback(10, 10);
        opponentsBoard = new BoardStart("opponentsBoard", 10, 10);
        heatmap = new Heatmap_Impl(board, shipManager);
        checkboardStrategy = new CheckboardStrategy();
        spiralStrategy = new SpiralStrategy();
        Ship_Impl ship = new Ship_Impl(3, "ship", "1");
        Ship_Impl ship2 = new Ship_Impl(3, "ship", "2");
        shipManager.addShip(ship);
        opponentsBoard.placeShip(0, 0, 0, ship);
        shipManager.addShip(ship2);
        opponentsBoard.placeShip(5, 5, 0, ship2);
        botPlayer = new BotPlayer("Bot", new BoardStart("Player1", 10, 10), opponentsBoard, board, shipManager);
    }

    @Test
    public void testChangeStrategy() {
        // Simula 4 mosse per cambiare strategia
        for (int i = 0; i < 4; i++) {
            botPlayer.makeMove();
        }
        int[] move = botPlayer.getNextMove();
        assertArrayEquals(new int[]{2, 0}, move); // Verifica che la strategia sia cambiata a SpiralStrategy
    }
    @Test
    public void testBotMakeMove() {
        botPlayer.makeMove();
        botPlayer.makeMove();
        botPlayer.makeMove();
        botPlayer.makeMove();
        botPlayer.makeMove();
        botPlayer.makeMove();
        botPlayer.makeMove();
        botPlayer.makeMove();
        botPlayer.makeMove();
    }
}