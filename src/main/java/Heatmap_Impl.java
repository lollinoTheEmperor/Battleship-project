public class Heatmap_Impl implements Heatmap {
    private int[][] heatmap;
    private BoardFeedback board;
    private ShipManager shipman;
    //need a board feedback, a ship manager
    public Heatmap_Impl(BoardFeedback board, ShipManager shipman) {
        this.board = board;
        heatmap = new int[board.width][board.height];
        this.shipman=shipman;
    }

    //for every cell it recount the probability that there is a cell, used only if the strategis are all complete
    @Override
    public void updateheatMap() {
        int width = board.width;
        int height = board.height;
        // Update heatmap probabilities
        updateHeatMapProbabilities(width, height);
        // Adjust heatmap based on board state
        adjustHeatMapBasedOnBoardState(width, height);
    }

    private void updateHeatMapProbabilities(int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                heatmap[i][j] = calculateProbability(i, j);
            }
        }
    }

    private void adjustHeatMapBasedOnBoardState(int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (board.isAlreadyAttacked(i, j)) {
                    heatmap[i][j] = 0;
                } else if (board.isHit(i, j)) {
                    increaseAdjacentProbabilities(i, j);
                }
            }
        }
    }



    private int calculateProbability(int x, int y) {
        if (board.isAlreadyAttacked(x, y)) {
            return 1;  // Return minimum probability if already attacked
        }

        int probability = 0;
        int width = board.width;
        int height = board.height;

        for (Ship_Impl ship : shipman.ships.values()) {
            int length = ship.getSize();
            probability += checkDirection(x, y, length, width, height, true);  // Horizontal direction
            probability += checkDirection(x, y, length, width, height, false); // Vertical direction
        }

        return probability > 0 ? probability : 1; // Ensure minimum probability is 1
    }

    private int checkDirection(int x, int y, int length, int width, int height, boolean isHorizontal) {
        for (int k = 0; k < length; k++) {
            int newX = isHorizontal ? x + k : x;
            int newY = isHorizontal ? y : y + k;

            if (newX >= width || newY >= height || board.isAlreadyAttacked(newX, newY)) {
                return 0; // Not possible to place ship in this direction
            }
        }
        return 1; // Possible to place ship in this direction
    }

    private void increaseAdjacentProbabilities(int x, int y) {
        if (x > 0 && !board.isAlreadyAttacked(x - 1, y)) heatmap[x - 1][y] += 5;
        if (x < board.getWidth() - 1 && !board.isAlreadyAttacked(x + 1, y)) heatmap[x + 1][y] += 5;
        if (y > 0 && !board.isAlreadyAttacked(x, y - 1)) heatmap[x][y - 1] += 5;
        if (y < board.getHeight() - 1 && !board.isAlreadyAttacked(x, y + 1)) heatmap[x][y + 1] += 5;
    }

    //take the cell with the highest probability
    @Override
    public int[] getBestMove() {
        int bestX = 0;
        int bestY = 0;
        int maxProbability = 0;

        for (int i = 0; i < heatmap.length; i++) {
            for (int j = 0; j < heatmap[i].length; j++) {
                if (heatmap[i][j] > maxProbability) {
                    bestX = i;
                    bestY = j;
                    maxProbability = heatmap[i][j];
                }
            }
        }
        return new int[]{bestX, bestY};
    }
    //only used for the test
    protected int getHeatmapValue(int x, int y) {
        return heatmap[x][y];
    }
}