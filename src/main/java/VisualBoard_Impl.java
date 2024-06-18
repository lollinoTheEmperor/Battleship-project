import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class VisualBoard_Impl implements VisualBoard {

    final int HORIZONTAL = 0, VERTICAL = 1, ERROR = 3;
    final boolean HIT = true;

    protected int tableSize;
    private final Color placeShipColor, boardColor, islandColor, hitColor, waterColor;

    protected JFrame gameFrame;
    protected JPanel board1, board2;
    protected boolean[] endTurn, allShipPlaced;

    protected boolean shipStartPointSelected;                                                                           // to differentiate start point from end point
    protected int rememberXforPlace, rememberYforPlace;                                                                 // used to remember starting x/y when doing select-place of ships

    protected int singleBoardWidth, singleBoardHeight;

    protected Map<String, Component> componentMap;


    public enum MapElements {                                                                                           // created variables to have help when writing code
        GRID_PANEL_P1("GRID_PANEL_P1"),                                                                           // in order to, do not remember all string but having suggestions
        GRID_PANEL_P2("GRID_PANEL_P2"),
        TEXT_FIELD_P1("TEXT_FIELD_P1"),
        TEXT_FIELD_P2("TEXT_FIELD_P2"),
        BUTTON_P1("BUTTON_P1"),
        BUTTON_P2("BUTTON_P2"),
        REMAINING_SHIPS_LABEL_P1("REMAINING_SHIPS_LABEL_P1"),
        REMAINING_SHIPS_LABEL_P2("REMAINING_SHIPS_LABEL_P2"),
        FETCHING_GRID_PANEL_P1("FETCHING_GRID_PANEL_P1"),
        FETCHING_GRID_PANEL_P2("FETCHING_GRID_PANEL_P2");

        private final String value;

        MapElements(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


    public VisualBoard_Impl() {
        singleBoardWidth = 450;
        singleBoardHeight = 300;
        placeShipColor = Color.GRAY;
        islandColor = Color.BLACK;
        hitColor = Color.RED;
        waterColor = Color.GRAY;
        boardColor = Color.WHITE;
        tableSize = 10;

        gameFrame = new JFrame("Game Window");
        gameFrame.pack();
        gameFrame.setSize(singleBoardWidth * 2, singleBoardHeight * 2);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(new GridLayout(1, 2, 10, 10));
        gameFrame.setVisible(true);

        board1 = new JPanel();
        board2 = new JPanel();
        shipStartPointSelected = false;
        rememberXforPlace = 0;
        rememberYforPlace = 0;
        endTurn = new boolean[]{false};
        allShipPlaced = new boolean[]{false};

        componentMap = new HashMap<>();
        createComponents();
    }

    private void createComponents() {
        JPanel gridPanelP1 = new JPanel();
        JPanel gridPanelP2 = new JPanel();
        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JLabel remainingShipsLabel1 = new JLabel();
        JLabel remainingShipsLabel2 = new JLabel();
        JPanel fetchingGridPanelP1 = new JPanel();
        JPanel fetchingGridPanelP2 = new JPanel();

        componentMap.put(MapElements.GRID_PANEL_P1.getValue(), gridPanelP1);
        componentMap.put(MapElements.GRID_PANEL_P2.getValue(), gridPanelP2);
        componentMap.put(MapElements.TEXT_FIELD_P1.getValue(), textField1);
        componentMap.put(MapElements.TEXT_FIELD_P2.getValue(), textField2);
        componentMap.put(MapElements.BUTTON_P1.getValue(), button1);
        componentMap.put(MapElements.BUTTON_P2.getValue(), button2);
        componentMap.put(MapElements.REMAINING_SHIPS_LABEL_P1.getValue(), remainingShipsLabel1);
        componentMap.put(MapElements.REMAINING_SHIPS_LABEL_P2.getValue(), remainingShipsLabel2);
        componentMap.put(MapElements.FETCHING_GRID_PANEL_P1.getValue(), fetchingGridPanelP1);
        componentMap.put(MapElements.FETCHING_GRID_PANEL_P2.getValue(), fetchingGridPanelP2);
    }

    public void createGameBoards(Player_Impl p1, Player_Impl p2) {
        gameFrame.repaint();

        createVisualBoard(board1, true, p1);
        createVisualBoard(board2, false, p2);

        JTextField textField1 = (JTextField) componentMap.get(MapElements.TEXT_FIELD_P1.getValue());
        JTextField textField2 = (JTextField) componentMap.get(MapElements.TEXT_FIELD_P2.getValue());

        if (textField1 != null)
            textField1.setEditable(false);
        if (textField2 != null)
            textField2.setEditable(false);

        gameFrame.add(board1);
        gameFrame.add(board2);
    }

    @Override
    public void createVisualBoard(JPanel panel, boolean isP1, Player_Impl player) {

        JTextField textField = new JTextField();
        JButton button = new JButton("Attack!!");
        JLabel coordLabel = new JLabel("Coordinates:");
        coordLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        JLabel remainingShipsLabel = new JLabel("** x2 *** x1");
//TODO show effective remainig ship not **x2

        remainingShipsLabel.setOpaque(true);                                                                            // set the background for the game remaining ship label
        remainingShipsLabel.setBackground(Color.LIGHT_GRAY);

        componentMap.put((isP1 ? MapElements.GRID_PANEL_P1.getValue() : MapElements.GRID_PANEL_P2.getValue()), getjPanel(tableSize, textField, button));
        JPanel boardPanel = (JPanel) componentMap.get((isP1 ? MapElements.GRID_PANEL_P1.getValue() : MapElements.GRID_PANEL_P2.getValue()));

        button.setEnabled(false);                                                                                       // is set to disable but in method getjPanel, after a click will be re-enabled
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {                                                                // eventListener to perform attack
                System.out.print(player.name +"->");
                int[] saveXY = parseCord(textField.getText().toUpperCase());
                // FIXME
                boolean attackFeedback = player.attack(saveXY[0], saveXY[1]);
                // TODO change color based on the feedback
                paintFeedback(saveXY[0], saveXY[1], attackFeedback == HIT? hitColor : waterColor , boardPanel);
                endTurn[0] = true;
            }
        });

//////// BOARD SETTINGS ////////////////////
        panel.setLayout(new BorderLayout(10, 10)); // Add gap between components
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding around the panel
        panel.add(boardPanel, BorderLayout.CENTER);

        JPanel attackPanel = new JPanel();
        attackPanel.setLayout(new BoxLayout(attackPanel, BoxLayout.Y_AXIS));
        attackPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding for right panel

        // Align and resize components
        remainingShipsLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to left
        remainingShipsLabel.setVerticalAlignment(SwingConstants.TOP); // Align to top
        coordLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to left
        textField.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to left
        textField.setMaximumSize(new Dimension(100, 20)); // Set maximum size to align with the button
        button.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to left

        // Add components to attackPanel
        attackPanel.add(remainingShipsLabel);
        attackPanel.add(coordLabel);
        attackPanel.add(textField);
        attackPanel.add(button);

        // Add right panel and board panel to main panel
        panel.add(attackPanel, BorderLayout.SOUTH);  // Add remainingShip/attack panel to the bottom
        panel.add(boardPanel, BorderLayout.CENTER); // Add board panel to the center

        panel.setPreferredSize(new Dimension(singleBoardWidth, singleBoardHeight));
////////////////////////////////////////////

        // Store references for further use (using a map)
        if (isP1) {
            componentMap.put(MapElements.GRID_PANEL_P1.getValue(), boardPanel);
            componentMap.put(MapElements.TEXT_FIELD_P1.getValue(), textField);
            componentMap.put(MapElements.BUTTON_P1.getValue(), button);
            componentMap.put(MapElements.REMAINING_SHIPS_LABEL_P1.getValue(), remainingShipsLabel);
        } else {
            componentMap.put(MapElements.GRID_PANEL_P2.getValue(), boardPanel);
            componentMap.put(MapElements.TEXT_FIELD_P2.getValue(), textField);
            componentMap.put(MapElements.BUTTON_P2.getValue(), button);
            componentMap.put(MapElements.REMAINING_SHIPS_LABEL_P2.getValue(), remainingShipsLabel);
        }

        reloadGameView();
    }


    @Override
    public JPanel getjPanel(int size, JTextField textField, JButton button) {

        JPanel boardPanel = new JPanel(new GridLayout(size, size));
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));                                              // Border around the board
        // using only size because the table is a square
        for (int i = 0; i < size * size; i++) {                                                                         // creating a grid of buttons to manage input
            final int index = i;                                                                                        // using a eventListener
            JButton square = new JButton();
            square.setBackground(boardColor);
//            TODO needed font?
            Font font = new Font("Monospaced", Font.BOLD, 20);
            square.setFont(font);

            square.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int col = index / size;
                    int row = index % size;
                    String coord = row + ":" + col;
                    textField.setText(coord);
                    button.setEnabled(true);
                }
            });
            boardPanel.add(square);
        }
        return boardPanel;
    }

    @Override
    public int[] parseCord(String cord) {
        System.out.println(cord);
        String[] parseCoord = cord.split(":");

        return new int[]{Integer.parseInt(parseCoord[0]), Integer.parseInt(parseCoord[1])};
    }

//TODO placeShip (shiplayout, nNavi)
// placeShip piu volte per ogni barca se ritorna vero ok, se falso rifare il place ,
// return false/true se tutto aposto.

//    FIXME con errore sulla mia board non funziona correttamente


    @Override
    public void fetchingShips(BoardStart shipLayout, Map<Integer, Ship_Impl> ships, boolean isP1) {

        allShipPlaced[0] = false;                                                                                       // using an array of 1 element to use value by reference

        JPanel panel = new JPanel();
        panel.setVisible(true);
        gameFrame.add(panel);

        Map<Integer, Ship_Impl> shipCopy = new HashMap<>(ships);
        JTextField coordField = new JTextField();
        JButton button = new JButton("Select");
        JLabel coordLabel = new JLabel("Coordinates:");
        coordLabel.setFont(new Font("Arial", Font.PLAIN, 10));

        componentMap.put((isP1 ? MapElements.FETCHING_GRID_PANEL_P1.getValue() : MapElements.FETCHING_GRID_PANEL_P2.getValue()), getjPanel(tableSize, coordField, button));
        JPanel boardPanel = (JPanel) componentMap.get((isP1 ? MapElements.FETCHING_GRID_PANEL_P1.getValue() : MapElements.FETCHING_GRID_PANEL_P2.getValue()));

//        button.setEnabled(false);                                                                                       // is set to disable but in method getjPanel, after a click will be re-enabled
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int[] saveCoord = parseCord(coordField.getText().toUpperCase());
                Ship_Impl currentShip;
                boolean feedBackPlaceShip;

                if (shipStartPointSelected) {
                    int shipOrientation = validateCoordPlaceShip(rememberXforPlace, rememberYforPlace, saveCoord[0], saveCoord[1]);
                    if (shipOrientation == ERROR) {
                        coordField.setText("Invalid points!");
                        System.out.println("Invalid points!");
                        return;
                    }

// TODO fare label per n barche qui e dallalrta

                    // TODO call method with saved x y and other form save cord
                    System.out.println("Ship from " + rememberXforPlace + ":" + rememberYforPlace + " to " + saveCoord[0] + ":" + saveCoord[1]);

                    int shipId = shipCopy.keySet().iterator().next();
                    currentShip = shipCopy.get(shipId);

                    int startX = Math.min(rememberXforPlace, saveCoord[0]);
                    int startY = Math.min(rememberYforPlace, saveCoord[1]);
                    int endX = Math.max(rememberXforPlace, saveCoord[0]);
                    int endY = Math.max(rememberYforPlace, saveCoord[1]);
                    boolean validLenght;

                    if (shipOrientation == HORIZONTAL)
                        validLenght = validateLenghtShip(startX, endX, currentShip.getSize());
                    else
                        validLenght = validateLenghtShip(startY, endY, currentShip.getSize());

                    if (!validLenght) {
                        coordField.setText("Ship size: " + currentShip.getSize());
                        System.out.println("Ship size: " + currentShip.getSize());
                        return;
                    }

                    shipStartPointSelected = false;
                    button.setText("Select cell");
                    System.out.println("Ship from " + rememberXforPlace + ":" + rememberYforPlace + " to " + saveCoord[0] + ":" + saveCoord[1]);

                    feedBackPlaceShip = shipLayout.placeShip(startX, startY, shipOrientation, currentShip);


                    if (feedBackPlaceShip) {
                        paintFeedback(rememberXforPlace, rememberYforPlace, saveCoord[0], saveCoord[1], placeShipColor, boardPanel);
                        shipCopy.remove(shipId);
                    } else {
                        coordField.setText("Ship do not fit!");
                        paintFeedback(rememberXforPlace, rememberYforPlace, boardColor, boardPanel);                         // reset previous square painted
                    }

                } else {
                    shipStartPointSelected = true;
                    rememberXforPlace = saveCoord[0];
                    rememberYforPlace = saveCoord[1];

                    paintFeedback(rememberXforPlace, rememberYforPlace, placeShipColor, boardPanel);

                    button.setText("Select ship end point");
                }


                if (shipCopy.isEmpty()) {
                    gameFrame.remove(panel);
                    allShipPlaced[0] = true;
                    System.out.println("------ Placed all ships ------");
                }
            }
        });


//////// BOARD SETTINGS ////////////////////
        panel.setLayout(new BorderLayout(10, 10)); // Add gap between components
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding around the panel
        panel.add(boardPanel, BorderLayout.CENTER);

        JLabel turnLabel = new JLabel(shipLayout.title);

        JPanel attackPanel = new JPanel();
        attackPanel.setLayout(new BoxLayout(attackPanel, BoxLayout.Y_AXIS));
        attackPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding for right panel

        // Align and resize components
        coordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        coordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        coordField.setMaximumSize(new Dimension(100, 20)); // Set maximum size to align with the button
        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        attackPanel.add(coordLabel);
        attackPanel.add(coordField);
        attackPanel.add(button);

        panel.add(attackPanel, BorderLayout.SOUTH);  // Add remainingShip/attack panel to the bottom
        panel.add(boardPanel, BorderLayout.CENTER); // Add board panel to the center
        panel.add(turnLabel, BorderLayout.NORTH);

        panel.setPreferredSize(new Dimension(singleBoardWidth, singleBoardHeight));
////////////////////////////////////////////

        gameFrame.add(panel);
        reloadGameView();
    }

    public boolean validateLenghtShip(int startPoint, int endPoint, int shipSize) {
        System.out.println(startPoint + " " + endPoint + " " + shipSize);
        return shipSize == endPoint - startPoint+1;                                  // using 0 we have math problem (in method) so do +1
    }

    @Override
    public int validateCoordPlaceShip(int startX, int startY, int endX, int endY) {

        //TODO check if need ship of size 1
        if(startX != endX || startY != endY) {
            if (startY == endY)
                return HORIZONTAL;

            if (startX == endX)
                return VERTICAL;
        }
        return ERROR;
    }

    @Override
    public void paintFeedback(int startX, int startY, Color feedbackColor, JPanel targetPanel) {
        paintFeedback(startX, startY, startX, startY, feedbackColor, targetPanel);
    }

    @Override
    public void paintFeedback(int startX, int startY, int endX, int endY, Color feedbackColor, JPanel targetPanel) {

        int minX = Math.min(startX, endX);
        int maxX = Math.max(startX, endX);
        int minY = Math.min(startY, endY);
        int maxY = Math.max(startY, endY);

        if (minX == maxX && minY == maxY) {         // this is used for attack
            paintSquare(feedbackColor, targetPanel, (minY) * tableSize + (minX));
        } else {
            if (minX == maxX) { // Horizontal ship for placing ships
                for (int y = minY; y <= maxY; y++) {
                    int index = (y) * tableSize + (minX);
                    paintSquare(feedbackColor, targetPanel, index);
                }
            } else if (minY == maxY) { // Vertical Ship for placing ships
                for (int x = minX; x <= maxX; x++) {
                    int index = (minY) * tableSize + (x);
                    paintSquare(feedbackColor, targetPanel, index);
                }
            }
        }
        reloadGameView();
    }

    protected void paintSquare(Color feedbackColor, JPanel targetPanel, int index) {
        if (index >= 0 && index < targetPanel.getComponentCount()) {
            JButton square = (JButton) targetPanel.getComponent(index);
            square.setBackground(feedbackColor);
            if (boardColor == feedbackColor)
                square.setEnabled(true);
            else
                square.setEnabled(false);
        }
    }

    @Override
    public void paintIsland(int x, int y, String targetPanel) {
        JPanel panel = (JPanel) componentMap.get(targetPanel);
        paintFeedback(x, y, islandColor, panel);
    }

    @Override
    public void turnP1() {
        endTurn[0] = false;
        this.board2.setVisible(false);
        this.board1.setVisible(true);
    }

    @Override
    public void turnP2() {
        endTurn[0] = false;
        this.board1.setVisible(false);
        this.board2.setVisible(true);
    }

    @Override
    public void reloadGameView() {                                                                                      // Used to solve a visual bug (show the board without all items)
        gameFrame.revalidate();
        gameFrame.repaint();
    }

   
}
