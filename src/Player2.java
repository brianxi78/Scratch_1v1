

import java.awt.*;

	public class Player2 {

		//VARIABLE DECLARATION SECTION
		//Here's where you state which variables you are going to use.

		public double xpos;                //the x position
		public double ypos;                //the y position
		public int width;
		public int height;
		public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
		public double dx;                    //the speed of the hero in the x direction
		public double dy;                    //the speed of the hero in the y direction
		public Rectangle rec;
		public Image pic;
		public int hits;
		public boolean upM;
		public boolean rightM;
		public boolean leftM;
		public boolean downM;
		public boolean FacingL2;
		public boolean isJumping;

		// METHOD DEFINITION SECTION

		//This is a constructor that takes 3 parameters.  This allows us to specify the object's name and position when we build it.
		// if you put in a String, an int and an int the program will use this constructor instead of the one above.
		public Player2(int pXpos, int pYpos) {

			xpos = pXpos;
			ypos = pYpos;
			width = 125;
			height = 125;
			dx = 5;
			dy = -5;
			isAlive = true;
			hits = 0;
			rec = new Rectangle((int)xpos, (int)ypos, width, height);


		} // constructor


		public Player2(double pXpos, double pYpos, double dxParameter, double dyParameter) {

			xpos = pXpos;
			ypos = pYpos;
			width = 125;
			height = 125;
			dx = dxParameter;
			dy = dyParameter;
			isAlive = true;
			hits = 0;
			rec = new Rectangle((int)xpos, (int)ypos, width, height);


		} // constructor

//		public Player2(int i, int i1, int i2, int i3, Image mousePic) {
//		}


		//The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
		public void move() {
			xpos = xpos + dx;
			ypos = ypos + dy;

//			if (xpos > 1000 - width || xpos < 0) {
//				dx = -dx;
//			}
//
//			if (ypos < 0 || ypos + height > 700) {
//				dy = -dy;
//			}
			if(rightM == true){
				dx = 5;
			} else if (leftM == true) {
				dx = -5;
			} else { // (right == false && left == false)
				dx = 0;
			}

//			if(downM == true){
//				dy = 5;
//			} else if (upM == true) {
//				dy = -5;
//			} else {
//				dy = 0;
//			}

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
				dy = dy + .5;
			}

			rec = new Rectangle((int)xpos, (int)ypos, width, height);

		}


	} //end of the Mouse object class  definition
