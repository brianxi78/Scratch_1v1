import java.awt.*;
public class Bullet {
    public int dx;
    public int dy;
    public int xpos;
    public int ypos;
    public int height;
;    public int width;
    public Rectangle rec;
    public Image pic;
    public boolean isShot;
    public boolean isShotL;
    public boolean hitTarget;
    public Bullet(int pXpos, int pYpos, int dxParameter, int dyParameter, Image picParameter){
        xpos = pXpos;
        ypos = pYpos;
        width = 14;
        height = 12;
        dy = dyParameter;
        dx = dxParameter;
        pic = picParameter;
        rec = new Rectangle(xpos, ypos, width, height);
        hitTarget = false;
    }
    public void move(){

        if( xpos<1000){
            xpos = xpos + dx;
        }
        if(isShot==true ){
            dx=10;
            dy=0;
        }
        if(isShotL==true){
            dx=-10;
            dy=0;
        }
        rec = new Rectangle(xpos, ypos, width, height);

    }


    }


