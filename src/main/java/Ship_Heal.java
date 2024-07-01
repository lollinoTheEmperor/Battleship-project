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

    /**
     * Called in Main to determine the end of a turn and activate the healing process
     * @return true if turns passed are 2
     */
    public boolean endTurn() {
        if (turnUntilHel > 0)
            turnUntilHel--;
        return turnUntilHel == 0;
    }

    /**
     *  heal core process
     */
    public void heal(BoardStart board, BoardFeedback opponentsBoardFeedback) {
        if (recover()) {
            updateMap(board, opponentsBoardFeedback);
        }
    }

    /**
     * If the ship is still alive and is damaged, heals it for one unit
     * @return boolean (if returns false, the method updateMap will not be called)
     */
    private boolean recover() {
        if (this.getHp() != 0 && this.getHp() < maxHp) {
            System.out.println("Healing ship " + this.getId());
            this.setHp(this.getHp() + 1);
            turnUntilHel = -1;
            return true;
        }
        return false;
    }

    /**
     * Update the feedback board in order to visualize it on the visualBoard
     */
    private void updateMap(BoardStart board, BoardFeedback opponentsBoardFeedback) {
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (board.getCell(x, y).equals(this.getId())) {
                    if (opponentsBoardFeedback.getCell(x, y).equals("hit")) {
                        System.out.println("Recovering " + board.getCell(x, y) + " at " + x + ", " + y);
                        opponentsBoardFeedback.putHealCell(x, y);
                        return;
                    }
                }
            }
        }
    }
}