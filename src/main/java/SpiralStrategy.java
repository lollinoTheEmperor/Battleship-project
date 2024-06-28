public class SpiralStrategy implements AttackStrategy{
    @Override
    public int[] getNextMove(Heatmap_Impl heatmap, BoardFeedback board) {
        int width = board.getWidth();
        int height = board.getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        for (int layer = 0; layer <= Math.max(centerX, centerY); layer++) {
            for (int i = -layer; i <= layer; i++) {
                int x = centerX + i;
                int y1 = centerY + layer;
                int y2 = centerY - layer;
                if (isValidMove(x, y1, width, height, board)) return new int[]{x, y1};
                if (isValidMove(x, y2, width, height, board)) return new int[]{x, y2};
            }
            for (int j = -layer + 1; j < layer; j++) {
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
