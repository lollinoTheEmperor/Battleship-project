import java.util.Map;


public class Ship_Impl implements Ship{
    int size; //size of the Ship
    
    private int hp; //health of the ship
    String shipName;    //se vogliamo dare un nome ai tipi di barche
    int orientation; //0 for a horizontal Ship; 1 for vertical (replace with boolean?)
    int y; //starting y coord
    int x; //starting x coord

    private String id_ship; //id ship for position on the map
    public Ship_Impl(int size, int hp, String name, int orientation, int x, int y, BoardStart Mappa) {
        
        this.size = size;
        this.hp = hp;
        this.shipName = name;
        if (orientation == 1 || orientation == 0) {
        this.orientation = orientation;

        //whenever we create a Ship we should also position it, need a Map before we make a Ship
        //if we just wanna create Ships without placing them can also take this out
        Mappa.placeShip(x, y, size, orientation, this);
        }
    }

    

    //check if the ship was destroyed based on its hp
    @Override
    public boolean isDestroyed(Ship_Impl Ship) {
        if (this.hp == 0) {
            return true;
        } else{
            return false;
        }
        
    }

    // //check if a certain coordinate has been hit, dont know if needed
    // @Override
    // public boolean isHit(Ship_Impl Ship) { 
    //     return false;
    // }

    //check if the move is possible, Already done by all other methods that needed it, can still implement it
    //@Override
    // public boolean isValidMove(Ship_Impl Ship) {
    //     return false;
    // }

    //move Ship to other coordinates
    @Override
    public void moveShip(int newX, int newY, BoardStart mappa) {

        // Check if the new coordinates are within the boundaries of the map
        if (newX < 0 || newX >= mappa.getWidth() || newY < 0 || newY >= mappa.getHeight()) {
            System.out.println("Cannot move ship outside the boundaries of the map.");
            return;
        }
    
        // Clear the ship's current coordinates on the map
        for (int i = 0; i < size; i++) {
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
    
        // Update shipMap with new coordinates
        updateShipMap(newX, newY, mappa);
    
        System.out.println("Ship moved to coordinates (" + newX + ", " + newY + ").");
    }

    //show where the ship is
    @Override
    public void showShip(Ship_Impl Ship, BoardStart Mappa){
        for(Object obj : Mappa.getshipMap().entrySet()) {
            if (obj instanceof Map.Entry) {
                Map.Entry<String, Ship> entry = (Map.Entry<String, Ship>) obj;
                String key = entry.getKey();
                Ship value = entry.getValue();
                System.out.println("Coordinates: " + key);
            }
        }
    }
    public String getId(){
        return this.id_ship;
    }
    //when hit reduce hp of Ship
    @Override
    public void takeDamage(Ship_Impl Ship) { 

        //if a hit can only take away 1hp, otherwise need more parameters
        this.hp--;

    }

    public int getX() {
        return x;
    }
  
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //could also be in class Mappa, will see
    private void updateShipMap(int newX, int newY, BoardStart mappa) {
        // Remove old ship coordinates from shipMap
        for (int i = 0; i < size; i++) {
            int oldCoordX = (orientation == 0) ? x + i : x;
            int oldCoordY = (orientation == 1) ? y + i : y;
            String oldCoordinate = oldCoordX + "," + oldCoordY;
            mappa.getshipMap().remove(oldCoordinate);
        }
    
        // Add new ship coordinates to shipMap
        for (int i = 0; i < size; i++) {
            int newCoordX = (orientation == 0) ? newX + i : newX;
            int newCoordY = (orientation == 1) ? newY + i : newY;
            String newCoordinate = newCoordX + "," + newCoordY;
            mappa.getshipMap().put(newCoordinate, this);
        }
    }


}

