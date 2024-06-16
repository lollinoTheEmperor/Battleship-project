import java.awt.*;
import java.util.Set;

import org.junit.Test;

public class VisualBoard_ImplTest {

    final boolean isP1 = true;

    //// This will be an input for the method, so it would be deleted ///////////////
    protected Ship_Impl shipS = new Ship_Impl(2, "light", "1");   // int size, String type, String id
    protected Ship_Impl shipM = new Ship_Impl(2, "medium", "2");   // int size, String type, String id
    protected Ship_Impl shipL = new Ship_Impl(2, "heavy", "3");   // int size, String type, String id

    protected Set<Ship_Impl> ships = Set.of(shipS);
    //    protected Set<Ship_Impl> ships = Set.of(shipS, shipM, shipL);
    VisualBoard_Impl gameWindow = new VisualBoard_Impl();



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

        BoardStart boardStartP1 = new BoardStart("Layout P1");
        BoardStart boardStartP2 = new BoardStart("Layout P2");
        ////////////////////////////////////////////////////////////////////


        System.out.printf("Hello and welcome!\n");
//        VisualBoard_Impl gameWindow = new VisualBoard_Impl("Aldo", "GianPaolo89");


        gameWindow.fetchingShips(boardStartP1, ships, isP1);
        gameWindow.paintIsland(3,3, VisualBoard_Impl.MapElements.FETCHING_GRID_PANEL_P1.getValue());
        stopBackendUntil(gameWindow.allShipPlaced);


        gameWindow.fetchingShips(boardStartP2, ships, !isP1);
        stopBackendUntil(gameWindow.allShipPlaced);


        System.out.println("Now starting game session - all ships are located");
        gameWindow.createGameBoards("Aldo", "GianPaolo89");

        for (int i =0; i < 4; i++) {
            gameWindow.turnP1();
            stopBackendUntil(gameWindow.endTurn);

            gameWindow.turnP2();
            stopBackendUntil(gameWindow.endTurn);
        }
    }
}