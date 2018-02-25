package game;

import java.awt.Color;
import java.util.ArrayList;

import swingy_Stuff.DrawingInstructions;

public class Pellet extends Instance {
	private int pelletShot;
	
	public Pellet(float X, float Y, float direction, float dX, float dY, int pelletShot){
		super(X,Y,32,32);
		this.direction = direction;
		this.dX = (float) (dX+Math.cos(this.direction)*1000);
		this.dY = (float) (dY+Math.sin(this.direction)*1000);
		this.pelletShot = pelletShot;
	}
	
	public void oobCheck(int scrWidth, int scrHeight){
		if (x+w < 0 || x > scrWidth || y+h < 0 || y > scrHeight){
			remove = true;
		}
	}
	
	public ArrayList<DrawingInstructions> draw(){
		ArrayList<DrawingInstructions> arr = new ArrayList<DrawingInstructions>();
		arr.add(new DrawingInstructions((int)x, (int)y, (int)w, (int)h, new Color(255,255,0), "Pellet", new float[]{pelletShot}));
		return arr;
	}
}
