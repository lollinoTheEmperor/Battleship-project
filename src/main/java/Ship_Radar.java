public class Ship_Radar extends Ship_Impl{

    BoardStart board;
    int quadrant;
    public Ship_Radar(int size, String type, String id, BoardStart board) {
        super(size, type, id);
        this.board = board;
        quadrant = 0;
    }

    public void mapRev(BoardStart boardwithShip) {

        int width = boardwithShip.getHeight();
        int height = boardwithShip.getHeight();

        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;
        int counter4 = 0;


        for (int i = 0; i < ((int) Math.round(width / 2.0)); i++) {

            for (int x = 0; x < ((int) Math.round(height / 2.0)); x++) {
                if(!boardwithShip.getCell(i, x).equals("water")) {
                    counter1++;
                }
            }
        }

        for (int i = width / 2; i < width; i++) {

            for (int x = height / 2; x < height; x++) {
                if(!boardwithShip.getCell(i, x).equals("water")) {
                    counter4++;
                }
            }
        }


        for (int i = width / 2; i < width; i++) {

            for (int x = 0; x < ((int) Math.round(height / 2.0)); x++) {
                if(!boardwithShip.getCell(i, x).equals("water")) {
                    counter2++;
                }
            }
        }


        for (int i = 0; i < ((int) Math.round(width / 2.0)); i++) {

            for (int x = height / 2; x < height; x++) {
                if(!boardwithShip.getCell(i, x).equals("water")) {
                    counter3++;
                }
            }
        }
        
       

        if(counter1 == 0 && counter4 == 0 && counter3 == 0 && counter2 == 0 ){
            System.out.println("No Ships");
        }
        if (counter1 >= counter2 && counter1 >= counter3 && counter1 >= counter4) {
            System.out.println("The top left quadrant has the most ships!");
            quadrant = 1;
        } else if (counter2 >= counter1 && counter2 >= counter3 && counter2 >= counter4) {
            System.out.println("The top right quadrant has the most ships!");
            quadrant = 2;
            
        } else if (counter3 >= counter1 && counter3 >= counter2 && counter3 >= counter4) {
            System.out.println("The bottom left quadrant has the most ships!");
            quadrant = 3;
            
        } else if (counter4 >= counter1 && counter4 >= counter2 && counter4 >= counter3) {
            System.out.println("The bottom right quadrant has the most ships!");
            quadrant = 4;
        }
    }

    public BoardStart getBoard() {
        return board;
    }

    public int getQuadrant() {
        return quadrant;
    }
}



    

