import java.util.List;
import java.util.Set;

public class BoardFeedback extends GameBoard_Impl {

    public BoardFeedback() {
        super();
    }
    public BoardFeedback( int width, int height) {
        super(width, height);
    }

    //check the board with the ship for the attack if it has water then putt miss,
    // otherwise put hit and if it was already attacked in console log says
    // already attacked
    public boolean addFeedBack(BoardStart boardWithShip, int x, int y, ShipManager allship){
        //check if coordinates valid
        if (!areValidCoordinates(x,y)) {
            System.out.println("Coordinates are out of bounds");
            return false;
        }
        //check what is hitting, if water or a ship
        if(board_Game[x][y].equals("water")){
            switch (boardWithShip.getCell(x,y)){
                case "water" :
                    board_Game[x][y]="miss";
                    return false;
                default:
                    board_Game[x][y]="hit";
                    Ship_Impl ship_hitted = allship.getShipById(boardWithShip.getCell(x,y));
                    ship_hitted.takeDamage();
                    return true;
            }
        }
        //if board feed is not water then already hit
        else{
            System.out.println("Cell already attacked");
            return true;
        }
    }


}
