public class Board_feedback extends gameBoard_Impl {

    public Board_feedback(String title) {
        super(title);
    }
    public Board_feedback(String title, int width, int height) {
        super(title, width, height);
    }

    public void addFeedBack(Board_start board_starter,int X, int Y){
        if(board_Game[X][Y].equals("water")){
            switch (board_starter.getCell(X,Y)){
                case "water" :
                    board_Game[X][Y]="miss";
                    break;
                default:
                    board_Game[X][Y]="hit";
                    break;
            }
        }
        else{
            System.out.println("Cell already attacked");
        }
    }


}
