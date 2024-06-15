import javax.swing.*;
import java.util.Set;

interface VisualBoard {

    //    create the visual board thanks to the size
    void createVisualBoard(JPanel panel, boolean isP1, int size, String playerName);

    JPanel getjPanel(int size, JTextField textField, JButton button);

    //    is used to get the coordinates of the cliceck cell
    void getTableCord(int x, int y);

    void sendAttackCord(int x, int y);

    int[] parseCord(String coord);

    void fetchingShips(BoardStart shipLayout, Set<Ship_Impl> ships);

    /**
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return 0 if horizontal alignment, 1 for vertical, 3 for not valid input
     */
    int validateCoordPlaceShip(int startX, int startY, int endX, int endY);

    //        TODO disabilitare le caselle
    void turnP1();

    void turnP2();
}

