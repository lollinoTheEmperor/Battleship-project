public interface GameBoard {

    void putWater();
    int getWidth();
    int getHeight();
    String getName();
    String getCell(int X, int Y);
    boolean areValidCoordinates(int X, int Y);


}

