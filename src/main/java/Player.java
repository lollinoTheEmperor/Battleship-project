public interface Player {

    //takes in input the coordinates and calls addFeedback
    //if it returns true the player must repeat the turn
    boolean attack(int x, int y);

    //checks if the player still has ships
    boolean hasShips();
}
