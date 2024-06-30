import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

public class CheckboardStrategy implements AttackStrategy{

    //attack as a "chess board", if all the "chess position" are attacked use bestmove from heat map
    private Random random = new Random();

    @Override
    public int[] getNextMove(Heatmap_Impl heatmap, BoardFeedback board) {
        int width = board.getWidth();
        int height = board.getHeight();

        List<int[]> validPositions = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if ((x + y) % 2 == 0) {
                    validPositions.add(new int[]{x, y});
                }
            }
        }

        Collections.shuffle(validPositions, random);

        for (int[] pos : validPositions) {
            if (!board.isAlreadyAttacked(pos[0], pos[1])) {
                return pos;
            }
        }

        return heatmap.getBestMove(); // if all positions are already attacked
    }
}

