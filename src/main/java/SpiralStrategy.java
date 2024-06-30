import java.util.Random;

public class SpiralStrategy implements AttackStrategy{
    @Override
    public int[] getNextMove(Heatmap_Impl heatmap, BoardFeedback board) {
        int width = board.getWidth();
        int height = board.getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        Random rand = new Random();

        for (int layer = 0; layer <= Math.max(centerX, centerY); layer++) {
            // Randomize the order of moves
            int[] indices = rand.ints(-layer, layer + 1).distinct().limit(2 * layer + 1).toArray();

            for (int i : indices) {
                int x = centerX + i;
                int y1 = centerY + layer;
                int y2 = centerY - layer;
                if (isValidMove(x, y1, width, height, board)) return new int[]{x, y1};
                if (isValidMove(x, y2, width, height, board)) return new int[]{x, y2};
            }

            indices = rand.ints(-layer + 1, layer).distinct().limit(2 * layer - 1).toArray();

            for (int j : indices) {
                int y = centerY + j;
                int x1 = centerX + layer;
                int x2 = centerX - layer;
                if (isValidMove(x1, y, width, height, board)) return new int[]{x1, y};
                if (isValidMove(x2, y, width, height, board)) return new int[]{x2, y};
            }
        }

        return heatmap.getBestMove(); // if position already attacked
    }

    private boolean isValidMove(int x, int y, int width, int height, BoardFeedback board) {
        return x >= 0 && x < width && y >= 0 && y < height && !board.isAlreadyAttacked(x, y);
    }
}
