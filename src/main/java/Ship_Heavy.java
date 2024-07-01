import java.util.*;


public class Ship_Heavy extends Ship_Impl {

    public Ship_Heavy(int size, String type, String id) {
        super(size, type, id);
    }

    /**
     * it @Override the method attack in Ship_Impl and perform an extra attack with a random spread
     *
     * @return a set of hits
     */
    @Override
    public Set<ShotsFeedback> attack(BoardFeedback myFeedbacks, BoardStart opponentsBoard, int x, int y, ShipManager shipManager) {

        Set<ShotsFeedback> attacks = new HashSet<>();
        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, x, y, shipManager));

        // + second not aimed attack
        int secondX, secondY;
        String cellState;

        // Crea una lista di tutte le possibili caselle attorno al punto (x, y)
        List<int[]> possibleMoves = new ArrayList<>();
        if (x > 0) possibleMoves.add(new int[]{x - 1, y});
        if (x < opponentsBoard.width - 1) possibleMoves.add(new int[]{x + 1, y});
        if (y > 0) possibleMoves.add(new int[]{x, y - 1});
        if (y < opponentsBoard.height - 1) possibleMoves.add(new int[]{x, y + 1});
        if (x > 0 && y > 0) possibleMoves.add(new int[]{x - 1, y - 1});
        if (x < opponentsBoard.width - 1 && y > 0) possibleMoves.add(new int[]{x + 1, y - 1});
        if (x > 0 && y < opponentsBoard.height - 1) possibleMoves.add(new int[]{x - 1, y + 1});
        if (x < opponentsBoard.width - 1 && y < opponentsBoard.height - 1) possibleMoves.add(new int[]{x + 1, y + 1});

        // Create a list and order it in random way
        Collections.shuffle(possibleMoves);

        for (int[] move : possibleMoves) {
            secondX = move[0];
            secondY = move[1];

            cellState = myFeedbacks.getCell(secondX, secondY);

            if (!cellState.equals("hit") && !cellState.equals("miss") && !cellState.equals("destructed")) {

                attacks.addAll(super.attack(myFeedbacks, opponentsBoard, secondX, secondY, shipManager));
                return attacks;
            }
        }
        return attacks;
    }

}