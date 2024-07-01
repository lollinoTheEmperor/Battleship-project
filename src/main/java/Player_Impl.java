import java.util.*;

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

    public String getName() {return name;}

    public void setOpponentsBoard(BoardStart board) {
        this.opponentsBoard = board;
    }

//    @Override
//    public boolean attack(int x, int y) {
//        return myFeedbacks.addFeedBack(opponentsBoard,x,y, shipManager);
//    }

    @Override
    public Set<ShotsFeedback> attack(int x, int y) {
        Random rand = new Random();

        Ship_Impl ship = shipManager.getShipById(Integer.toString(rand.nextInt((shipManager.getShips().keySet().size()))+1));
        return ship.attack(myFeedbacks, opponentsBoard, x, y, opponentsShipManager);
    }


    @Override
    public boolean hasShips() {
        return !shipManager.ships.isEmpty();
    }
}
