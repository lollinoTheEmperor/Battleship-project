import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class VisualBoard_Impl implements VisualBoard {

    final int HORIZONTAL = 0, VERTICAL = 1, NOTVALID = 3;


    protected JFrame megaFrame;

    protected JPanel board1;
    protected JPanel board2;
    protected JTextField textField1;
    protected JTextField textField2;
    protected JButton button1;
    protected JButton button2;

    protected JPanel placingShipsPanelP1;
    protected JLabel remainingShipsLabel1;
    protected JLabel remainingShipsLabel2;
    protected boolean selectedShipStart;           // to differentiate start point from end point
    protected boolean allShipPlaced;
    protected int rememberXforPlace;
    protected int rememberYforPlace;

    protected boolean endTurn;
    protected int panelWidth;
    protected int panelHeight;


    public VisualBoard_Impl() {
        megaFrame = new JFrame("Game Window");
        panelWidth = 450;
        panelHeight = 300;

        board1 = new JPanel();
        board2 = new JPanel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        remainingShipsLabel1 = new JLabel();
        remainingShipsLabel2 = new JLabel();

        megaFrame.setSize(panelWidth * 2, panelHeight * 2);
        megaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        megaFrame.setLayout(new GridLayout(1, 2, 10, 10)); // allows to size better the elements
        megaFrame.setVisible(true);

        selectedShipStart = false;
        rememberXforPlace = 0;
        rememberYforPlace = 0;
        allShipPlaced = false;
    }

    public void createGameBaords (String nameP1, String nameP2) {

//        this();     // call to the default constructor

        createVisualBoard(board1, true, 10, nameP1);
        createVisualBoard(board2, false, 10, nameP2);

        textField1.setEditable(false);  // do not allow to change coordinates
        textField2.setEditable(false);
        megaFrame.add(board1);
        megaFrame.add(board2);
    }


    private boolean shotHit(int row, int col) {
        // TODO: use to change things on board
        int myRow = 2, myCol = 3;

        return (row == myRow && col == myCol);
    }

    @Override
    public void createVisualBoard(JPanel panel, boolean isP1, int size, String name) {

        JTextField textField = new JTextField();
        JButton button = new JButton("Attack!!");
        JLabel coordLabel = new JLabel("Coordinates:");
        coordLabel.setFont(new Font("Arial", Font.PLAIN, 10)); // Smaller font for the label
        JLabel remainingShipsLabel = new JLabel("** x2 *** x1");

        // Configura lo sfondo per la label
        remainingShipsLabel.setOpaque(true); // Rendi opaco lo sfondo
        remainingShipsLabel.setBackground(Color.LIGHT_GRAY); // Colore di sfondo

        JPanel boardPanel = getjPanel(size, textField, button);

        // control the button click and it is called
        button.setEnabled(false);   // is set to disable but in method getjPanel, after a click will be re-enabled
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.print(name +"->");
                parseCord(textField.getText().toUpperCase());
            }
        });

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

        panel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        // Store references for further use
//        TODO change use boolean
        if (isP1) {
            textField1 = textField;
            button1 = button;
            remainingShipsLabel1 = remainingShipsLabel;
        } else {
            textField2 = textField;
            button2 = button;
            remainingShipsLabel2 = remainingShipsLabel;
        }
    }


    private static JPanel getjPanel(int size, JTextField textField, JButton button) {
        int boardRows = size, boardCols = size;

        JPanel boardPanel = new JPanel(new GridLayout(boardRows, boardCols));
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border around the board

        for (int i = 0; i < boardCols * boardRows; i++) {
            final int index = i;
            JButton square = new JButton();
            square.setBackground(Color.WHITE);
            Font font = new Font("Monospaced", Font.BOLD, 20); // Slightly smaller font
            square.setFont(font);

            square.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int row = index / boardRows + 1;
                    int col = index % boardCols + 1;
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
    public void getTableCord(int x, int y) {

    }

    @Override
    public void sendAttackCord(int x, int y) {

    }

    @Override
    public int[] parseCord(String cord) {
        System.out.println(cord);
        String[] parseCoord = cord.split(":");
//        int myRow = Integer.parseInt(parseCoord[0]);
//        int myCol = Integer.parseInt(parseCoord[1]);
        this.endTurn = true;

        return new int[]{Integer.parseInt(parseCoord[0]), Integer.parseInt(parseCoord[1])};
    }

    //        TODO disabilitare le caselle
    @Override
    public void showBoard1() {
        this.board1.setVisible(true);
    }

    @Override
    public void showBoard2() {
        this.board2.setVisible(true);
    }

    @Override
    public void hideBoard1() {
        this.board1.setVisible(false);
    }

    @Override
    public void hideBoard2() {
        this.board2.setVisible(false);
    }


//TODO placeShip (shiplayout, nNavi)
// placeShip piu volte per ogni barca se ritorna vero ok, se falso rifare il place ,
// return false/true se tutto aposto.

//    TODO passare tutte le navi a placeShip



    protected void getShipsPositions(BoardStart shipLayout, Set<Ship_Impl> ships) {

        allShipPlaced = false;

        JPanel panel = new JPanel();
        panel.setVisible(true);
        megaFrame.add(panel);

        Set<Ship_Impl> shipCopy = new HashSet<>(ships);

        //        TODO chiamare -> placeShip(int x, int y, int orientation, Ship_Impl ship)

        JTextField coordField = new JTextField();

        JButton button = new JButton("Select");
        JLabel coordLabel = new JLabel("Coordinates:");
        coordLabel.setFont(new Font("Arial", Font.PLAIN, 10)); // Smaller font for the label


        JPanel boardPanel = getjPanel(10, coordField, button);

        // control the button click and it is called
        button.setEnabled(false);   // is set to disable but in method getjPanel, after a click will be re-enabled
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int[] saveCoord = parseCord(coordField.getText().toUpperCase());
                Ship_Impl currentShip = null;
                boolean feedBackPlaceShip = false;


                if (selectedShipStart) {
                    selectedShipStart = false;

                    button.setText("Select");

                    int shipOrientation = validateCoordPlaceShip(rememberXforPlace, rememberYforPlace, saveCoord[0], saveCoord[1]);
                    if (shipOrientation == NOTVALID) {
                        coordField.setText("Invalid points!");
                        System.out.println("Invalid points!");
                        return;
                    }

                    // TODO call method with saved x y and other form save cord
                    System.out.println("Ship from " + rememberXforPlace + ":" + rememberYforPlace + " to " + saveCoord[0] + ":" + saveCoord[1]);

                    currentShip = shipCopy.stream().findAny().get();
                    feedBackPlaceShip = shipLayout.placeShip( Math.min(rememberXforPlace, saveCoord[0]), Math.min(rememberYforPlace, saveCoord[1]), shipOrientation, currentShip);

                    if (feedBackPlaceShip) {
                        shipCopy.remove(currentShip);
                    }

                } else {
                    selectedShipStart = true;
                    rememberXforPlace = saveCoord[0];
                    rememberYforPlace = saveCoord[1];

                    button.setText("Place!");
                }


                if (shipCopy.isEmpty()) {
                    System.out.println("------ Placed all ships ------");
                    allShipPlaced = true;
                    megaFrame.remove(panel);
                }
            }
        });

        panel.setLayout(new BorderLayout(10, 10)); // Add gap between components
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding around the panel
        panel.add(boardPanel, BorderLayout.CENTER);


        JLabel turnLabel = new JLabel(shipLayout.title);


        JPanel attackPanel = new JPanel();
        attackPanel.setLayout(new BoxLayout(attackPanel, BoxLayout.Y_AXIS));
        attackPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding for right panel

        // Align and resize components
        coordLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to left
        coordField.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to left
        coordField.setMaximumSize(new Dimension(100, 20)); // Set maximum size to align with the button
        button.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to left

        attackPanel.add(coordLabel);
        attackPanel.add(coordField);
        attackPanel.add(button);

        // Add right panel and board panel to main panel
        panel.add(attackPanel, BorderLayout.SOUTH);  // Add remainingShip/attack panel to the bottom
        panel.add(boardPanel, BorderLayout.CENTER); // Add board panel to the center
        panel.add(turnLabel, BorderLayout.NORTH);

        panel.setPreferredSize(new Dimension(panelWidth, panelHeight));
    }

    @Override
    public int validateCoordPlaceShip(int startX, int startY, int endX, int endY) {

        //TODO check if need ship of size 1
        if(startX == endX && startY == endY)
            return NOTVALID;

        if(startY == endY)
            return VERTICAL;

        if(startX == endX)
            return HORIZONTAL;

        return NOTVALID;
    }
}
