public class Board_start extends gameBoard_Impl{

    public Board_start(String title) {
        super(title);
    }
    public Board_start(String title, int widht, int height) {
        super(title,widht,height);
    }
    public boolean placeShip(Ship ship, int X, int Y) {
        if(getCell(X,Y).equals("water")){
            board_Game[X][Y]=ship.getId();
            return true;
        }
        System.out.println("Impossibile posizionare nave probabilmente blocco gia occupato");
        return false;
    }
}
