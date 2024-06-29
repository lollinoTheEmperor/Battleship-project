import java.util.*;

//this ship will fire 3 random shots when destroyed
public class Ship_OnDeath extends Ship_Impl{

    BoardStart opponentsBoard;
    public Ship_OnDeath(int size, String type, String id, BoardStart opponentsBoard) {
        super(size, type, id);
        this.opponentsBoard = opponentsBoard;
    }

    public void onDeath(BoardFeedback myFeedbacks, BoardStart opponentsBoard, ShipManager shipManager){
        for (int i=0;i<3;i++){
            int x = opponentsBoard.getWidth();
            int y = opponentsBoard.getHeight();
            attack(myFeedbacks,opponentsBoard,x,y,shipManager);
        }
    }
}
