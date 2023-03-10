import java.awt.*;
public class Boxes {
    public int xpos;
    public int ypos;
    public int width;
    public int height;
    public Rectangle rec;
    public Boxes(int pXpos, int pYpos){
        xpos = pXpos;
        ypos = pYpos;
        width = 100;
        height = 100;
        rec = new Rectangle(xpos, ypos, width, height);
    }
}
