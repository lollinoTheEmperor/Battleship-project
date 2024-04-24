public class gameBoard_Impl implements gameBoard {
    private String name;
    private int width;
    private int height;
    String [][] board_Game;
    public gameBoard_Impl(String title){
        this.name=title;
        this.width = 10;
        this.height= 10;
        String [][] board_Game = new String[width][height];
        putWater();
    }
    public gameBoard_Impl(String title, int widht, int height){
        this.name=title;
        this.width = widht;
        this.height= height;
        String [][] board_Game = new String[width][height];
        putWater();
    }
    public void putWater(){
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                board_Game[row][col] = "water";
            }
        }
    }


    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeght() {
        return height;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getCell(int X, int Y){
        if(areValidCoordinates(X,Y)){
           return null;
        }
        return board_Game[X][Y];
    }

    @Override
    public boolean areValidCoordinates(int X, int Y){
        if((width>=X)&&(height>=Y)){
            return true;
        }
        return false;
    }
}
