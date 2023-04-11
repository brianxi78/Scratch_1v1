//K.  Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import sun.awt.HToolkit;
import sun.lwawt.macosx.CPrinterDevice;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;

/***
 * Step 0 for keyboard control - Import
 */
import java.awt.event.*;

/***
 * Step 1 for keyboard control - implements KeyListener
 */
public class Scratch1v1 implements Runnable, KeyListener {

    //Variable Definition Section

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    //Declare the variables needed for images
    public Image ChugJugPic;
    public Image user2Pic;
    public Image tomPic;
    public Image left1;
    public Image left2;
    public Image right1;
    public Image right2;
    public Image background;
    public Image boxPic;
    public Image bulletPic;

    //Declare the character objects
    public Player2 user2;
//    public Cheese ChugJug;
    public Cheese[] Chug;
    public Player user;
    public Boxes[] Box;
    public Bullet[] Bb;
    public Bullet[] Bb2;
   public  int bulletCounter=0;
   public  int bulletCounter2 = 0;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        Scratch1v1 myApp = new Scratch1v1();   //creates a new instance of the game
        new Thread(myApp).start();               //creates a threads & starts up the code in the run( ) method
    }

    // Constructor Method - setup portion of the program
    // Initialize your variables and construct your program objects here.
    public Scratch1v1() {

        setUpGraphics();

        /***
         * Step 2 for keyboard control - addKeyListener(this) to the canvas
         */
        canvas.addKeyListener(this);

        //load images
        ChugJugPic = Toolkit.getDefaultToolkit().getImage("Chug Jug.png");
        user2Pic = Toolkit.getDefaultToolkit().getImage("jerry.gif");
        tomPic = Toolkit.getDefaultToolkit().getImage("tomCat.png");
        left1 = Toolkit.getDefaultToolkit().getImage("User 1 Shooting Left.png");
        left2 = Toolkit.getDefaultToolkit().getImage("User 2 Shooting Left.png");
        right1 = Toolkit.getDefaultToolkit().getImage("User 1 Shooting Right.png");
        right2 = Toolkit.getDefaultToolkit().getImage("User 2 Shooting Right.png");
        background = Toolkit.getDefaultToolkit().getImage("Background.png");
        boxPic = Toolkit.getDefaultToolkit().getImage("Box.png");
        bulletPic = Toolkit.getDefaultToolkit().getImage("BB_Pellet.png");

        //create (construct) the objects needed for the game
        user2 = new Player2(200, 600, 0, 0);
//        ChugJug = new Cheese(400, 300, 3, -4, ChugJugPic);
        user = new Player(250, 600, 0, 0);
        Chug = new Cheese[5];
        for(int i = 0; i<Chug.length; i++){
            Chug[i] = new Cheese((int)(Math.random()*800),(int)(Math.random()*600), 3, 3, ChugJugPic);
        }
        Box = new Boxes[2];
        for(int i = 0; i<Box.length; i++){
            Box[i] = new Boxes((int)(Math.random()*800), 600-89, 0,0,boxPic);
        }
        Bb = new Bullet[2000];
        for(int i = 0; i<Bb.length;i++){
            Bb[i] = new Bullet(2000,2000,0,0,bulletPic);
        }
        Bb2 = new Bullet[2000];
        for(int i = 0; i<Bb2.length;i++){
            Bb2[i] = new Bullet(2000,2000,0,0,bulletPic);
        }

    } // CheeseWorld()


//*******************************************************************************
//User Method Section

    // main thread
    // this is the code that plays the game after you set things up
    public void moveThings() {
        user2.move();
//        ChugJug.move();
        user.move();
        for(int i=0; i<Chug.length; i++){
            Chug[i].move();
        }
        if(user.ypos>620-user.height){
            user.ypos = 620- user.height;
        }
        if(user2.ypos>620-user2.height){
            user2.ypos = 620- user2.height;
        }
       /* for(int i = 0; i < Bb.length; i++){
            if(user.isShooting == true){
                Bb[i].isShot = true;
                // Bb[bulletCounter-1].move();
            }else if(user.isShootingL == true ){
                Bb[i].isShotL = true;
            }
        }*/
        moveBullet();
    }

    public void moveBullet(){
        for(int i = 0; i < Bb.length; i++){
            Bb[i].move();
        }
        for(int i = 0; i < Bb2.length; i++){
            Bb2[i].move();
        }
    }




    public void checkIntersections() {
        for(int i = 0; i<Chug.length; i++){
            if(user.rec.intersects(Chug[i].rec) && Chug[i].Taken == false){
                Chug[i].Taken = true;
                user.height = user.height - 5;
                user.width = user.width - 5;
            }
        }
        for(int i = 0; i<Chug.length; i++){
            if(user2.rec.intersects(Chug[i].rec) && Chug[i].Taken == false){
                Chug[i].Taken = true;
                user2.height = user2.height - 5;
                user2.width = user2.width - 5;
            }
        }
        for(int i=0;i<Box.length; i++){
            if(user.rec.intersects(Box[i].Lrec)){
                user.xpos = Box[i].xpos - user.width;
            }
        }
        for(int i=0;i<Box.length; i++){
            if(user.rec.intersects(Box[i].Rrec)){
                user.xpos = Box[i].xpos +Box[i].width;
            }
        }
        for(int i=0;i<Box.length; i++){
            if(user.rec.intersects(Box[i].Trec)){
                user.ypos = Box[i].ypos - user.height;
            }
        }
        for(int i=0;i<Box.length; i++){
            if(user2.rec.intersects(Box[i].Lrec)){
                user2.xpos = Box[i].xpos - user.width;
            }
        }
        for(int i=0;i<Box.length; i++){
            if(user2.rec.intersects(Box[i].Rrec)){
                user2.xpos = Box[i].xpos +Box[i].width;
            }
        }
        for(int i=0;i<Box.length; i++){
            if(user2.rec.intersects(Box[i].Trec)){
                user2.ypos = Box[i].ypos - user.height;
            }
        }
        for(int i=0; i<Bb.length; i++){
            if(user2.rec.intersects(Bb[i].rec) && user2.lives>0 && Bb2[i].hitTarget==false){
                Bb2[i].hitTarget=true;
                user2.lives = user2.lives-1;
            }
        }
        for(int i=0; i<Bb2.length; i++){
            if(user.rec.intersects(Bb2[i].rec) && user.lives>0 && Bb[i].hitTarget==false){
                Bb[i].hitTarget=true;
                user.lives = user.lives-1;
            }
        }
    }

    public void run() {
        while (true) {
            moveThings();           //move all the game objects
            checkIntersections();   // check character crashes
            render();               // paint the graphics
            pause(20);         // sleep for 20 ms
        }
    }

    //paints things on the screen using bufferStrategy
    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw characters to the screen
        g.drawImage(background, 0, 0, 1000, 700, null);
//        if(ChugJug.Taken == false){
//            g.drawImage(ChugJug.pic, ChugJug.xpos, ChugJug.ypos, ChugJug.width, ChugJug.height, null);
//        }
        for(int i=0; i<Chug.length; i++){
            if(Chug[i].Taken == false){
                g.drawImage(Chug[i].pic, Chug[i].xpos, Chug[i].ypos, Chug[i].width, Chug[i] .height, null);
            }
        }
        if(user.FacingL == true){
            g.drawImage(left1, user.xpos, (int) user.ypos, user.width, user.height, null);
        }else if(user.FacingL == false){
            g.drawImage(right1, user.xpos, (int) user.ypos, user.width, user.height, null);
        }
        if(user2.FacingL2 == true){
            g.drawImage(left2, (int)user2.xpos, (int)user2.ypos, user2.width, user2.height, null);
        }else if(user2.FacingL2 == false){
            g.drawImage(right2, (int)user2.xpos, (int)user2.ypos, user2.width, user2.height, null);
        }
        for(int i = 0; i<Box.length;i++){
            g.drawImage(boxPic, Box[i].xpos, Box[i].ypos, Box[i].width, Box[i].height, null);
        }
        for(int i = 0; i<Bb.length;i++){
            g.drawImage(bulletPic, Bb[i].xpos, Bb[i].ypos, Bb[i].width, Bb[i].height, null);
        }
        for(int i = 0; i<Bb2.length;i++){
            g.drawImage(bulletPic, Bb2[i].xpos, Bb2[i].ypos, Bb2[i].width, Bb2[i].height, null);
        }
        g.setColor(Color.white);
        g.fillRect(10,10,210, 30);
        if(user.lives>0){
            g.setColor(Color.red);
            g.fillRect(15, 15, (int)(200*(user.lives/user.maxHp)), 20);
        }
        g.setColor(Color.white);
        g.fillRect(780,10,210, 30);
        if(user.lives>0){
            g.setColor(Color.red);
            g.fillRect(785, 15, (int)(200*(user2.lives/user2.maxHp)), 20);
        }
        g.dispose();
        bufferStrategy.show();
    }

    /***
     * Step 3 for keyboard control - add required methods
     * You need to have all 3 even if you aren't going to use them all
     */
    public void keyPressed(KeyEvent event) {
        //This method will do something whenever any key is pressed down.
        //Put if( ) statements here
        char key = event.getKeyChar();     //gets the character of the key pressed
        int keyCode = event.getKeyCode();  //gets the keyCode (an integer) of the key pressed

        System.out.println("Key Pressed: " + key + "  Code: " + keyCode);

        if (keyCode == 68) { // d
            user.right = true;
            user.FacingL = false;
        }
        if (keyCode == 65) { // a
            user.left = true;
            user.FacingL = true;
        }
        if (keyCode == 83) { // s
            user.down = true;
            /*if(user.FacingL==false){
            user.isShooting = true;
            user.isShootingL=false;
            }
            else {
                user.isShootingL= true;
            user.isShooting=false;}*/

            if(user.FacingL==true){
                Bb[bulletCounter].isShotL = true;
                Bb[bulletCounter].xpos = user.xpos;
                Bb[bulletCounter].ypos = (int)user.ypos+35;
            }else {
                Bb[bulletCounter].isShot = true;
                Bb[bulletCounter].xpos = user.xpos+(user.width-16);
                Bb[bulletCounter].ypos = (int)user.ypos+35;
            }
            bulletCounter=bulletCounter+1;
        }
        if (keyCode == 87) { // w
            user.up = true;
            user.isJumping=true;
            user.dy = -11;
        }

        if (keyCode == 39) { // d
            user2.rightM = true;
            user2.FacingL2 = false;
        }
        if (keyCode == 37) { // a
            user2.leftM = true;
            user2.FacingL2 = true;
        }

        if (keyCode == 40) { // s
            user2.downM = true;
            if(user2.FacingL2==true){
                Bb2[bulletCounter2].isShotL = true;
                Bb2[bulletCounter2].xpos = user2.xpos;
                Bb2[bulletCounter2].ypos = (int)user2.ypos+35;
            }else {
                Bb2[bulletCounter2].isShot = true;
                Bb2[bulletCounter2].xpos = user2.xpos+(user2.width-16);
                Bb2[bulletCounter2].ypos = (int)user2.ypos+35;
            }
            bulletCounter2=bulletCounter2+1;
        }
        if (keyCode == 38) { // wss
            user2.isJumping=true;
            user2.dy = -11;
        }
    }//keyPressed()

    public void keyReleased(KeyEvent event) {
        char key = event.getKeyChar();
        int keyCode = event.getKeyCode();
        //This method will do something when a key is released
        if (keyCode == 68) { // d
            user.right = false;
        }
        if (keyCode == 65) { // a
            user.left = false;
        }
        if (keyCode == 83) { // s
            user.down = false;
//            for(int i=0; i<Bb.length; i++){
//                Bb[i].xpos = user.xpos+(user.width-16);
//                Bb[i].ypos = (int)user.ypos+35;
//                Bb[i].dx = 100;
//            }
        }
        if (keyCode == 87) { // w
            user.up = false;
        }

        if (keyCode == 39) { // d
            user2.rightM = false;
        }
        if (keyCode == 37) { // a
            user2.leftM = false;
        }

        if (keyCode == 40) { // s
            user2.downM = false;
        }
        if (keyCode == 38) { // w
            user2.upM = false;
        }

    }//keyReleased()

    public void keyTyped(KeyEvent event) {
        // handles a press of a character key (any key that can be printed but not keys like SHIFT)
        // we won't be using this method, but it still needs to be in your program
    }//keyTyped()


    //Graphics setup method
    public void setUpGraphics() {
        frame = new JFrame("Scratch 1v1");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

}//class
