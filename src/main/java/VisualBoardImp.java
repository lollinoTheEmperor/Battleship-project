import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualBoardImp implements VisualBoard {

    JFrame frame;
    JTextField textField;
    JButton button;
    boolean endTurn;


    public VisualBoardImp (String player, int size, int myXLocation) {
        createVisualBoard(player, size, myXLocation);
        endTurn = false;
    }

    private boolean shotHit(int row, int col) {
        int myRow = 2, myCol = 3;

        return (row == myRow && col == myCol);
    }

    @Override
    public void createVisualBoard(String player, int size, int myXLocation) {
        frame=new JFrame("Button Example");
        textField=new JTextField();
        button=new JButton("Attack!!");

        int bordRows = size, boardCols = size;

        JPanel boardPanel = new JPanel(new GridLayout(bordRows, boardCols)); // Change grid size as needed
        for (int i = 0; i < boardCols*bordRows; i++) {
            final int index = i;
            JButton square = new JButton();
            square.setBackground(Color.WHITE);

            Font font = new Font("MonoSpace", Font.BOLD, 25);
            square.setFont(font);

            square.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int row = index / bordRows + 1;
                    int col = index % boardCols + 1;
                    String coord = String.valueOf(row) + ":" + String.valueOf(col);
                    textField.setText(coord);

                }
            });
            boardPanel.add(square);
        }

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                parseCoord(textField.getText().toUpperCase());
            }
        });

        frame.add(button);
        frame.add(textField);
        frame.add(boardPanel); // Add boardPanel to JFrame
        frame.setSize(550,400); // Adjust size as needed
        textField.setBounds(340,50, 150,20);
        button.setBounds(340,100,95,30);
        boardPanel.setBounds(50, 100, 200, 200); // Adjust position and size as needed
        frame.setLayout(null);
        frame.setVisible(true);

        frame.setLocation(myXLocation, 0);
    }

    @Override
    public void getTableCoord(int x, int y) {

    }

    @Override
    public void sendAttackCoord(int x, int y) {

    }

    @Override
    public void parseCoord(String coord) {
       System.out.println(coord);

        String[] parseCoord = coord.split(":");

        int myRow = Integer.parseInt(parseCoord[0]);
        int myCol =  Integer.parseInt(parseCoord[1]);

        textField.setText("");

        this.endTurn = true;

        //TODO send coord
        // myCoord[0] myCoord[1];
    }

    @Override
    public void showTable() {
        this.frame.setVisible(true);
    }

    @Override
    public void hideTable() {
        this.frame.setVisible(false);
    }

}
