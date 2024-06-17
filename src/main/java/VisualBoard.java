import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Set;

interface VisualBoard {

    //    create the visual board thanks to the size
    void createVisualBoard(JPanel panel, boolean isP1, String playerName);

    JPanel getjPanel(int size, JTextField textField, JButton button);

    int[] parseCord(String coord);

    void fetchingShips(BoardStart shipLayout, Map<Integer, Ship_Impl> ships, boolean isP1);

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

    void paintFeedback(int startX, int startY, Color feedbackColor, JPanel targetPanel);

    void paintFeedback(int startX, int startY, int endX, int endY, Color feedbackColor, JPanel targetPanel);

    void paintIsland(int x, int y, String targetPanel);

    void reloadGameView();
}

