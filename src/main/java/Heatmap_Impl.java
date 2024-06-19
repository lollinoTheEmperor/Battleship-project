public class Heatmap_Impl implements Heatmap {
    private int[][] heatmap;
    private BoardFeedback board;
    private ShipManager shipman;
    public Heatmap_Impl(BoardFeedback board, ShipManager shipman) {
        this.board = board;
        heatmap = new int[10][10];
        this.shipman=shipman;
    }

    @Override
    public void updateheatMap() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                heatmap[i][j] = calculateProbability(i, j);
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.isAlreadyAttacked(i, j)) {
                    heatmap[i][j] = 0;
                }
                if (board.isHit(i, j)) {
                    increaseAdjacentProbabilities(i, j);
                }
            }
        }
    }



    private int calculateProbability(int x, int y) {
        int probability = 0;

        if (!board.isAlreadyAttacked(x, y)) {
            for (Ship_Impl ship : shipman.ships.values()) {
                int length = ship.getSize();
                for (int direction = 0; direction < 2; direction++) {
                    boolean possible = true;
                    for (int k = 0; k < length; k++) {
                        int newX = direction == 0 ? x + k : x;
                        int newY = direction == 1 ? y + k : y;
                        if (newX >= 10 || newY >= 10 || board.isAlreadyAttacked(newX, newY)) {
                            possible = false;
                            break;
                        }
                    }
                    if (possible) probability++;
                }
            }
        }

        return probability > 0 ? probability : 1;
    }

    private void increaseAdjacentProbabilities(int x, int y) {
        if (x > 0 && !board.isAlreadyAttacked(x - 1, y)) heatmap[x - 1][y] += 5;
        if (x < board.getWidth() - 1 && !board.isAlreadyAttacked(x + 1, y)) heatmap[x + 1][y] += 5;
        if (y > 0 && !board.isAlreadyAttacked(x, y - 1)) heatmap[x][y - 1] += 5;
        if (y < board.getHeight() - 1 && !board.isAlreadyAttacked(x, y + 1)) heatmap[x][y + 1] += 5;
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