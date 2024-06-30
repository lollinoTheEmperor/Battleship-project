import java.util.List;

public interface PlayerStatus {

    //checks if the name of the player is already written in Status.txt and if not it writes it
    void selectPlayer(String playerName);

    //given the winning player increment it's winning index
    void incrementWinnerIndex(String name);

    //return an ordered list from the one with more wins to the one with less wins
    List<String> ranking();
}