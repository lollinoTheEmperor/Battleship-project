import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpiralStrategy implements AttackStrategy{
    @Override
    public int[] getNextMove(Heatmap_Impl heatmap, BoardFeedback board) {
        int width = board.getWidth();
        int height = board.getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        Random rand = new Random();

        for (int layer = 0; layer <= Math.max(centerX, centerY); layer++) {
            List<int[]> possibleMoves = new ArrayList<>();

            for (int i = -layer; i <= layer; i++) {
                possibleMoves.add(new int[]{centerX + i, centerY + layer});
                possibleMoves.add(new int[]{centerX + i, centerY - layer});
            }

            for (int j = -layer + 1; j <= layer - 1; j++) {
                possibleMoves.add(new int[]{centerX + layer, centerY + j});
                possibleMoves.add(new int[]{centerX - layer, centerY + j});
            }

            // Randomize the order of possible moves
            Collections.shuffle(possibleMoves, rand);

            for (int[] move : possibleMoves) {
                int x = move[0];
                int y = move[1];
                if (isValidMove(x, y, width, height, board)) {
                    return new int[]{x, y};
                }
            }
        }

        return heatmap.getBestMove(); // if all positions are already attacked
    }

    private boolean isValidMove(int x, int y, int width, int height, BoardFeedback board) {
        return x >= 0 && x < width && y >= 0 && y < height && !board.isAlreadyAttacked(x, y);
    }
}