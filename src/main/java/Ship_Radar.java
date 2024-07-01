import java.util.Random;
public class Ship_Radar extends Ship_Impl{

    BoardStart board;
    int quadrant;
    public Ship_Radar(int size, String type, String id, BoardStart board) {
        super(size, type, id);
        this.board = board;
        quadrant = 0;
    }

    //loops through the quadrants to see which one is the most "populated" one by ships (as in individual squares not a complete ship)
    public void mapRev(BoardStart boardwithShip, BoardFeedback boardFeedback) {

        int width = boardwithShip.getHeight();
        int height = boardwithShip.getHeight();

        int counter1 = quadrantChecker(1, width, height, boardFeedback, boardwithShip);
        int counter2 = quadrantChecker(2, width, height, boardFeedback, boardwithShip);
        int counter3 = quadrantChecker(3, width, height, boardFeedback, boardwithShip);
        int counter4 = quadrantChecker(4, width, height, boardFeedback, boardwithShip);
    
        Random random = new Random();

        if(counter1 == 0 && counter2 == 0 && counter3 == 0 && counter4 == 0) {
            System.out.println("No Ships");
        } else {
            if (counter1 == counter2 && counter2 == counter3 && counter3 == counter4) {
                quadrant = random.nextInt(4) + 1;
            } else {
                int maxCounter = Math.max(Math.max(counter1, counter2), Math.max(counter3, counter4));
                if (maxCounter == counter1) {
                    System.out.println("The top left quadrant has the most ships!");
                    quadrant = 1;
                } else if (maxCounter == counter2) {
                    System.out.println("The top right quadrant has the most ships!");
                    quadrant = 2;
                } else if (maxCounter == counter3) {
                    System.out.println("The bottom left quadrant has the most ships!");
                    quadrant = 3;
                } else if (maxCounter == counter4) {
                    System.out.println("The bottom right quadrant has the most ships!");
                    quadrant = 4;
                }
            }
            }

            switch (quadrant) {
                case 1:
                    markHotzone(0, (int) Math.round(width / 2.0), 0, (int) Math.round(height / 2.0), boardFeedback);
                    break;
                case 2:
                    markHotzone((int) Math.round(width / 2.0), width, 0, (int) Math.round(height / 2.0), boardFeedback);
                    break;
                case 3:
                    markHotzone(0, (int) Math.round(width / 2.0), (int) Math.round(height / 2.0), height, boardFeedback);
                    break;
                case 4:
                    markHotzone((int) Math.round(width / 2.0), width, (int) Math.round(height / 2.0), height, boardFeedback);
                    break;
            }
        }


    private int getCounter(BoardStart boardwithShip, int i, int x, BoardFeedback boardFeedback) {
        if(!boardwithShip.getCell(i, x).equals("water") &&
                                                      (boardFeedback.getCell(i, x).equals("water")
                                                      || boardFeedback.getCell(i, x).equals("hotzone")
                                                      || boardFeedback.getCell(i, x).equals("heal")) ) {
            return 1;
        }
        return 0;
    }


    private void markHotzone(int xStart, int xEnd, int yStart, int yEnd, BoardFeedback boardFeedback) {
        for(int x = xStart; x < xEnd; x++) {
            for(int y = yStart; y < yEnd; y++) {
                if(boardFeedback.getCell(x, y).equals("water")) {
                    boardFeedback.puyHotzoneCell(x, y);
                }
            }
        }
    }

    public BoardStart getBoard() {
        return board;
    }

    public int getQuadrant() {
        return quadrant;
    }

    //updates the correct counter and returns it
    public int quadrantChecker(int quadrant, int width, int height, BoardFeedback boardFeedback, BoardStart boardwithShip) {

        int counter = 0;

        switch (quadrant) {
            case 1:
                for (int i = 0; i < ((int) Math.round(width / 2.0)); i++) {
                    for (int x = 0; x < ((int) Math.round(height / 2.0)); x++) {
                        counter += getCounter(boardwithShip, i, x, boardFeedback) ;
                    }
                }
                break;
    
            case 2:
                for (int i = width / 2; i < width; i++) {
                    for (int x = 0; x < ((int) Math.round(height / 2.0)); x++) {
                        counter += getCounter(boardwithShip, i, x, boardFeedback);
                    }
                }
                break;
    
            case 3:
                for (int i = 0; i < ((int) Math.round(width / 2.0)); i++) {
                    for (int x = height / 2; x < height; x++) {
                        counter += getCounter(boardwithShip, i, x, boardFeedback);
                    }
                }
                break;
    
            case 4:
                for (int i = width / 2; i < width; i++) {
                    for (int x = height / 2; x < height; x++) {
                        counter += getCounter(boardwithShip, i, x, boardFeedback);
                    }
                }
                break;
    
            default:
                throw new IllegalArgumentException("Invalid quadrant: " + quadrant);
        }
        return counter;
    }



}


    

