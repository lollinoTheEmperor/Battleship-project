import javax.swing.*;

interface VisualBoard {

    //    create the visual board thanks to the size
    void createVisualBoard(JPanel panel, boolean isP1, int size, String playerName);

    //    is used to get the coordinates of the cliceck cell
    void getTableCord(int x, int y);

    void sendAttackCord(int x, int y);

    int[] parseCord(String coord);

    void showBoard1();

    void showBoard2();

    void hideBoard1();

    void hideBoard2();

    /**
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return 0 if horizontal alignment, 1 for vertical, 3 for not valid input
     */
    int validateCoordPlaceShip(int startX, int startY, int endX, int endY);
}

