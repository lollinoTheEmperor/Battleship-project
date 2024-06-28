import java.util.Random;

public class BotPlayer extends Player_Impl {
    private Heatmap_Impl heatmap;
    private AttackStrategy strategy;
    private CheckboardStrategy checkboardStrategy;
    private SpiralStrategy spiralStrategy;
    private int moveCount;
    public BotPlayer(String name, BoardStart myBoard, BoardStart opponentsBoard, BoardFeedback myFeedbacks, ShipManager shipManager) {
        super(name, myBoard, myFeedbacks, shipManager);
        this.heatmap = new Heatmap_Impl(myFeedbacks, shipManager);
        this.setOpponentsBoard(opponentsBoard);
        this.checkboardStrategy = new CheckboardStrategy();
        this.spiralStrategy = new SpiralStrategy();
        this.strategy = checkboardStrategy;
        this.moveCount = 0;

        placeShip();
    }

    private void updateHeatmap() {
        heatmap.updateheatMap();
    }

    public void placeShip() {
        Random random = new Random();
        boolean done = false;
        int x,y;
        for (int i:shipManager.ships.keySet()){
            int orientation = random.nextInt(2);
            int shipSize = shipManager.getShipById(Integer.toString(i)).getSize();
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

    public int[] getNextMove() {
        changeStrategyIfNeeded();
        int[] nextMove = findAdjacentMove(); // Cerca prima le celle adiacenti
        if (nextMove == null) {
            nextMove = strategy.getNextMove(heatmap, myFeedbacks);
        }
        return nextMove;
    }

    public boolean makeMove() {
        updateHeatmap();
        int[] move = getNextMove();
        moveCount++;
        return attack(move[0], move[1]);
    }

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
        return null; // Nessuna cella adiacente trovata
    }

    private boolean isValidMove(int x, int y, int width, int height) {
        return x >= 0 && x < width && y >= 0 && y < height && !myFeedbacks.isAlreadyAttacked(x, y);
    }
}
