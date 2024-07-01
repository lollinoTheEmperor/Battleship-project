import java.util.Map;
import java.util.Random;
import java.util.Set;

public class BotPlayer extends Player_Impl {
    private Heatmap_Impl heatmap;
    private AttackStrategy strategy;
    private CheckboardStrategy checkboardStrategy;
    private SpiralStrategy spiralStrategy;
    private int moveCount;

    //a bot need name, board start his and of the adversare, board feedback, a shipmanager, a strategy.
    public BotPlayer(String name, BoardStart myBoard, BoardFeedback myFeedbacks, ShipManager shipManager, ShipManager opponentsShipManager) {
        super(name, myBoard, myFeedbacks, shipManager, opponentsShipManager);
        this.heatmap = new Heatmap_Impl(myFeedbacks, shipManager);
        this.checkboardStrategy = new CheckboardStrategy();
        this.spiralStrategy = new SpiralStrategy();
        this.strategy = checkboardStrategy;
        this.moveCount = 0;

        placeShip();
    }

    @Override
    public void setOpponentsBoard(BoardStart board) {
        super.setOpponentsBoard(board);
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
        System.out.println("Ships placed correctly");
    }


    // choose which one is the next move, first if there is a ship marked
    // as hitted chose an adjante to hit otherwise it use a strategy
    public int[] getNextMove() {
        changeStrategyIfNeeded();

        int[] nextMove = findAdjacentMove();
        if (nextMove != null) {
            return nextMove;
        }

        int[] strat = strategy.getNextMove(heatmap, myFeedbacks);
        nextMove = getRadarMove();

        return (nextMove != null) ? nextMove : strat;
    }

    private int[] getRadarMove() {
        for (Map.Entry<Integer, Ship_Impl> entry : shipManager.getShips().entrySet()) {
            Ship_Impl ship = entry.getValue();
            if (ship instanceof Ship_Radar) {
                return getRandomMoveInQuadrant(((Ship_Radar) ship).getQuadrant());
            }
        }
        return null;
    }

    private int[] getRandomMoveInQuadrant(int quadrant) {
        Random ran = new Random();
        int halfHeight = (int) Math.round(myBoard.getHeight() / 2.0);
        int x, y;

        switch (quadrant) {
            case 1:
                x = ran.nextInt(halfHeight);
                y = ran.nextInt(halfHeight);
                break;
            case 2:
                x = ran.nextInt(halfHeight) + halfHeight;
                y = ran.nextInt(halfHeight);
                break;
            case 3:
                x = ran.nextInt(halfHeight);
                y = ran.nextInt(halfHeight) + halfHeight;
                break;
            case 4:
                x = ran.nextInt(halfHeight) + halfHeight;
                y = ran.nextInt(halfHeight) + halfHeight;
                break;
            default:
                 return strategy.getNextMove(heatmap, myFeedbacks);
        }

        return new int[]{x, y};
    }
    //update the heat map, call getNextMove and adjour the move count,
    // attack in base which one is the next move
    public Set<ShotsFeedback> makeMove() {
        heatmap.updateheatMap();
        int[] move = getNextMove();
        moveCount++;
        Ship_Impl ship = shipManager.getShipById(shipManager.ships.entrySet().stream().findFirst().get().getKey().toString());
        return ship.attack(myFeedbacks, opponentsBoard, move[0], move[1], opponentsShipManager);
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
    //check if there are ship marked as hitted and if so check the near one, now refacotr in two method
    private int[] findAdjacentMove() {
        int width = myFeedbacks.getWidth();
        int height = myFeedbacks.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (myFeedbacks.isHit(x, y)) {
                    int[] move = findValidAdjacentMove(x, y, width, height);
                    if (move != null) {
                        return move;
                    }
                }
            }
        }
        return null; // none found
    }
    private int[] findValidAdjacentMove(int x, int y, int width, int height) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (isValidMove(newX, newY, width, height)) {
                return new int[]{newX, newY};
            }
        }
        return null;
    }

    //check if is a valid move
    private boolean isValidMove(int x, int y, int width, int height) {
        return x >= 0 && x < width && y >= 0 && y < height && !myFeedbacks.isAlreadyAttacked(x, y);
    }
}
