import java.awt.*;
public class Boxes {
    public int xpos;
    public int ypos;
    public int width;
    public int height;
    public Rectangle Lrec;
    public Rectangle Rrec;
    public Rectangle Trec;
    public int dy;
    public Image pic;

    public Boxes(int pXpos, int pYpos, int dxParameter, int dyParameter, Image picParameter){
        xpos = pXpos;
        ypos = pYpos;
        width = 100;
        height = width;
        dy = dyParameter;
        pic = picParameter;
        Lrec = new Rectangle(xpos, ypos+20, 1, height-20);
        Rrec = new Rectangle(xpos+width-1, ypos+20, 1, height-20);
        Trec = new Rectangle(xpos, ypos, width, 1);

    }
}
