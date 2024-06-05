import java.util.Map;


public class Ship_Impl implements Ship{

    private int size; //size of the Ship
    String typeShip;    //se vogliamo dare un nome ai tipi di barche
    private String id_ship; //id ship for position on the map
    private int hp; //health of the ship


    public Ship_Impl(int size, String type, String id) {
        this.size = size;
        this.hp = size;
        id_ship = id;
       this.typeShip = type;
    }

    public String getId(){
        return this.id_ship;
    }
    public int getSize(){return size;}
    //when hit reduce hp of Ship
    @Override
    public boolean takeDamage(Ship_Impl ship) {
        //if a hit can only take away 1hp, otherwise need more parameters
        this.hp--;
        if (this.hp == 0) {
            return true;
        } else{
            return false;
        }

    }

}

