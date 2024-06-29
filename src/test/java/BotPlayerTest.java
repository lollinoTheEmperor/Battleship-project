import org.junit.jupiter.api.Nested;
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

        ShipManager opponentsShipManager = new ShipManager();
        Ship_Impl opShip = new Ship_Impl(3, "ship", "1");
        Ship_Impl opShip2 = new Ship_Impl(3, "ship", "2");
        opponentsShipManager.addShip(ship);
        opponentsBoard.placeShip(0, 0, 0, opShip);
        opponentsShipManager.addShip(ship2);
        opponentsBoard.placeShip(5, 5, 0, opShip2);

        botPlayer = new BotPlayer("Bot", new BoardStart("Player1", 10, 10), opponentsBoard, board, shipManager, opponentsShipManager);
    }

    @Test
    public void testChangeStrategy() {
        // Simulate 4 move and then change strategy
        for (int i = 0; i < 4; i++) {
            botPlayer.makeMove();
        }
        int[] move = botPlayer.getNextMove();
        assertArrayEquals(new int[]{2, 0}, move); // check if the strategy is correct (so the adjante one)
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

    //will always be true but when uncommented the related parts in BotPlayer we can visualise the board
    @Nested
    class ShipPlacement {

        @Test
        public void FirstPlacement(){
            assertTrue(true);
        }

        @Test
        public void SecondPlacement(){
            assertTrue(true);
        }

        @Test
        public void ThirdPlacement(){
            assertTrue(true);
        }

        @Test
        public void FourthPlacement(){
            assertTrue(true);
        }

        @Test
        public void FifthPlacement(){
            assertTrue(true);
        }
    }
}