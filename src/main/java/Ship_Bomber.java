import java.util.*;

//this ship can shoot a 2x2, the input coordinate is an edge picked randomly and can shoot only once every 3 turns
public class Ship_Bomber extends Ship_Impl{
    public Ship_Bomber(int size, String type, String id){super(size, type, id);}

    //it attacks an area, the player doesn't have the full control over it
    //it can attack cells already attacked
    @Override
    public Set<ShotsFeedback> attack (BoardFeedback myFeedbacks, BoardStart opponentsBoard, int x, int y, ShipManager shipManager){
        Random rand = new Random();
        int edge=rand.nextInt(4);
        boolean done=false;
        Set<ShotsFeedback> attacks = new HashSet<>();
        int xa,ya,xb,yb,xc,yc,xd,yd;
        int check=0;
        while (!done){
            if (check>=4){
                System.out.println("Coordinates not valid");
                return null;
            }
            switch (edge){
                case 0:
                    xa = x;
                    ya = y;
                    xb = x+1;
                    yb = y;
                    xc = x+1;
                    yc = y+1;
                    xd = x;
                    yd = y+1;
                    if (myFeedbacks.areValidCoordinates(xa,ya) && myFeedbacks.areValidCoordinates(xb,yb) && myFeedbacks.areValidCoordinates(xc,yc) && myFeedbacks.areValidCoordinates(xd,yd)){
                        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, xa, ya, shipManager));
                        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, xb, yb, shipManager));
                        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, xc, yc, shipManager));
                        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, xd, yd, shipManager));
                        return attacks;
                    }else {
                        edge++;
                        check++;
                    }
                    break;
                case 1:
                    xa = x-1;
                    ya = y;
                    xb = x;
                    yb = y;
                    xc = x;
                    yc = y+1;
                    xd = x-1;
                    yd = y-1;
                    if (myFeedbacks.areValidCoordinates(xa,ya) && myFeedbacks.areValidCoordinates(xb,yb) && myFeedbacks.areValidCoordinates(xc,yc) && myFeedbacks.areValidCoordinates(xd,yd)){
                        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, xa, ya, shipManager));
                        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, xb, yb, shipManager));
                        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, xc, yc, shipManager));
                        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, xd, yd, shipManager));
                        return attacks;
                    }else {
                        edge++;
                        check++;
                    }
                    break;
                case 2:
                    xa = x-1;
                    ya = y-1;
                    xb = x;
                    yb = y-1;
                    xc = x;
                    yc = y;
                    xd = x-1;
                    yd = y;
                    if (myFeedbacks.areValidCoordinates(xa,ya) && myFeedbacks.areValidCoordinates(xb,yb) && myFeedbacks.areValidCoordinates(xc,yc) && myFeedbacks.areValidCoordinates(xd,yd)){
                        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, xa, ya, shipManager));
                        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, xb, yb, shipManager));
                        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, xc, yc, shipManager));
                        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, xd, yd, shipManager));
                        return attacks;
                    }else {
                        edge++;
                        check++;
                    }
                    break;
                case 3:
                    xa = x;
                    ya = y-1;
                    xb = x+1;
                    yb = y+1;
                    xc = x+1;
                    yc = y;
                    xd = x;
                    yd = y;
                    if (myFeedbacks.areValidCoordinates(xa,ya) && myFeedbacks.areValidCoordinates(xb,yb) && myFeedbacks.areValidCoordinates(xc,yc) && myFeedbacks.areValidCoordinates(xd,yd)){
                        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, xa, ya, shipManager));
                        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, xb, yb, shipManager));
                        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, xc, yc, shipManager));
                        attacks.addAll(super.attack(myFeedbacks, opponentsBoard, xd, yd, shipManager));
                        return attacks;
                    }else {
                        edge=0;
                        check++;
                    }
                    break;
            }
        }
        return null;
    }
}
