import java.util.Map;
import java.util.Set;

public class Player_Impl implements Player {
    String name;
    BoardStart myBoard;
    BoardStart opponentsBoard;
    BoardFeedback myFeedbacks;
    ShipManager shipManager;
    ShipManager opponentsShipManager;

    Player_Impl(String name, BoardStart board, BoardFeedback feedbacks, ShipManager ships, ShipManager opponentsShipManager) {
        this.name = name;
        this.myBoard = board;
        this.myFeedbacks = feedbacks;
        this.shipManager = ships;
        this.opponentsShipManager = opponentsShipManager;
    }

    public BoardStart getMyBoard() {
        return myBoard;
    }

    // FIXME possibilità di integrarlo con il costruttore
    public void setOpponentsBoard(BoardStart board) {
        this.opponentsBoard = board;
    }

//    @Override
//    public boolean attack(int x, int y) {
//        return myFeedbacks.addFeedBack(opponentsBoard,x,y, shipManager);
//    }

    @Override
    public Set<ShotsFeedback> attack(int x, int y) {
        // FIXME do bether decision for which ship to use

        // TODO @lollino, qualcosa non va coi colori non capisco se sia hit oppure no, controllare secondo attacco ship
        //  colorare secondo attaco ship.
        Ship_Impl ship = shipManager.getShipById(shipManager.ships.entrySet().stream().findFirst().get().getKey().toString());
        // FIXME QUA USAVAMO LE BARCHE DEL GIOCATORE PER ATTACCARE SE SETESSO
        return ship.attack(myFeedbacks, opponentsBoard, x, y, opponentsShipManager);
    }


    @Override
    public boolean hasShips() {
        return shipManager.ships.isEmpty();
    }
}
