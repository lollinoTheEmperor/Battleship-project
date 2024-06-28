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
        this.strategy = checkboardStrategy; // Strategia iniziale
        this.moveCount = 0;
    }

    public void updateHeatmap() {
        heatmap.updateheatMap();
    }

    public int[] getNextMove() {
        changeStrategyIfNeeded();
        return strategy.getNextMove(heatmap, myFeedbacks);
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
}
