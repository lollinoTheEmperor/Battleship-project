public class Ship_Heal extends Ship_Impl {
    final int maxHp, turnToHeal;
    private int turnUntilHel;

    public Ship_Heal(int size, String type, String id) {
        super(size, type, id);
        maxHp = size;
        turnToHeal = 2;
        turnUntilHel = -1;
    }

    /**
     * it @Override the method takeDamage in Ship_Impl to heal a damaged part
     *
     * @return a boolean to see if it is destroyed
     */
    @Override
    public boolean takeDamage() {
        if (turnUntilHel < 0)
            turnUntilHel = turnToHeal;

        return super.takeDamage();
    }

    public boolean endTurn() {
        if (turnUntilHel > 0)
            turnUntilHel--;
        return turnUntilHel == 0;
    }

    public boolean heal(BoardStart board, BoardFeedback opponentsBoardFeedback) {
        // if the first is false the second will not be done
        return recover() && updateMap(board, opponentsBoardFeedback);
    }

    public boolean recover() {
        if (this.getHp() != 0 && this.getHp() < maxHp) {
            System.out.println("Healing ship " + this.getId());
            this.setHp(this.getHp() + 1);
            turnUntilHel = -1;
            return true;
        }
        return false;
    }

    private boolean updateMap(BoardStart board, BoardFeedback opponentsBoardFeedback) {
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (board.getCell(x, y).equals(this.getId())) {
                    if (opponentsBoardFeedback.getCell(x, y).equals("hit")) {
                        System.out.println("Recovering " + board.getCell(x, y) + " at " + x + ", " + y);
                        opponentsBoardFeedback.putHealCell(x, y);
//                        this.setLastHitTurn(currentTurn);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}