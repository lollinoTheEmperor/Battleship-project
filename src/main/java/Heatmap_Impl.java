public class Heatmap_Impl implements Heatmap {
    private int[][] heatmap;
    private BoardFeedback board;

    public Heatmap_Impl(BoardFeedback board) {
        this.board = board;
        heatmap = new int[10][10];
    }
    @Override
    public void updateheatMap() {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (!(board.isAlreadyAttacked(i,j))) {
                        heatmap[i][j] = calculateProbability(i, j);
                    } else {
                        heatmap[i][j] = 0;
                    }
                }
            }
    }



    private int calculateProbability(int x, int y) {
        int probability = 0;
        int[] shipLengths = {2, 3}; // lenght ship, how can we take it???

        for (int length : shipLengths) {
            // Orizzontale e Verticale
            for (int direction = 0; direction < 2; direction++) {
                boolean possible = true;
                for (int k = 0; k < length; k++) {
                    int newX = direction == 0 ? x + k : x;
                    int newY = direction == 1 ? y + k : y;
                    if (newX >= 10 || newY >= 10 || board.isAlreadyAttacked(newX,newY)) {
                        possible = false;
                        break;
                    }
                }
                if (possible) probability++;
            }
        }
        // Increase probability around previous hits that have not sunk ships
        if (board.isHit(x, y)) {
            probability += 10; // Arbitrary increment of probability
            if (x > 0 && !board.isAlreadyAttacked(x - 1, y)) probability += 5;
            if (x < board.getWidth() - 1 && !board.isAlreadyAttacked(x + 1, y)) probability += 5;
            if (y > 0 && !board.isAlreadyAttacked(x, y - 1)) probability += 5;
            if (y < board.getHeight() - 1 && !board.isAlreadyAttacked(x, y + 1)) probability += 5;
        }

        if (probability == 0 && !board.isAlreadyAttacked(x, y)) {
            probability = 1;
        }
        return probability;
    }

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
    public int getHeatmapValue(int x, int y) {
        return heatmap[x][y];
    }
}
