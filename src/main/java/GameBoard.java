public interface GameBoard {

    void putWater();
    int getWidth();
    int getHeight();
    String getCell(int x, int y);
    boolean areValidCoordinates(int x, int y);


}

