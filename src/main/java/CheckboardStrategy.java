public class CheckboardStrategy implements AttackStrategy{

    //attack as a "chess board", if all the "chess position" are attacked use bestmove from heat map
    @Override
    public int[] getNextMove(Heatmap_Impl heatmap, BoardFeedback board) {
        int width = board.getWidth();
        int height = board.getHeight();
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                if((x+y)%2==0 && !board.isAlreadyAttacked(x,y)){
                    return new int[]{x,y};
                }
            }
        }
        return heatmap.getBestMove(); //if all position already attacked
    }
}
