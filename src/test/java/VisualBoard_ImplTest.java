import java.awt.*;
import java.util.Set;

import org.junit.Test;

public class VisualBoard_ImplTest {

    final boolean isP1 = true;
    final int turnsForHealing = 2;
    protected int nTurns = 6 *2;                                                                                        // 2 turn for 2 player

    //// This will be an input for the method, so it would be deleted ///////////////
    protected Ship_Impl shipS = new Ship_Impl(2, "light", "1");   // int size, String type, String id
    protected Ship_Impl shipM = new Ship_Impl(3, "medium", "2");   // int size, String type, String id
    protected Ship_Impl shipL = new Ship_Impl(4, "heavy", "3");   // int size, String type, String id

    protected Ship_Heal shipHeal = new Ship_Heal(3, "medium", "5");   // int size, String type, String id
    protected Ship_Heavy shipHeavy = new Ship_Heavy(4, "heavy", "4");   // int size, String type, String id

    public ShipManager shipManagerP1 = new ShipManager();
    public ShipManager shipManagerP2 = new ShipManager();



    public void stopBackendUntil(boolean[] condition) {
        while (!condition[0]) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void testing_boardStartP1AndP2() {

        BoardStart boardStartP1 = new BoardStart("Layout P1", 10,10);
        BoardStart boardStartP2 = new BoardStart("Layout P2", 10,10);

        BoardFeedback boardFeedbackP1 = new BoardFeedback(10,10);
        BoardFeedback boardFeedbackP2 = new BoardFeedback(10,10);

        shipManagerP1.addShip(shipHeal);
//        shipManagerP1.addShip(shipS);
//        shipManagerP1.addShip(shipM);
//        shipManagerP1.addShip(shipL);

        shipManagerP2.addShip(shipHeavy);
//        shipManagerP2.addShip(shipS);
//        shipManagerP2.addShip(shipM);
//        shipManagerP2.addShip(shipL);

        Player_Impl me = new Player_Impl("me", boardStartP1, boardFeedbackP1, shipManagerP1, shipManagerP2);
        Player_Impl opponent = new Player_Impl("opponent", boardStartP2, boardFeedbackP2, shipManagerP2, shipManagerP1);

        me.setOpponentsBoard(boardStartP2);
        opponent.setOpponentsBoard(boardStartP1);

        ////////////////////////////////////////////////////////////////////


        System.out.printf("Hello and welcome!\n");


//    Map.of(1, shipS, 2, shipM, 3, shipL);
        VisualBoard_Impl gameWindow = new VisualBoard_Impl(me, opponent);

// usare i player
        gameWindow.fetchingShips(boardStartP1, shipManagerP1.ships, isP1);

        // TODO generate islands
        int x = 3, y = 4;
        gameWindow.paintIsland(x, y, true);
        opponent.myFeedbacks.placeIsland(x, y);

        stopBackendUntil(gameWindow.allShipPlaced);

        gameWindow.fetchingShips(boardStartP2, shipManagerP2.ships, !isP1);

        // TODO generate islands
        gameWindow.paintIsland(x, y, false);
        me.myFeedbacks.placeIsland(x, y);
        opponent.myFeedbacks.placeIsland(x, y);

        stopBackendUntil(gameWindow.allShipPlaced);


        System.out.println("Now starting game session - all ships are located");
        gameWindow.createGameBoards(me, opponent);

        gameWindow.repaintMap(me.myFeedbacks, isP1);
        gameWindow.repaintMap(opponent.myFeedbacks, !isP1);


        for (int i = 1; i < nTurns + 1; i++) {
            gameWindow.turnP1();
            stopBackendUntil(gameWindow.endTurn);

            if (!opponent.hasShips()) {
                break;
            }
            final int current_turn = i;
            gameWindow.turnP2();
            stopBackendUntil(gameWindow.endTurn);

            if (!me.hasShips()) {
                break;
            }

            if (i % turnsForHealing == 0) {
                me.shipManager.ships.values().stream()
                        .filter(ship -> ship instanceof Ship_Heal)
                        .forEach(ship -> ((Ship_Heal) ship).heal(me.myBoard, opponent.myFeedbacks, current_turn));

                opponent.shipManager.ships.values().stream()
                        .filter(ship -> ship instanceof Ship_Heal)
                        .forEach(ship -> ((Ship_Heal) ship).heal(opponent.myBoard, me.myFeedbacks, current_turn));

                gameWindow.repaintMap(me.myFeedbacks, isP1);
                gameWindow.repaintMap(opponent.myFeedbacks, !isP1);
            }
        }
    }
}