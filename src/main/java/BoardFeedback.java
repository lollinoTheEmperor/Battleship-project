public class BoardFeedback extends GameBoard_Impl {

    public BoardFeedback(String title) {
        super();
    }
    public BoardFeedback(String title, int width, int height) {
        super(width, height);
    }

    //check the board with the ship for the attack if it has water then putt miss,
    // other wise put hit and if it was already attacked in console log says
    // already attacked
    public boolean addFeedBack(BoardStart board_starter, int x, int y){
        if(board_Game[x][y].equals("water")){
            switch (board_starter.getCell(x,y)){
                case "water" :
                    board_Game[x][y]="miss";
                    return false;
                default:
                    board_Game[x][y]="hit";

                    //prendere nava dall'array delle nave avendo id cosi: board_starter.getCell(x,y), chiamare metodo take damage.
                    return true;
            }
        }
        else{
            System.out.println("Cell already attacked");
            return true;
        }
    }


}
