
interface VisualBoard {

    //    create the visual board thanks to the size
    void createVisualBoard(String player, int size, int myXLocation);  // size is width and high

    //    is used to get the coordinates of the cliceck cell
    void getTableCoord(int x, int y);

    void sendAttackCoord(int x, int y);

    void parseCoord(String coord);

    void showTable();

    void hideTable();
}
