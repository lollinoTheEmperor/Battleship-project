public class Player_Impl implements Player {
    String name;
    BoardStart myBoard;
    BoardStart opponentsBoard;
    BoardFeedback myFeedbacks;
    ShipManager shipManager;

    Player_Impl(String name, BoardStart board, BoardFeedback feedbacks, ShipManager ships) {
        this.name = name;
        this.myBoard = board;
        this.myFeedbacks = feedbacks;
        this.shipManager = ships;
    }

    public BoardStart getMyBoard() {
        return myBoard;
    }

    public void setOpponentsBoard(BoardStart board) {
        this.opponentsBoard = board;
    }

    @Override
    public boolean attack(int x, int y) {
        return myFeedbacks.addFeedBack(opponentsBoard,x,y, shipManager);
    }

    @Override
    public boolean hasShips() {
        return shipManager.ships.isEmpty();
    }
}
