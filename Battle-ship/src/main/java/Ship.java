public interface Ship {

    String getId();
    void moveShip(int newX, int newY, BoardStart mappa);
    boolean isDestroyed(Ship_Impl Ship);
    void takeDamage(Ship_Impl Ship);
}
