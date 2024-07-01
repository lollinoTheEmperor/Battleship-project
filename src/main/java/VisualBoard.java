import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Set;

interface VisualBoard {

    /**
     * used as a constructor to initialize swing object to put in a map
     */
    void createComponents();

    /**
     * Call createVisualBoard to create two board with grids, buttons, and all other component + an event listener (for each board)
     * @param p1 (Player 1)
     * @param p2 (Player 2)
     */
    void createGameBoards(Player_Impl p1, Player_Impl p2);

    /**
     * Create a board with grids, buttons, and all other component + an event listener (for each board)
     * @param panel (the target panel where it will be referenced after the creation
     * @param isP1 (boolean to differs player1 from player2)
     */
    void createVisualBoard(JPanel panel, boolean isP1);

    /**
     * Method to create the grid of buttons
     * @param size
     * @param textField
     * @param button
     * @return the object to reference it to a variable
     */
    JPanel getjPanel(int size, JTextField textField, JButton button);

    int[] parseCord(String coord);

    void fetchingShips(BoardStart shipLayout, Map<Integer, Ship_Impl> ships, boolean isP1);

    boolean validateLengthShip(int startPoint, int endPoint, int shipSize);

    /**
     * Returns an int to decide if the coordinates are valid or not + horizontal or vertical
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return 0 if horizontal alignment, 1 for vertical, 3 for not valid input
     */
    int validateCoordPlaceShip(int startX, int startY, int endX, int endY);

    /**
     * Method to paint a targrt button of the grid
     * @param feedbackColor
     * @param targetPanel
     * @param index (rappredent the index of the button) to search in the target panel
     */
    void paintSquare(Color feedbackColor, JPanel targetPanel, int index);

    /**
     * Paint an island, tha will block placement of ships and attack
     * @param x
     * @param y
     * @param isP1
     */
    void paintIsland(int x, int y, boolean isP1);

    /**
     * Read the feedbackBoard of the current player and update the visualBoard with colors
     * @param isP1
     */
    void repaintMap(boolean isP1);

    /**
     * setup boards for turn P1
     */
    void turnP1();

    /**
     * setup boards for turn P2
     */
    void turnP2();

    /**
     * Write in the label of the current player, the total of ships and types remaining
     * @param isP1
     */
    void updateRemainingShipsLabel(boolean isP1);

    /**
     * Method to paint a square (override the second method bellow, to permit also single cells)
     * @param startX
     * @param startY
     * @param feedbackColor
     * @param targetPanel
     */
    void paintFeedback(int startX, int startY, Color feedbackColor, JPanel targetPanel);

    /**
     * Method to paint a square or a line of buttons
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @param feedbackColor
     * @param targetPanel
     */
    void paintFeedback(int startX, int startY, int endX, int endY, Color feedbackColor, JPanel targetPanel);

    /**
     * Setup for the bot turn (disables attack button, ...)
     */
    void turnBot();

    /**
     * Setup for the player turn after the bot (re-enable the button, ...)
     */
    void endTurnBot();

    /**
     * reloads component and repaint them
     */
    void reloadGameView();

    /**
     * Setup to show a player boar and a bot board
     */
    void showBoardPvE();

    /**
     * Setup to show both bot boards and disable interactions
     */
    void showBoardsForBot();

    /**
     * Show a dialog for the winner
     */
    void win();
}

