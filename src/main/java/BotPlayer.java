public class BotPlayer extends Player_Impl {
    private Heatmap_Impl heatmap;

    public BotPlayer(String name, BoardStart myBoard, BoardStart opponentsBoard, BoardFeedback myFeedbacks, ShipManager shipManager) {
        super(name, myBoard, myFeedbacks, shipManager);
        this.heatmap = new Heatmap_Impl(myFeedbacks, shipManager);
        this.setOpponentsBoard(opponentsBoard);
    }

    public void updateHeatmap() {
        heatmap.updateheatMap();
    }

    public int[] getNextMove() {
        return heatmap.getBestMove();
    }

    public boolean makeMove() {
        int[] move = getNextMove();
        return attack(move[0], move[1]);
    }
}
