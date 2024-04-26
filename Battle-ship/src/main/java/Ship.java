public interface Ship {

    boolean isDestroyed(Ship_Impl Ship);
    void takeDamage(Ship_Impl Ship);
    boolean isHit(Ship_Impl Ship);
    boolean isValidMove(Ship_Impl Ship);
    void showShip(Ship_Impl Ship, Mappa Mappa);
    void moveShip(int newX, int newY, Mappa mappa);

}
