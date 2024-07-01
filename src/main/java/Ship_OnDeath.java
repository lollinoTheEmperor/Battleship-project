import java.util.*;
import java.util.Random;

//this ship will fire 3 random shots when destroyed
public class Ship_OnDeath extends Ship_Impl{

    BoardStart opponentsBoard;
    public Ship_OnDeath(int size, String type, String id, BoardStart opponentsBoard) {
        super(size, type, id);
        this.opponentsBoard = opponentsBoard;
    }

    public void onDeath(BoardFeedback myFeedbacks, BoardStart opponentsBoard, ShipManager shipManager){
        for (int i=0;i<3;i++){
            Random random = new Random();
            int x = random.nextInt(opponentsBoard.getWidth());
            int y = random.nextInt(opponentsBoard.getHeight());
            attack(myFeedbacks,opponentsBoard,x,y,shipManager);
        }
    }
}
