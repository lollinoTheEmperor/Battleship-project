import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {
    public static void main(String[] args) throws InterruptedException {
    
        System.out.printf("Hello and welcome!\n");
    
        // VisualBoard_Impl myBoard = new VisualBoard_Impl("palyer1", 11, 0);
        // VisualBoard_Impl mySecondBoard = new VisualBoard_Impl("player2", 11, 500);
    
            // JPanel panel = new JPanel();
            // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            // JTextField widthGB = new JTextField(10);
            // JTextField heightGB = new JTextField(10);
            // JTextField shipC = new JTextField(10);
            // panel.add(new JLabel("Enter the height of the Gameboard:"));
            // panel.add(heightGB);
            // panel.add(Box.createVerticalStrut(15)); // A spacer
            // panel.add(new JLabel("Enter the width of the Gameboard:"));
            // panel.add(widthGB);
            // panel.add(new JLabel("How many ships will you play with:"));
            // panel.add(shipC);
            // int result = JOptionPane.showConfirmDialog(null, panel, "User Input", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            // if (result == JOptionPane.OK_OPTION) {
            //     String heightStr = heightGB.getText();
            //     String widthStr = widthGB.getText();
            //     String shipStr = shipC.getText();
            //     JOptionPane.showMessageDialog(null, "Height: " + heightStr + ", Width: " + widthStr + ", Ships: " + shipStr);
            // }

        // for (int i =0; i < 10; i++) {
        //         turnManager(myBoard, mySecondBoard);
        //         String str = myBoard.getLastClick();
        //         System.out.println(str + "It works" + str);
        //         turnManager(mySecondBoard, myBoard);
        // }

        referee();

        System.out.println("GG");


        

        
            


    }

//    FIXME, old problem with fetching ship & size of the board is not respected
    public static void referee() throws InterruptedException {

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
        if (result == JOptionPane.OK_OPTION) {
            String namep1 = nameFieldp1.getText();
            String namep2 = nameFieldp2.getText();
            JOptionPane.showMessageDialog(null, "Player 1: " + namep1 + ", Player 2: " + namep2);
        }

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        JTextField widthGB = new JTextField(10);
        JTextField heightGB = new JTextField(10);
        JTextField shipC = new JTextField(10);
        panel2.add(new JLabel("Enter the height of the Gameboard:"));
        panel2.add(heightGB);
        panel2.add(Box.createVerticalStrut(15)); // A spacer
        panel2.add(new JLabel("Enter the width of the Gameboard:"));
        panel2.add(widthGB);
        panel2.add(new JLabel("How many ships will you play with:"));
        panel2.add(shipC);
        int result2 = JOptionPane.showConfirmDialog(null, panel2, "Gameboard Specifics:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result2 == JOptionPane.OK_OPTION) {
            String heightStr = heightGB.getText();
            String widthStr = widthGB.getText();
            String shipStr = shipC.getText();
            JOptionPane.showMessageDialog(null, "Height: " + heightStr + ", Width: " + widthStr + ", Ships: " + shipStr);
            // TODO method to save heightGB.getText(); or width (+ check if is a square) and save to reuse in the constructor of visualboard
        }

        int shipCount;
        try {
            shipCount = Integer.parseInt(shipC.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number of ships.");
            return;
        }

        ShipManager ships1 = new ShipManager();
        ShipManager ships2 = new ShipManager();
       
        for (int i = 0; i < shipCount; i++) {
            JPanel panel4 = new JPanel();
            panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
            JTextField sizeIn = new JTextField(10);
            panel4.add(new JLabel("Size of Ship Nr. " + (i + 1) + ": "));
            panel4.add(sizeIn);

            int result4 = JOptionPane.showConfirmDialog(null, panel4, "Ship Size", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result4 == JOptionPane.OK_OPTION) {
                try {
                    int size = Integer.parseInt(sizeIn.getText());
                    String id = Integer.toString(i + shipCount);
                    ships1.addShip(new Ship_Impl(size, null, id));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid size for ship " + (i + 1));
                    i--; // Retry for the same ship
                }
            }
        }

        for (int i = 0; i < shipCount; i++) {
            JPanel panel4 = new JPanel();
            panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
            JTextField sizeIn = new JTextField(10);
            panel4.add(new JLabel("Size of Ship Nr. " + (i + 1) + ": "));
            panel4.add(sizeIn);

            int result4 = JOptionPane.showConfirmDialog(null, panel4, "Ship Size", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result4 == JOptionPane.OK_OPTION) {
                try {
                    int size = Integer.parseInt(sizeIn.getText());
                    String id2 = Integer.toString(i + shipCount);
                    
                    ships2.addShip(new Ship_Impl(size, null, id2));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid size for ship " + (i + 1));
                    i--; // Retry for the same ship
                }
            }
        }

        String namep1 = nameFieldp1.getText();
        String namep2 = nameFieldp2.getText();
        int height;
        int width;

        try {
            height = Integer.parseInt(heightGB.getText());
            width = Integer.parseInt(widthGB.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid dimensions for the game board.");
            return;
        }

        BoardStart board1 = new BoardStart(namep1, width, height);
        BoardFeedback feedb1 = new BoardFeedback(width, height);
        Player_Impl p1 = new Player_Impl(namep1, board1, feedb1, ships1, ships2);

        BoardStart board2 = new BoardStart(namep2, width, height);
        BoardFeedback feedb2 = new BoardFeedback(width, height);
        Player_Impl p2 = new Player_Impl(namep2, board2, feedb2, ships2, ships1);
        // TODO decidere se usare un single size per square o fare with e height
        VisualBoard_Impl vb = new VisualBoard_Impl(height, p1, p2);
       
        System.out.println("quiiii");


        p1.setOpponentsBoard(board2);
        p2.setOpponentsBoard(board1);
        vb.fetchingShips(board1, ships1.ships, true);
        vb.paintIsland(3,3, true);
        stopBackendUntil(vb.allShipPlaced);


        vb.fetchingShips(board2, ships2.ships, false);
        stopBackendUntil(vb.allShipPlaced);


        System.out.println("Now starting game session - all ships are located");
        // Usare i player?
        vb.createGameBoards(p1, p2);

        
        for (int i =0; i < 10; i++) {
            vb.turnP1();
            stopBackendUntil(vb.endTurn);

            if(!p2.hasShips()) {
                return;
            }

            vb.turnP2();
            stopBackendUntil(vb.endTurn);

            if (!p1.hasShips()) {
                return;
            }
        }


    }


    public static void stopBackendUntil(boolean[] condition) {

        while (!condition[0]) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}



    