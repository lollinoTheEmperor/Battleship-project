public class Board_start extends gameBoard_Impl{

    public Board_start(String title) {
        super(title);
    }
    public Board_start(String title, int width, int height) {
        super(title,width,height);
    }

    //used to place the ship
    public boolean placeShip(int X, int Y, int size, int orientation, Ship_Impl ship) {
        if(orientation == 0 || orientation == 1) {
            // Check if the ship fits on the game board
            if ((orientation == 0 && X + size > width) || (orientation == 1 && Y + size > height)) {
                System.out.println("Ship does not fit on the game board.");
                return false;
            }

            // Check if the coordinates are already occupied
            for (int i = 0; i < size; i++) {
                int newX = (orientation == 0) ? X + i : X;
                int newY = (orientation == 1) ? Y + i : Y;
                if (!(getCell(X,Y).equals("water"))) {
                    System.out.println("Coordinate (" + newX + ", " + newY + ") is already occupied.");
                    return false;
                }
            }
            // Place the ship on the game board
            for (int i = 0; i < size; i++) {
                int newX = (orientation == 0) ? X + i : X;
                int newY = (orientation == 1) ? Y + i : Y;
                board_Game[X][Y]=ship.getId();
            }

            System.out.println("Ship placed successfully.");
            ship.setX(X);
            ship.setY(Y);
             return true;
        }
        return false;
    }

    public boolean isCoordinataOccupata(int X, int Y) {
        return getCell(X, Y).equals("water");
    }
//Maybe more easy to do e new ship and destroy the precedent?
   public void occupaCoordinata(int x, int y, String ship) {
        board_Game[x][y] = ship;
    }

    public void voidaCoordinata(int x, int y) {
        board_Game[x][y] = "water";
    }
    public String[][] getshipMap(){
        return this.board_Game;
    }
}
