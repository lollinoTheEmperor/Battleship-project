public class BoardStart extends GameBoard_Impl {

    public BoardStart(String title) {
        super(title);
    }
    public BoardStart(String title, int width, int height) {
        super(title,width,height);
    }

    //used to place the ship
    public boolean placeShip(int x, int y, int size, int orientation, Ship_Impl ship) {
        if(orientation == 0 || orientation == 1) {
            // Check if the ship fits on the game board
            if ((orientation == 0 && x + size > width) || (orientation == 1 && y + size > height)) {
                System.out.println("Ship does not fit on the game board.");
                return false;
            }

            // Check if the coordinates are already occupied
            for (int i = 0; i < size; i++) {
                int newX = (orientation == 0) ? x + i : x;
                int newY = (orientation == 1) ? y + i : y;
                if (!(getCell(x,y).equals("water"))) {
                    System.out.println("Coordinate (" + newX + ", " + newY + ") is already occupied.");
                    return false;
                }
            }
            // Place the ship on the game board
            for (int i = 0; i < size; i++) {
                int newX = (orientation == 0) ? x + i : x;
                int newY = (orientation == 1) ? y + i : y;
                board_Game[x][y]=ship.getId();
            }

            System.out.println("Ship placed successfully.");
             return true;
        }
        return false;
    }
    //move Ship to other coordinates
    @Override
    public void moveShip(int newX, int newY,Ship_Impl ship) {

        // Check if the new coordinates are within the boundaries of the map
        if (newX < 0 || newX >= getWidth() || newY < 0 || newY >= getHeight()) {
            System.out.println("Cannot move ship outside the boundaries of the map.");
            return;
        }

        // Clear the ship's current coordinates on the map
        for (int i = 0; i < ship.getSize(); i++) {
            int coordX = (orientation == 0) ? x + i : x;
            int coordY = (orientation == 1) ? y + i : y;
            mappa.voidaCoordinata(coordX, coordY);
        }

        // Check if the new coordinates are already occupied by another ship
        for (int i = 0; i < size; i++) {
            int coordX = (orientation == 0) ? newX + i : newX;
            int coordY = (orientation == 1) ? newY + i : newY;
            if (mappa.isCoordinataOccupata(coordX, coordY)) {
                System.out.println("Cannot move ship to coordinates (" + newX + ", " + newY + ") because they are occupied.");
                // Restore the ship's previous coordinates on the map
                for (int j = 0; j < i; j++) {
                    int prevCoordX = (orientation == 0) ? newX + j : newX;
                    int prevCoordY = (orientation == 1) ? newY + j : newY;
                    mappa.occupaCoordinata(prevCoordX, prevCoordY, id_ship);
                }
                return;
            }
        }

        // Update the ship's coordinates
        this.x = newX;
        this.y = newY;

        // Place the ship at its new coordinates on the map
        for (int i = 0; i < size; i++) {
            int coordX = (orientation == 0) ? newX + i : newX;
            int coordY = (orientation == 1) ? newY + i : newY;
            mappa.occupaCoordinata(coordX, coordY, id_ship);
        }

        System.out.println("Ship moved to coordinates (" + newX + ", " + newY + ").");
    }
    public boolean isCoordinataOccupata(int x, int y) {
        return getCell(x, y).equals("water");
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
