public class BoardStart extends GameBoard_Impl {

    public BoardStart(String title) {
        super(title);
    }
    public BoardStart(String title, int width, int height) {
        super(title,width,height);
    }

    //used to place the ship
    public boolean placeShip(int x, int y, int orientation, Ship_Impl ship) {
        if(orientation == 0 || orientation == 1) {
            // Check if the ship fits on the game board
            if ((orientation == 0 && x + ship.getSize() > width) || (orientation == 1 && y + ship.getSize() > height)) {
                System.out.println("Ship does not fit on the game board.");
                return false;
            }

            // Check if the coordinates are already occupied
            for (int i = 0; i < ship.getSize(); i++) {
                int newX = (orientation == 0) ? x + i : x;
                int newY = (orientation == 1) ? y + i : y;
                if (!(getCell(x,y).equals("water"))) {
                    System.out.println("Coordinate (" + newX + ", " + newY + ") is already occupied.");
                    return false;
                }
            }
            // Place the ship on the game board
            for (int i = 0; i < ship.getSize(); i++) {
                int newX = (orientation == 0) ? x + i : x;
                int newY = (orientation == 1) ? y + i : y;
                board_Game[x][y]=ship.getId();
            }

            System.out.println("Ship placed successfully.");
             return true;
        }
        return false;
    }
}
