public class Ship_Radar extends Ship_Impl{

    BoardStart opponentsBoard;
    public Ship_Radar(int size, String type, String id, BoardStart opponentsBoard) {
        super(size, type, id);
        this.opponentsBoard = opponentsBoard;
    }

    public void mapRev() {

        int width = opponentsBoard.getWidth();
        int height = opponentsBoard.getHeight();

        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;
        int counter4 = 0;

        for (int i = 0; i < ((int) Math.round(width / 2.0)); i++) {

            for (int x = 0; x < ((int) Math.round(height / 2.0)); x++) {
                if(!opponentsBoard.getCell(i, x).equals("Water")) {
                    counter1++;
                }
            }
        }

        for (int i = width / 2; i < width; i++) {

            for (int x = height / 2; x < height; x++) {
                if(!opponentsBoard.getCell(i, x).equals("Water")) {
                    counter4++;
                }
            }
        }


        for (int i = width / 2; i < width; i++) {

            for (int x = 0; x < ((int) Math.round(height / 2.0)); x++) {
                if(!opponentsBoard.getCell(i, x).equals("Water")) {
                    counter2++;
                }
            }
        }


        for (int i = 0; i < ((int) Math.round(width / 2.0)); i++) {

            for (int x = height / 2; x < height; x++) {
                if(!opponentsBoard.getCell(i, x).equals("Water")) {
                    counter3++;
                }
            }
        }
        

        if (counter1 >= counter2 && counter1 >= counter3 && counter1 >= counter4) {
            for (int i = 0; i < ((int) Math.round(width / 2.0)); i++) {

                for (int x = 0; x < ((int) Math.round(height / 2.0)); x++) {
                        opponentsBoard.board_Game[i][x] = "Hotzone";
                    }
                }
        } else if (counter2 >= counter1 && counter2 >= counter3 && counter2 >= counter4) {
            for (int i = width / 2; i < width; i++) {

                for (int x = 0; x < ((int) Math.round(height / 2.0)); x++) {
                    opponentsBoard.board_Game[i][x] = "Hotzone";
                }
            }
            
        } else if (counter3 >= counter1 && counter3 >= counter2 && counter3 >= counter4) {
            for (int i = 0; i < ((int) Math.round(width / 2.0)); i++) {

                for (int x = height / 2; x < height; x++) {
                    opponentsBoard.board_Game[i][x] = "Hotzone";
                }
            }
            
        } else if (counter4 >= counter1 && counter4 >= counter2 && counter4 >= counter3) {
            
            for (int i = width / 2; i < width; i++) {

                for (int x = height / 2; x < height; x++) {
                    opponentsBoard.board_Game[i][x] = "Hotzone";
                }
            }
        }
    }
}



    

