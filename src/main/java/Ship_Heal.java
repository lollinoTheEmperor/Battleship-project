import java.util.*;


public class Ship_Heal extends Ship_Impl {
    final int maxHp;
    private int lastHitTurn;
    public Ship_Heal(int size, String type, String id) {
        super(size, type, id);
        maxHp = size;
        this.lastHitTurn = -1;
    }

    /**
     * it @Override the method takeDamage in Ship_Impl to heal a damaged part if is not hit
     *
     * @return a boolean to see if it is destroyed
     */
    public int getLastHitTurn() {
        return lastHitTurn;
    }

    public void setLastHitTurn(int lastHitTurn) {
        this.lastHitTurn = lastHitTurn;
    }

    public boolean heal(BoardStart board, BoardFeedback opponentsBoardFeedback, int currentTurn) {
        return recover(currentTurn) && updateMap(board, opponentsBoardFeedback, currentTurn);
    }

    public boolean recover(int currentTurn) {
        if (this.getHp() != 0 && this.getHp() < maxHp && !isHitRecently(currentTurn, 2)) {
            System.out.println("Healing ship " + this.getId());
            this.setHp(this.getHp() + 1);
            return true;
        }
        return false;
    }

    private boolean updateMap(BoardStart board, BoardFeedback opponentsBoardFeedback, int currentTurn) {
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (board.getCell(x, y).equals(this.getId())) {
                    if (opponentsBoardFeedback.getCell(x, y).equals("hit")) {
                        System.out.println("Recovering " + board.getCell(x, y) + " at " + x + ", " + y);
                        opponentsBoardFeedback.healCell(x, y);
                        this.setLastHitTurn(currentTurn);
                        return true;
                    }
                }
            }
        }
        return false;
    }

//
//        String cellContent = boardWithShip.getCell(x, y);
//        if ("water".equals(cellContent)) {
//            board_Game[x][y] = "miss";
//            return false;
//        } else if ("hit".equals(board_Game[x][y]) || "miss".equals(board_Game[x][y]) || "destructed".equals(board_Game[x][y])) {
//            System.out.println("Cell already attacked");
//            return true;
//        }  else {
//            board_Game[x][y] = "hit";
//            Ship_Impl shipHitted = allship.getShipById(cellContent);
//            //if take damage, call the method to take an hp, and if
//            // it's destructed (so th method return true) remove the
//            // ship from the ship manage, and putt destructed where
//            // there was the id
//            if(shipHitted.takeDamage()){
//                allship.removeShipById(shipHitted.getId());
//                markShipDestructed(shipHitted);
//                boardWithShip.markShipDestructed(shipHitted);
//            };
//            return true;
//        }

    public boolean isHitRecently(int currentTurn, int turnsForHealing) {
        return (currentTurn - lastHitTurn) < turnsForHealing;
    }
}