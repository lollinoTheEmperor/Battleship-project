import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Ship_Impl implements Ship{

    private int size; //size of the Ship
    String typeShip;    //se vogliamo dare un nome ai tipi di barche
    private String id_ship; //id ship for position on the map
    private int hp; //health of the ship
    List<int[]> coordinates;

    public Ship_Impl(int size, String type, String id) {
        this.size = size;
        this.hp = size;
        id_ship = id;
        this.typeShip = type;
        this.coordinates = new ArrayList<>();
    }

    public String getId(){
        return this.id_ship;
    }
    public int getSize(){return size;}
    public int getHp(){return hp;}

    public void addCoordinate(int x, int y) {
        coordinates.add(new int[]{x, y});
    }

    public List<int[]> getCoordinates() {
        return coordinates;
    }

    //when hit reduce hp of Ship
    @Override
    public boolean takeDamage() {
        //if a hit can only take away 1hp, otherwise need more parameters
        if(hp>0){
          this.hp--;
        }
        return this.hp == 0;
    }

    public Set<ShotsFeedback> attack (BoardFeedback myFeedbacks, BoardStart opponentsBoard, int x, int y, ShipManager shipManager) {

        System.out.println("Attack test for normal ship");
        return Set.of(new ShotsFeedback(myFeedbacks.addFeedBack(opponentsBoard,x,y, shipManager), x, y));
    }
}

