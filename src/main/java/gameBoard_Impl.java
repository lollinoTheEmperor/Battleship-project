public class gameBoard_Impl implements gameBoard {
    //name = name mappa, board game is a table
    protected String name;
    protected int width;
    protected int height;
    protected String [][] board_Game;

    //first one is used when width and heght are not specified
    public gameBoard_Impl(String title){
        this.name=title;
        this.width = 10;
        this.height= 10;
        this.board_Game = new String[width][height];
        putWater();
    }
    //thi constructor is used when the player can choose the heght and width
    public gameBoard_Impl(String title, int widht, int height){
        this.name=title;
        this.width = widht;
        this.height= height;
        this.board_Game = new String[width][height];
        putWater();
    }

    //helpfull method to populate at the start the map
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
    public int getHeight() {
        return height;
    }
    @Override
    public String getName() {
        return name;
    }

    //method used to return the cell
    @Override
    public String getCell(int X, int Y){
        if(areValidCoordinates(X,Y)){
           return null;
        }
        return board_Game[X][Y];
    }
    //check if the coordinates are valid
    @Override
        public boolean areValidCoordinates(int X, int Y) {
            return (X >= 0 && X < width && Y >= 0 && Y < height);
        }
}
