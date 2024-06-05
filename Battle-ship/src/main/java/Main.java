
public class Main {
    public static void main(String[] args) {

        System.out.printf("Hello and welcome!\n");

        VisualBoard_Impl myBoard = new VisualBoard_Impl("palyer1", 10, 0);
        VisualBoard_Impl mySecondBoard = new VisualBoard_Impl("player2", 10, 500);

        for (int i =0; i < 10; i++) {
            turnManager(myBoard, mySecondBoard);
            turnManager(mySecondBoard, myBoard);
        }
    }

    // created to test tables TODO insert this in other class 'referee'
    public static void turnManager(VisualBoard_Impl B1, VisualBoard_Impl B2){
        B2.hideTable();
        B1.showTable();
        while (!B1.endTurn) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        B1.endTurn = false;
    }
}