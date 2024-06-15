import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;



public class VisualBoard_ImplTest {

    @Test
    public void main() {

        //// This will be an input for the method so it would be deleted ///////////////
        Ship_Impl shipS = new Ship_Impl(2, "light", "1");   // int size, String type, String id
        Ship_Impl shipM = new Ship_Impl(2, "medium", "2");   // int size, String type, String id
        Ship_Impl shipL = new Ship_Impl(2, "heavy", "3");   // int size, String type, String id

        Set<Ship_Impl> ships = new HashSet<Ship_Impl>();
        ships.add(shipS);
        ships.add(shipM);
        ships.add(shipL);

        BoardStart boardStartP1 = new BoardStart("Layout P1");
        BoardStart boardStartP2 = new BoardStart("Layout P2");
//        ////////////////////////////////////////////////////////////////////



        System.out.printf("Hello and welcome!\n");

        // TODO use name
        VisualBoard_Impl gameWindow = new VisualBoard_Impl();
//        VisualBoard_Impl gameWindow = new VisualBoard_Impl("Aldo", "GianPaolo89");


        boolean placedShips = false;

        gameWindow.getShipsPositions(boardStartP1, ships);


        while (!gameWindow.allShipPlaced) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

//        System.out.println("P1");
//
//        for (String[] pos : boardStartP1.board_Game) {
//            System.out.println(pos[0] + " " + pos[1]);
//        }

        gameWindow.getShipsPositions(boardStartP2, ships);


        while (!gameWindow.allShipPlaced) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


//        VisualBoard_Impl gameWindow = new VisualBoard_Impl("Aldo", "GianPaolo89");
//gameWindow.createGameBaords("Aldo", "GianPaolo89");
//        BoardStart shipLayoutP1 = new BoardStart("1");
//        BoardStart shipLayoutP2 = new BoardStart("2");

//        NAVI = set di navi inteso come n navi da 3, ... , usare per pizaazrle.
//        gameWindow.fetchingShips(shipLayoutP1, NAVI)



//
//        for (int i =0; i < 4; i++) {
//            gameWindow.showBoard1();
//            gameWindow.hideBoard2();
//
//            while (!gameWindow.endTurn) {
//                try {
//                    Thread.sleep(250);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            gameWindow.endTurn = false;
//
//            gameWindow.hideBoard1();
//            gameWindow.showBoard2();
//
//            while (!gameWindow.endTurn) {
//                try {
//                    Thread.sleep(250);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            gameWindow.endTurn = false;
//        }
    }
}