public class ShotsFeedback {
    public boolean hit;
    public int x, y;

    /**
     * Used to return a set of this object, in order to have a feedback for each attack using hit and x,y)
     * @param hit
     * @param x
     * @param y
     */
    public ShotsFeedback(boolean hit, int x, int y) {
        this.hit = hit;
        this.x = x;
        this.y = y;
    }
}