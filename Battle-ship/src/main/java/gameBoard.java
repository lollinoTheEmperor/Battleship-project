public interface gameBoard {

    void putWater();
    int getWidth();
    int getHeght();
    String getName();
    String getCell(int X, int Y);
    boolean areValidCoordinates(int X, int Y);


}

