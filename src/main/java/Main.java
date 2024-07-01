import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static final int MINIMUM_SHIP_SIZE = 2, MAXIMUM_BOARD_SIZE = 100, MINIMUM_BOARD_SIZE = 3;
    private static int MAXIMUM_SHIP_SIZE = 6;
    public static BoardStart board1;
    public static BoardFeedback feedb1;
    public static BoardStart board2;
    public static BoardFeedback feedb2;
    private final int waitTime = (int) (0.5 * 1000);


    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        Main main = new Main();
        main.choseMod();
    }
    // public static Player_Impl player;
    
    // public static void main(String[] args) throws InterruptedException {
    //     System.out.printf("Hello and welcome!\n");
    //     referee();
    //     System.out.println("GG");
    // }

    public void choseMod() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel modeLabel = new JLabel("Select game mode");
        JRadioButton PvP = new JRadioButton("PvP");
        JRadioButton PvE = new JRadioButton("PvE");
        JRadioButton Bot = new JRadioButton("Bot vs Bot");
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(PvP);
        typeGroup.add(PvE);
        typeGroup.add(Bot);

        PvP.setSelected(true);

        JPanel typePanel = new JPanel();
        typePanel.add(modeLabel);
        typePanel.add(PvP);
        typePanel.add(PvE);
        typePanel.add(Bot);
        panel.add(typePanel);

        int result = JOptionPane.showConfirmDialog(null, panel, "Ship Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        if (PvP.isSelected())
            referee();
        else if (PvE.isSelected())
            playPvE();
        else if (Bot.isSelected())
            playBotVsBot();
    }


    public void playPvE() {
        System.out.println("Playing PvE mode...");

        PlayerStatus_Impl writer = new PlayerStatus_Impl();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JTextField nameFieldp1 = new JTextField(10);
        JTextField nameFieldp2 = new JTextField(10);
        panel.add(new JLabel("Enter Player 1 name:"));
        panel.add(nameFieldp1);
        int result = JOptionPane.showConfirmDialog(null, panel, "Player Names:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }
        String namep1 = nameFieldp1.getText();
        writer.selectPlayer(namep1);
        JOptionPane.showMessageDialog(null, "Player 1: " + namep1);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        JTextField sizeGB = new JTextField(10);
        JTextField shipC = new JTextField(10);
        panel2.add(new JLabel("Enter the size of the Gameboard (it will be a square, between " + MINIMUM_BOARD_SIZE + " and " + MAXIMUM_BOARD_SIZE + ")"));
        panel2.add(sizeGB);
        panel2.add(Box.createVerticalStrut(15)); // A spacer
        panel2.add(new JLabel("How many ships will you play with:"));
        panel2.add(shipC);

        int boardSize = -1;
        int shipCount = -1;
        while (true) {
            int result2 = JOptionPane.showConfirmDialog(null, panel2, "Gameboard Size and Total Ships:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result2 != JOptionPane.OK_OPTION) {
                return;
            }
            String sizeStr = sizeGB.getText();
            String shipStr = shipC.getText();
            try {
                boardSize = Integer.parseInt(sizeStr);
                shipCount = Integer.parseInt(shipStr);
                if (boardSize < MINIMUM_BOARD_SIZE || boardSize > MAXIMUM_BOARD_SIZE) {
                    JOptionPane.showMessageDialog(null, "Board size must be between 3 and 100.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid board size or number of ships.");
            }
        }
        JOptionPane.showMessageDialog(null, "Board Size: " + boardSize + "x" + boardSize + ", Ships: " + shipCount);


        int maxOccupiedCells = boardSize * boardSize / 2;
        int totalOccupiedCells = 0;

        ShipManager shipsP1 = new ShipManager();
        ShipManager shipsP2 = new ShipManager();

        for (int i = 0; i < shipCount; i++) {
            boolean retrySelection = true;
            while (retrySelection) {
                JPanel panel3 = new JPanel();
                panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

                JLabel sizeLabel = new JLabel("Select size of Ship Nr. " + (i + 1) + " (between " + MINIMUM_SHIP_SIZE + " and " + boardSize + "):");
                JTextField sizeInput = new JTextField(5);
                panel3.add(sizeLabel);
                panel3.add(sizeInput);

                JLabel typeLabel = new JLabel("Select type of Ship Nr. " + (i + 1) + ":");
                JRadioButton typeBattleship = new JRadioButton("Battleship");
                JRadioButton typeDestroyer = new JRadioButton("Destroyer");
                JRadioButton typeExplosive = new JRadioButton("Explosive");
                JRadioButton typeRadar = new JRadioButton("Radar");
                JRadioButton typeHeal = new JRadioButton("Heal");
                JRadioButton typeClassic = new JRadioButton("Classic");
                ButtonGroup typeGroup = new ButtonGroup();
                typeGroup.add(typeBattleship);
                typeGroup.add(typeDestroyer);
                typeGroup.add(typeExplosive);
                typeGroup.add(typeRadar);
                typeGroup.add(typeHeal);
                typeGroup.add(typeClassic);
                typeBattleship.setSelected(true);

                JPanel typePanel = new JPanel();
                typePanel.add(typeLabel);
                typePanel.add(typeBattleship);
                typePanel.add(typeDestroyer);
                typePanel.add(typeExplosive);
                typePanel.add(typeRadar);
                typePanel.add(typeHeal);
                typePanel.add(typeClassic);
                panel3.add(typePanel);

                int result3 = JOptionPane.showConfirmDialog(null, panel3, "Ship Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result3 != JOptionPane.OK_OPTION) {
                    return;
                }

                int size = -1;
                try {
                    size = Integer.parseInt(sizeInput.getText());
                    if (size < MINIMUM_SHIP_SIZE || size > boardSize) {
                        JOptionPane.showMessageDialog(null, "Ship size must be between " + MINIMUM_SHIP_SIZE + " and " + boardSize + ".");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid ship size.");
                    continue;
                }

                String type = null;
                if (typeBattleship.isSelected()) type = "Battleship";
                else if (typeDestroyer.isSelected()) type = "Destroyer";
                else if (typeExplosive.isSelected()) type = "Explosive";
                else if (typeRadar.isSelected()) type = "Radar";
                else if (typeHeal.isSelected()) type = "Heal";
                else if(typeClassic.isSelected()) type = "Classic";

                board1 = new BoardStart(namep1, boardSize, boardSize);
                feedb1 = new BoardFeedback(boardSize, boardSize);

                board2 = new BoardStart("bot1", boardSize, boardSize);
                feedb2 = new BoardFeedback(boardSize, boardSize);


                if (totalOccupiedCells + size > maxOccupiedCells) {
                    JOptionPane.showMessageDialog(null, "Total occupied cells cannot exceed 50% of the board size.");
                    retrySelection = true; // Retry for the same ship
                    i = 0; // Restart from the first ship
                    totalOccupiedCells = 0; // Reset total occupied cells
                } else {
                    retrySelection = false; // Valid ship selected, exit the retry loop
                    String id = String.valueOf(i + 1);



                    // TODO change class based on tipe
                    shipsP1.addShip(new Ship_Impl(size, type, id));
                    shipsP2.addShip(new Ship_Impl(size, type, id));

                    if(type.equals("Radar")) {
                        shipsP1.addShip(new Ship_Radar(size, type, id, board1));
                        shipsP2.addShip(new Ship_Radar(size, type, id, board2));
                    } else if(type.equals("Battleship")) {
                        shipsP1.addShip(new Ship_Heavy(size, type, id));
                        shipsP2.addShip(new Ship_Heavy(size, type, id));
                    } else if(type.equals("Destroyer")) {
                        shipsP1.addShip(new Ship_Bomber(size, type, id));
                        shipsP2.addShip(new Ship_Bomber(size, type, id));
                    } else if(type.equals("Explosive")) {
                        shipsP1.addShip(new Ship_OnDeath(size, type, id, board2));
                        shipsP2.addShip(new Ship_OnDeath(size, type, id, board1));
                    } else if(type.equals("Heal")) {
                        shipsP1.addShip(new Ship_Heal(size, type, id));
                        shipsP2.addShip(new Ship_Heal(size, type, id));
                    } else if(type.equals("Classic")) {
                        shipsP1.addShip(new Ship_Impl(size, type, id));
                        shipsP2.addShip(new Ship_Impl(size, type, id));
                    }

                    totalOccupiedCells += size; // Update total occupied cells
                }
            }
        }

        Player_Impl p1 = new Player_Impl(namep1, board1, feedb1, shipsP1, shipsP2);
        BotPlayer bot1 = new BotPlayer("bot1", board2, feedb2, shipsP2, shipsP1);
        VisualBoard_Impl vb = new VisualBoard_Impl(boardSize, p1, bot1, feedb1, feedb2);

        p1.setOpponentsBoard(board2);
        bot1.setOpponentsBoard(board1);

        vb.fetchingShips(board1, shipsP1.ships, true);
        stopBackendUntil(vb.allShipPlaced);

        System.out.println("Now starting game session - all ships are located");
        vb.createGameBoards(p1, bot1);
        vb.showBaordPvE();

        boolean attacking = false;
        Set<ShotsFeedback> attackFeedback = new HashSet<>();

        for (int i = 0; i < boardSize*boardSize; i++) {

            vb.endTurnBot();
            stopBackendUntil(vb.endTurn);

            vb.repaintMap(true);

            if (!bot1.hasShips()) {
                writer.incrementWinnerIndex(p1.getName());
                break;
            }

//////////////////////////////////////////////
            
            vb.turnBot();
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            do {
                attacking = false;
                attackFeedback = new HashSet<>();
                attackFeedback.addAll(bot1.makeMove());

                for (ShotsFeedback attack : attackFeedback) {
                    vb.repaintMap(false);
                    attacking |= attack.hit;
                }
            } while (attacking);

            if (!p1.hasShips()) {
                break;
            }
        }

        vb.win();
    }

    public void playBotVsBot() {
        System.out.println("Playing Bot vs Bot mode...");

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        JTextField sizeGB = new JTextField(10);
        JTextField shipC = new JTextField(10);
        panel2.add(new JLabel("Enter the size of the Gameboard (it will be a square, between " + MINIMUM_BOARD_SIZE + " and " + MAXIMUM_BOARD_SIZE + ")"));
        panel2.add(sizeGB);
        panel2.add(Box.createVerticalStrut(15)); // A spacer
        panel2.add(new JLabel("How many ships will you play with:"));
        panel2.add(shipC);

        int boardSize = -1;
        int shipCount = -1;
        while (true) {
            int result2 = JOptionPane.showConfirmDialog(null, panel2, "Gameboard Size and Total Ships:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result2 != JOptionPane.OK_OPTION) {
                return;
            }
            String sizeStr = sizeGB.getText();
            String shipStr = shipC.getText();
            try {
                boardSize = Integer.parseInt(sizeStr);
                shipCount = Integer.parseInt(shipStr);
                if (boardSize < MINIMUM_BOARD_SIZE || boardSize > MAXIMUM_BOARD_SIZE) {
                    JOptionPane.showMessageDialog(null, "Board size must be between 3 and 100.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid board size or number of ships.");
            }
        }
        JOptionPane.showMessageDialog(null, "Board Size: " + boardSize + "x" + boardSize + ", Ships: " + shipCount);


        int maxOccupiedCells = boardSize * boardSize / 2;
        int totalOccupiedCells = 0;

        ShipManager shipsP1 = new ShipManager();
        ShipManager shipsP2 = new ShipManager();

        for (int i = 0; i < shipCount; i++) {
            boolean retrySelection = true;
            while (retrySelection) {
                JPanel panel3 = new JPanel();
                panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

                JLabel sizeLabel = new JLabel("Select size of Ship Nr. " + (i + 1) + " (between " + MINIMUM_SHIP_SIZE + " and " + boardSize + "):");
                JTextField sizeInput = new JTextField(5);
                panel3.add(sizeLabel);
                panel3.add(sizeInput);

                JLabel typeLabel = new JLabel("Select type of Ship Nr. " + (i + 1) + ":");
                JRadioButton typeBattleship = new JRadioButton("Battleship");
                JRadioButton typeDestroyer = new JRadioButton("Destroyer");
                JRadioButton typeExplosive = new JRadioButton("Explosive");
                JRadioButton typeRadar = new JRadioButton("Radar");
                JRadioButton typeHeal = new JRadioButton("Heal");
                JRadioButton typeClassic = new JRadioButton("Classic");
                ButtonGroup typeGroup = new ButtonGroup();
                typeGroup.add(typeBattleship);
                typeGroup.add(typeDestroyer);
                typeGroup.add(typeExplosive);
                typeGroup.add(typeRadar);
                typeGroup.add(typeHeal);
                typeGroup.add(typeClassic);
                typeBattleship.setSelected(true);

                JPanel typePanel = new JPanel();
                typePanel.add(typeLabel);
                typePanel.add(typeBattleship);
                typePanel.add(typeDestroyer);
                typePanel.add(typeExplosive);
                typePanel.add(typeRadar);
                typePanel.add(typeHeal);
                typePanel.add(typeClassic);
                panel3.add(typePanel);

                int result3 = JOptionPane.showConfirmDialog(null, panel3, "Ship Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result3 != JOptionPane.OK_OPTION) {
                    return;
                }

                int size = -1;
                try {
                    size = Integer.parseInt(sizeInput.getText());
                    if (size < MINIMUM_SHIP_SIZE || size > boardSize) {
                        JOptionPane.showMessageDialog(null, "Ship size must be between " + MINIMUM_SHIP_SIZE + " and " + boardSize + ".");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid ship size.");
                    continue;
                }

                String type = null;
                if (typeBattleship.isSelected()) type = "Battleship";
                else if (typeDestroyer.isSelected()) type = "Destroyer";
                else if (typeExplosive.isSelected()) type = "Explosive";
                else if (typeRadar.isSelected()) type = "Radar";
                else if (typeHeal.isSelected()) type = "Heal";
                else if(typeClassic.isSelected()) type = "Classic";

                board1 = new BoardStart("bot1", boardSize, boardSize);
                feedb1 = new BoardFeedback(boardSize, boardSize);

                board2 = new BoardStart("bot2", boardSize, boardSize);
                feedb2 = new BoardFeedback(boardSize, boardSize);


                if (totalOccupiedCells + size > maxOccupiedCells) {
                    JOptionPane.showMessageDialog(null, "Total occupied cells cannot exceed 50% of the board size.");
                    retrySelection = true; // Retry for the same ship
                    i = 0; // Restart from the first ship
                    totalOccupiedCells = 0; // Reset total occupied cells
                } else {
                    retrySelection = false; // Valid ship selected, exit the retry loop
                    String id = String.valueOf(i + 1);



                    // TODO change class based on tipe
                    if(type.equals("Radar")) {
                        shipsP1.addShip(new Ship_Radar(size, type, id, board1));
                        shipsP2.addShip(new Ship_Radar(size, type, id, board2));
                    } else if(type.equals("Battleship")) {
                        shipsP1.addShip(new Ship_Heavy(size, type, id));
                        shipsP2.addShip(new Ship_Heavy(size, type, id));
                    } else if(type.equals("Destroyer")) {
                        shipsP1.addShip(new Ship_Bomber(size, type, id));
                        shipsP2.addShip(new Ship_Bomber(size, type, id));
                    } else if(type.equals("Explosive")) {
                        shipsP1.addShip(new Ship_OnDeath(size, type, id, board2));
                        shipsP2.addShip(new Ship_OnDeath(size, type, id, board1));
                    } else if(type.equals("Heal")) {
                        shipsP1.addShip(new Ship_Heal(size, type, id));
                        shipsP2.addShip(new Ship_Heal(size, type, id));
                    } else if(type.equals("Classic")) {
                        shipsP1.addShip(new Ship_Impl(size, type, id));
                        shipsP2.addShip(new Ship_Impl(size, type, id));
                    }

                    totalOccupiedCells += size; // Update total occupied cells
                }
            }
        }

        BotPlayer bot1=new BotPlayer("bot1",board1,feedb1,shipsP1,shipsP2);
        BotPlayer bot2=new BotPlayer("bot2",board2,feedb2,shipsP2,shipsP1);
        bot1.setOpponentsBoard(board2);
        bot2.setOpponentsBoard(board1);

        VisualBoard_Impl vb = new VisualBoard_Impl(boardSize, bot1, bot2, feedb1, feedb2);

        System.out.println("Now starting game session - all ships are located");
        vb.createGameBoards(bot1, bot2);
        vb.showBaordsForBot();

        boolean attacking = false;
        Set<ShotsFeedback> attackFeedback = new HashSet<>();

        for (int i = 0; /*pseudo infinite move*/; i++) {
            System.out.println("Turn " + i);

            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            do {
                attacking = false;
                attackFeedback = new HashSet<>();
                attackFeedback.addAll(bot1.makeMove());

                for (ShotsFeedback attack : attackFeedback) {
                    vb.repaintMap(true);
                    attacking |= attack.hit;
                }
            } while (attacking);

            if (!bot2.hasShips()) {
                break;
            }

//////////////////////////////////////////////

            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            do {
                attacking = false;
                attackFeedback = new HashSet<>();
                attackFeedback.addAll(bot2.makeMove());

                for (ShotsFeedback attack : attackFeedback) {
                    vb.repaintMap(false);
                    attacking |= attack.hit;
                }
            } while (attacking);

            if (!bot1.hasShips()) {
                break;
            }
        }

        vb.win();
    }

    // FIXME change referee into playPvP ?
    public void referee() {
        System.out.println("Playing PvP mode...");

        PlayerStatus_Impl writer = new PlayerStatus_Impl();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JTextField nameFieldp1 = new JTextField(10);
        JTextField nameFieldp2 = new JTextField(10);
        panel.add(new JLabel("Enter Player 1 name:"));
        panel.add(nameFieldp1);
        panel.add(Box.createVerticalStrut(15)); // A spacer
        panel.add(new JLabel("Enter Player 2 name:"));
        panel.add(nameFieldp2);
        int result = JOptionPane.showConfirmDialog(null, panel, "Player Names:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }
        String namep1 = nameFieldp1.getText();
        writer.selectPlayer(namep1);
        String namep2 = nameFieldp2.getText();
        writer.selectPlayer(namep2);
        JOptionPane.showMessageDialog(null, "Player 1: " + namep1 + ", Player 2: " + namep2);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        JTextField sizeGB = new JTextField(10);
        JTextField shipC = new JTextField(10);
        panel2.add(new JLabel("Enter the size of the Gameboard (it will be a square, between " + MINIMUM_BOARD_SIZE + " and " + MAXIMUM_BOARD_SIZE + ")"));
        panel2.add(sizeGB);
        panel2.add(Box.createVerticalStrut(15)); // A spacer
        panel2.add(new JLabel("How many ships will you play with:"));
        panel2.add(shipC);

        int boardSize = -1;
        int shipCount = -1;
        while (true) {
            int result2 = JOptionPane.showConfirmDialog(null, panel2, "Gameboard Size and Total Ships:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result2 != JOptionPane.OK_OPTION) {
                return;
            }
            String sizeStr = sizeGB.getText();
            String shipStr = shipC.getText();
            try {
                boardSize = Integer.parseInt(sizeStr);
                shipCount = Integer.parseInt(shipStr);
                if (boardSize < MINIMUM_BOARD_SIZE || boardSize > MAXIMUM_BOARD_SIZE) {
                    JOptionPane.showMessageDialog(null, "Board size must be between 3 and 100.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid board size or number of ships.");
            }
        }
        JOptionPane.showMessageDialog(null, "Board Size: " + boardSize + "x" + boardSize + ", Ships: " + shipCount);


        int maxOccupiedCells = boardSize * boardSize / 2;
        int totalOccupiedCells = 0;

        ShipManager shipsP1 = new ShipManager();
        ShipManager shipsP2 = new ShipManager();

        for (int i = 0; i < shipCount; i++) {
            boolean retrySelection = true;
            while (retrySelection) {
                JPanel panel3 = new JPanel();
                panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

                JLabel sizeLabel = new JLabel("Select size of Ship Nr. " + (i + 1) + " (between " + MINIMUM_SHIP_SIZE + " and " + boardSize + "):");
                JTextField sizeInput = new JTextField(5);
                panel3.add(sizeLabel);
                panel3.add(sizeInput);

                JLabel typeLabel = new JLabel("Select type of Ship Nr. " + (i + 1) + ":");
                JRadioButton typeBattleship = new JRadioButton("Battleship");
                JRadioButton typeDestroyer = new JRadioButton("Destroyer");
                JRadioButton typeExplosive = new JRadioButton("Explosive");
                JRadioButton typeRadar = new JRadioButton("Radar");
                JRadioButton typeHeal = new JRadioButton("Heal");
                JRadioButton typeClassic = new JRadioButton("Classic");
                ButtonGroup typeGroup = new ButtonGroup();
                typeGroup.add(typeBattleship);
                typeGroup.add(typeDestroyer);
                typeGroup.add(typeExplosive);
                typeGroup.add(typeRadar);
                typeGroup.add(typeHeal);
                typeGroup.add(typeClassic);
                typeBattleship.setSelected(true);

                JPanel typePanel = new JPanel();
                typePanel.add(typeLabel);
                typePanel.add(typeBattleship);
                typePanel.add(typeDestroyer);
                typePanel.add(typeExplosive);
                typePanel.add(typeRadar);
                typePanel.add(typeHeal);
                typePanel.add(typeClassic);
                panel3.add(typePanel);

                int result3 = JOptionPane.showConfirmDialog(null, panel3, "Ship Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result3 != JOptionPane.OK_OPTION) {
                    return;
                }

                int size = -1;
                try {
                    size = Integer.parseInt(sizeInput.getText());
                    if (size < MINIMUM_SHIP_SIZE || size > boardSize) {
                        JOptionPane.showMessageDialog(null, "Ship size must be between " + MINIMUM_SHIP_SIZE + " and " + boardSize + ".");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid ship size.");
                    continue;
                }

                String type = null;
                if (typeBattleship.isSelected()) type = "Battleship";
                else if (typeDestroyer.isSelected()) type = "Destroyer";
                else if (typeExplosive.isSelected()) type = "Explosive";
                else if (typeRadar.isSelected()) type = "Radar";
                else if (typeHeal.isSelected()) type = "Heal";
                else if(typeClassic.isSelected()) type = "Classic";

                board1 = new BoardStart(namep1, boardSize, boardSize);
                feedb1 = new BoardFeedback(boardSize, boardSize);

                board2 = new BoardStart(namep2, boardSize, boardSize);
                feedb2 = new BoardFeedback(boardSize, boardSize);


                if (totalOccupiedCells + size > maxOccupiedCells) {
                    JOptionPane.showMessageDialog(null, "Total occupied cells cannot exceed 50% of the board size.");
                    retrySelection = true; // Retry for the same ship
                    i = 0; // Restart from the first ship
                    totalOccupiedCells = 0; // Reset total occupied cells
                } else {
                    retrySelection = false; // Valid ship selected, exit the retry loop
                    String id = String.valueOf(i + 1);



                    // TODO change class based on tipe
                    if(type.equals("Radar")) {
                        shipsP1.addShip(new Ship_Radar(size, type, id, board1));
                        shipsP2.addShip(new Ship_Radar(size, type, id, board2));
                    } else if(type.equals("Battleship")) {
                        shipsP1.addShip(new Ship_Heavy(size, type, id));
                        shipsP2.addShip(new Ship_Heavy(size, type, id));
                    } else if(type.equals("Destroyer")) {
                        shipsP1.addShip(new Ship_Bomber(size, type, id));
                        shipsP2.addShip(new Ship_Bomber(size, type, id));
                    } else if(type.equals("Explosive")) {
                        shipsP1.addShip(new Ship_OnDeath(size, type, id, board2));
                        shipsP2.addShip(new Ship_OnDeath(size, type, id, board1));
                    } else if(type.equals("Heal")) {
                        shipsP1.addShip(new Ship_Heal(size, type, id));
                        shipsP2.addShip(new Ship_Heal(size, type, id));
                    } else if(type.equals("Classic")) {
                        shipsP1.addShip(new Ship_Impl(size, type, id));
                        shipsP2.addShip(new Ship_Impl(size, type, id));
                    }

                    totalOccupiedCells += size; // Update total occupied cells
                }
            }
        }

        Player_Impl p1 = new Player_Impl(namep1, board1, feedb1, shipsP1, shipsP2);

        Player_Impl p2 = new Player_Impl(namep2, board2, feedb2, shipsP2, shipsP1);

        VisualBoard_Impl vb = new VisualBoard_Impl(boardSize, p1, p2, feedb1, feedb2);

        p1.setOpponentsBoard(board2);
        p2.setOpponentsBoard(board1);
        vb.fetchingShips(board1, shipsP1.ships, true);
        stopBackendUntil(vb.allShipPlaced);

        vb.fetchingShips(board2, shipsP2.ships, false);
        stopBackendUntil(vb.allShipPlaced);

        System.out.println("Now starting game session - all ships are located");
        vb.createGameBoards(p1, p2);

        for (int i = 0; i < board2.getHeight(); i++) {
            for (int x = 0; x < board2.getHeight(); x++) {
                System.out.println( p1.opponentsBoard.getCell(i, x));
            }
        }

        for (int i = 0; i < boardSize*boardSize; i++) {
            vb.turnP1();
            stopBackendUntil(vb.endTurn);

            vb.repaintMap(true);

            if (!p2.hasShips()) {
                writer.incrementWinnerIndex(p1.getName());
                break;
            }

//////////////////////////////////////////////

            vb.turnP2();
            stopBackendUntil(vb.endTurn);

            vb.repaintMap(false);

            if (!p1.hasShips()) {
                writer.incrementWinnerIndex(p2.getName());
                break;
            }
        }

        // TODO show winner in bether way ??
        vb.win();

    }

    public static void stopBackendUntil(boolean[] condition) {
        while (!condition[0]) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // TODO check if is possible
        //  Main thread will wait here until the GUI thread finishes.
        /*
        *
        synchronized (Main.class) {
            Main.class.wait();
        }
        synchronized (Main.class) {
            Main.class.notify();
        }
        *
        *
        * */

    }
}


