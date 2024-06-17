import java.util.ArrayList;
import java.util.List;

public class Player_Impl implements Player {
    String name;
    BoardStart myBoard;
    BoardStart opponentsBoard;
    BoardFeedback myFeedbacks;
    List<Ship_Impl> myShips;

    Player_Impl(String name, BoardStart board, BoardFeedback feedbacks, List<Ship_Impl> ships) {
        this.name = name;
        this.myBoard = board;
        this.myFeedbacks = feedbacks;
        this.myShips=new ArrayList<>(ships);
    }

    public BoardStart getMyBoard() {
        return myBoard;
    }

    public void setOpponentsBoard(BoardStart board) {
        this.opponentsBoard = board;
    }

    @Override
    public boolean attack(int x, int y) {
        return myFeedbacks.addFeedBack(opponentsBoard,x,y);
    }

    @Override
    public boolean hasShips() {
        return myShips.isEmpty();
    }
}
