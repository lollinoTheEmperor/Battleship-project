import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpiralStrategy implements AttackStrategy{

   //get next move to attack in a sprial way
    @Override
    public int[] getNextMove(Heatmap_Impl heatmap, BoardFeedback board) {
        int width = board.getWidth();
        int height = board.getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        for (int layer = 0; layer <= Math.max(centerX, centerY); layer++) {
            List<int[]> possibleMoves = generatePossibleMoves(layer, centerX, centerY);
            Collections.shuffle(possibleMoves, new Random());

            for (int[] move : possibleMoves) {
                if (isValidMove(move[0], move[1], width, height, board)) {
                    return move;
                }
            }
        }

        return heatmap.getBestMove(); // if all positions are already attacked
    }
//generete all the possible move of the spiral
    private List<int[]> generatePossibleMoves(int layer, int centerX, int centerY) {
        List<int[]> possibleMoves = new ArrayList<>();

        for (int i = -layer; i <= layer; i++) {
            possibleMoves.add(new int[]{centerX + i, centerY + layer});
            possibleMoves.add(new int[]{centerX + i, centerY - layer});
        }

        for (int j = -layer + 1; j <= layer - 1; j++) {
            possibleMoves.add(new int[]{centerX + layer, centerY + j});
            possibleMoves.add(new int[]{centerX - layer, centerY + j});
        }

        return possibleMoves;
    }
//check if is a valid move
    private boolean isValidMove(int x, int y, int width, int height, BoardFeedback board) {
        return x >= 0 && x < width && y >= 0 && y < height && !board.isAlreadyAttacked(x, y);
    }
}