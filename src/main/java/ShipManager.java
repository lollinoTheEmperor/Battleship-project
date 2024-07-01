import java.util.HashMap;
import java.util.Map;

public class ShipManager {
    public final Map<Integer, Ship_Impl> ships;

    public ShipManager() {
        ships = new HashMap<>();
    }

    public void addShip(Ship_Impl ship) {
        int shipId = Integer.parseInt(ship.getId());
        ships.put(shipId, ship);
    }

    public Ship_Impl getShipById(String id) {
        int shipId = Integer.parseInt(id);
        Ship_Impl ship = ships.get(shipId);
        return ship;
    }

    public void removeShipById(String id) {
        int shipId = Integer.parseInt(id);
        ships.remove(shipId);
    }

    public Map<Integer, Ship_Impl> getShips() {
        return ships;
    }
}
