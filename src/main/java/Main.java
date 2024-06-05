
public class Main {
    public static void main(String[] args) {

        System.out.printf("Hello and welcome!\n");

        VisualBoardImp myBoard = new VisualBoardImzvp("palyer1", 10, 0);
        VisualBoardImp mySecondBoard = new VisualBoardImp("player2", 10, 500);

        for (int i =0; i < 10; i++) {
            turnManager(myBoard, mySecondBoard);
            turnManager(mySecondBoard, myBoard);
        }
    }

    // created to test tables TODO insert this in other class 'referee'
    public static void turnManager(VisualBoardImp B1, VisualBoardImp B2){
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