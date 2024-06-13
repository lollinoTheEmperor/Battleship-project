import java.util.List;

public interface PlayerStatus {
    //When called asks the user for his name and checks if it is already present in the record of names.
    //If it is display an error otherwise write the name in the record and assign it to the player
    //The name must be within 3 and 20 characters.
    String createNewPlayerName(String name);

    //Select a player from the saved names and assign it to the player name.
    String selectPlayer(String name);

    //given the winning player increment it's winning index
    void incrementWinnerIndex(String name);

    //return an ordered list from the one with more wins to the one with less wins
    List<String> ranking();
}