import java.util.Random;

public class BotPlayer extends Player_Impl {
    private Heatmap_Impl heatmap;
    private AttackStrategy strategy;
    private CheckboardStrategy checkboardStrategy;
    private SpiralStrategy spiralStrategy;
    private int moveCount;
    private ShipManager opponentsShipManager;

    //a bot need name, board start his and of the adversare, board feedback, a shipmanager, a strategy.
    public BotPlayer(String name, BoardStart myBoard, BoardStart opponentsBoard, BoardFeedback myFeedbacks, ShipManager shipManager, ShipManager opponentsShipManager) {
        super(name, myBoard, myFeedbacks, shipManager, opponentsShipManager);
        this.heatmap = new Heatmap_Impl(myFeedbacks, shipManager);
        this.setOpponentsBoard(opponentsBoard);
        this.checkboardStrategy = new CheckboardStrategy();
        this.spiralStrategy = new SpiralStrategy();
        this.strategy = checkboardStrategy;
        this.moveCount = 0;

        placeShip();
//      needed for visualising the board during the tests "ShipPlacement"
//        for (int i=0;i<myBoard.getWidth();i++){
//            for (int j=0;j<myBoard.getHeight();j++){
//                System.out.print("\t"+myBoard.getCell(i,j));
//            }
//            System.out.println();
//        }
    }

    private void updateHeatmap() {
        heatmap.updateheatMap();
    }

    public void placeShip() {
        Random random = new Random();
        int x,y;
        for (int i:shipManager.ships.keySet()){
            int orientation = random.nextInt(2);
            int shipSize = shipManager.getShipById(Integer.toString(i)).getSize();
            boolean done = false;
            while (!done){
                switch (orientation){
                    case 0:
                        x = random.nextInt(myBoard.getWidth());
                        y = random.nextInt(myBoard.getHeight()-shipSize);
                        done = myBoard.placeShip(x, y, orientation, shipManager.getShipById(Integer.toString(i)));
                        break;
                    case 1:
                        x = random.nextInt(myBoard.getWidth()-shipSize)+shipSize;
                        y = random.nextInt(myBoard.getHeight());
                        done = myBoard.placeShip(x, y, orientation, shipManager.getShipById(Integer.toString(i)));
                        break;
                }
            }
        }
    }


    // choose which one is the next move, first if there is a ship marked
    // as hitted chose an adjante to hit otherwise it use a strategy
    public int[] getNextMove() {
        changeStrategyIfNeeded();
        int[] nextMove = findAdjacentMove(); // check befoe adiacent cell
        if (nextMove == null) {
            nextMove = strategy.getNextMove(heatmap, myFeedbacks);
        }
        return nextMove;
    }
    //update the heat map, call getNextMove and adjour the move count,
    // attack in base which one is the next move
    public boolean makeMove() {
        heatmap.updateheatMap();
        int[] move = getNextMove();
        moveCount++;
        // FIXME now return tipe is Set<ShotsFeedback> (it is an object with boolean hit, and int x,y)
        //  return attack(move[0], move[1]);
        return false;
    }
    //every three move it change the strategy.
    private void changeStrategyIfNeeded() {
        if (moveCount > 3) {
            if (strategy instanceof CheckboardStrategy) {
                strategy = spiralStrategy;
            } else {
                strategy = checkboardStrategy;
            }
            moveCount = 0;
        }
    }
    //check if there are ship marked as hitted and if so check the near one
    private int[] findAdjacentMove() {
        int width = myFeedbacks.getWidth();
        int height = myFeedbacks.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (myFeedbacks.isHit(x, y)) {
                    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
                    for (int[] dir : directions) {
                        int newX = x + dir[0];
                        int newY = y + dir[1];
                        if (isValidMove(newX, newY, width, height)) {
                            return new int[]{newX, newY};
                        }
                    }
                }
            }
        }
        return null; // none found
    }
    //check if is a valid move
    private boolean isValidMove(int x, int y, int width, int height) {
        return x >= 0 && x < width && y >= 0 && y < height && !myFeedbacks.isAlreadyAttacked(x, y);
    }
}
