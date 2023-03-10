
import java.awt.*;

public class Player {


    public int xpos;                //the x position
    public double ypos;                //the y position
    public int width; 
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public int dx;                    //the speed of the hero in the x direction
    public double dy;                    //the speed of the hero in the y direction
    public Rectangle rec;
    public Image pic;

    //movement booleans
    public boolean right;
    public boolean left;
    public boolean down;
    public boolean up;
    public boolean FacingL;
    public boolean isJumping;


    public Player(int pXpos, double pYpos, int dxParameter, double dyParameter) {

        xpos = pXpos;
        ypos = pYpos;
        width = 125;
        height = 125;
        dx = dxParameter;
        dy = dyParameter;
        isAlive = true;
        rec = new Rectangle(xpos, (int)ypos, width, height);
        FacingL = true;

    } // constructor

    //move( ) method for a keyboard controlled character
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if(right == true){
           dx = 5;
        } else if (left == true) {
            dx = -5;
        } else { // (right == false && left == false)
            dx = 0;
        }

//        if(down == true){
//            dy = 5;
//        } else if (up == true) {
//            dy = dy-0.1;
//        } else {
//            dy = 0;
//        }

        if(xpos>1000-width){ // right
            xpos = 1000-width;
        }
        if(xpos < 0) { // left
            xpos = 0;
        }
        if(ypos>650-height){ // down
            ypos = 650-height;
        }
        if(ypos < 0) { // up
            ypos = 0;
        }
        if(isJumping == true ){
            dy = dy + .5 ;
        }


        //always put this after you've done all the changing of the xpos and ypos values
        rec = new Rectangle(xpos, (int)ypos, width, height);

    }

    public void move2() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (ypos < 250) {
            dy = dy + 1;
        }
        if (ypos > 250) {
            ypos = 250;
        }

        if(xpos>1000-width){ // right
            xpos = 1000-width;
        }
        if(xpos < 0) { // left
            xpos = 0;
        }
        if(ypos>650-height){ // down
            ypos = 650-height;
        }
        if(ypos < 0) { // up
            ypos = 0;
        }
    }

}
