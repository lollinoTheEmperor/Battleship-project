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
        Ship_Impl ship = new Ship_Impl(3, "ship", "1");
        Ship_Impl ship2 = new Ship_Impl(3, "ship", "2");
        Ship_Impl opShip2 = new Ship_Impl(3, "ship", "2");
        Ship_Impl opShip = new Ship_Impl(3, "ship", "1");

        ShipManager shipManager = new ShipManager();
        ShipManager opponentsShipManager = new ShipManager();

        board = new BoardFeedback(10, 10);
        opponentsBoard = new BoardStart("opponentsBoard", 10, 10);

        heatmap = new Heatmap_Impl(board, shipManager);
        checkboardStrategy = new CheckboardStrategy();
        spiralStrategy = new SpiralStrategy();

        shipManager.addShip(ship);
        shipManager.addShip(ship2);

        opponentsShipManager.addShip(ship);
        opponentsShipManager.addShip(ship2);

        opponentsBoard.placeShip(0, 0, 0, ship);
        opponentsBoard.placeShip(5, 5, 0, ship2);
        opponentsBoard.placeShip(0, 0, 0, opShip);
        opponentsBoard.placeShip(5, 5, 0, opShip2);

        botPlayer = new BotPlayer("Bot", new BoardStart("Player1", 10, 10), board, shipManager, opponentsShipManager);
        botPlayer.setOpponentsBoard(opponentsBoard);
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