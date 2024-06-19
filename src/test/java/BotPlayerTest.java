import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class BotPlayerTest {
    private ShipManager shipManager;
    private BoardStart myBoard;
    private BoardStart opponentsBoard;
    private BoardFeedback myFeedback;
    private BotPlayer botPlayer;

    @BeforeEach
    public void setUp() {
        shipManager = new ShipManager();
        myBoard = new BoardStart("myBoard", 10, 10);
        opponentsBoard = new BoardStart("opponentsBoard", 10, 10);
        myFeedback = new BoardFeedback(10, 10);

        Ship_Impl ship = new Ship_Impl(3, "ship", "1");
        shipManager.addShip(ship);
        opponentsBoard.placeShip(0, 0, 0, ship);

        botPlayer = new BotPlayer("Bot", myBoard, opponentsBoard, myFeedback, shipManager);
    }

    @Test
    public void testBotMakeMove() {
        botPlayer.updateHeatmap();
        assertTrue(botPlayer.makeMove());
        botPlayer.updateHeatmap();
        botPlayer.makeMove();
        botPlayer.updateHeatmap();
        botPlayer.makeMove();
        botPlayer.updateHeatmap();
        botPlayer.makeMove();
        botPlayer.updateHeatmap();
        botPlayer.makeMove();
        botPlayer.updateHeatmap();
        botPlayer.makeMove();
    }
}