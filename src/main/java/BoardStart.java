public class BoardStart extends GameBoard_Impl {

    protected String title;

    public BoardStart(String title) {
        super();
        this.title = title;
    }
    public BoardStart(String title, int width, int height) {
        super(width,height);
        this.title = title;
    }
    //orientation=0 means orizontal, 1 vertical
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
                if (!(getCell(newX,newY).equals("water"))) {
                    System.out.println("Coordinate (" + newX + ", " + newY + ") is already occupied.");
                    return false;
                }
            }
            // Place the ship on the game board
            for (int i = 0; i < ship.getSize(); i++) {
                int newX = (orientation == 0) ? x + i : x;
                int newY = (orientation == 1) ? y + i : y;
                board_Game[newX][newY]=ship.getId();
            }

            System.out.println("Ship placed successfully.");
             return true;
        }
        return false;
    }
}
