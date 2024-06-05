public class GameBoard_Impl implements GameBoard {
    //name = name mappa, board game is a table
    protected String name;
    protected int width;
    protected int height;
    protected String [][] board_Game;

    //first one is used when width and heght are not specified
    public GameBoard_Impl(String title){
        this.name=title;
        this.width = 10;
        this.height= 10;
        this.board_Game = new String[width][height];
        putWater();
    }
    //this constructor is used when the player can choose the heght and width
    public GameBoard_Impl(String title, int width, int height){
        this.name=title;
        this.width = width;
        this.height= height;
        this.board_Game = new String[this.width][height];
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
    public String getCell(int x, int y){
        if(areValidCoordinates(x, y)){
           return null;
        }
        return board_Game[x][y];
    }
    //check if the coordinates are valid
    @Override
        public boolean areValidCoordinates(int x, int y) {
            return (x >= 0 && x < width && y >= 0 && y < height);
        }
}
