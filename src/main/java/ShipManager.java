import java.util.HashMap;
import java.util.Map;

public class ShipManager {
    private final Map<Integer, Ship_Impl> ships;

    public ShipManager() {
        ships = new HashMap<>();
    }

    public void addShip(Ship_Impl ship) {
        ships.put(Integer.parseInt(ship.getId()), ship);
    }

    public Ship_Impl getShipById(String id) {
        return ships.get(Integer.parseInt(id));
    }
    public void removeShipById(String id) {
        ships.remove(Integer.parseInt(id));
    }
}
