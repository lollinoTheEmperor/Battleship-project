import java.util.Map;
import java.util.Set;

public interface Player {

    //takes in input the coordinates and calls addFeedback
    //if it returns true the player must repeat the turn
    Set<ShotsFeedback> attack(int x, int y);

    //checks if the player still has ships
    boolean hasShips();
}
