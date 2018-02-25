package game;

import java.awt.Color;
import java.util.ArrayList;

import swingy_Stuff.DrawingInstructions;

public class Ship extends Instance {
	private boolean pressedBefore;
	private int pelletsShot;
	
	public Ship(float X, float Y, float W, float H) {
		super(X, Y, W, H);
		pelletsShot = 0;
		pressedBefore = false;
		direction = (float) ((direction+Math.PI)%(2*Math.PI));
	}

	public void update(boolean[] controlsPressed, boolean[] controlsHeld, double deltaTime){
		if (controlsHeld[0] && !controlsHeld[1]){
			direction = (float)((float)(direction-2*Math.PI*deltaTime)%(2*Math.PI));
		} else if (controlsHeld[1] && !controlsHeld[0]){
			direction = (float)((float)(direction+2*Math.PI*deltaTime)%(2*Math.PI));
		}
		dX -= 0.25*dX*deltaTime;
		dY -= 0.25*dY*deltaTime;
		if (controlsHeld[2]){
			dX += 800*deltaTime*Math.cos(direction);
			if (dX > 400) dX = 400;
			else if (dX < -400) dX = -400;
			dY += 1600*deltaTime*Math.sin(direction);
			if (dY > 400) dY = 400;
			else if (dX < -400) dY = -400;
		}
		if (controlsPressed[3] && !pressedBefore){
			pressedBefore = true;
			addNext(new Pellet((float)(x+w/2+w/2*Math.cos(direction)), (float)(y+h/2+h/2*Math.sin(direction)),
					direction, dX, dY, pelletsShot));
			pelletsShot += 1;
		} else if (!controlsPressed[3]) pressedBefore = false;
	}
	
	public ArrayList<DrawingInstructions> draw(){
		ArrayList<DrawingInstructions> arr = new ArrayList<DrawingInstructions>();
		arr.add(new DrawingInstructions((int)x, (int)y, (int)w, (int)h, new Color(150,150,30), "Ship", new float[]{direction}));
		return arr;
	}
	
	public void extraCollision(Instance o, double direction){
		if (o.getClass().getSimpleName().equals("BreakingWindow")){
			remove = true;
		}
	}
}
