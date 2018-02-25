package game;

import java.awt.Color;
import java.util.ArrayList;

import swingy_Stuff.DrawingInstructions;

public abstract class Instance {
	protected float x, y, dX, dY, w, h, direction;
	protected int immovable;
	public Instance next;
	protected boolean remove;
	public Instance(float X, float Y, float W, float H){
		x = X;
		y = Y;
		w = W;
		h = H;
		dX = 0;
		dY = 0;
		immovable = 1;
		next = null;
		remove = false;
		direction = (float)(Math.PI/2);
	}
	
	public void addNext(Instance i){
		i.next = next;
		next = i;
	}
	
	public void deleteNext(){
		next = next.next;
	}
	
	public void update(boolean[] controlsPressed, boolean[] controlsHeld, double deltaTime){}
	
	public void finishUpdate(double deltaTime, int scrWidth, int scrHeight){
		x += dX*deltaTime;
		y += dY*deltaTime;
		oobCheck(scrWidth, scrHeight);
	}
	
	public void oobCheck(int scrWidth, int scrHeight){
		if (x < 0) x = 0;
		if (x+w > scrWidth) x = scrWidth-w;
		if (y < 0) y = 0;
		if (y+h > scrHeight) y = scrHeight-h;
	}
	
	public ArrayList<DrawingInstructions> draw(){
		ArrayList<DrawingInstructions> arr = new ArrayList<DrawingInstructions>();
		arr.add(new DrawingInstructions((int)x, (int)y, (int)w, (int)h, new Color(0,0,0), "Rectangle"));
		return arr;
	}
	
	public void collision(Instance o, double deltaTime){
		if (y+h+dY*deltaTime > o.y+o.dY*deltaTime && y+h <= o.y && 
		        x+w+dX*deltaTime > o.x+o.dX*deltaTime && x+dX*deltaTime < o.x+o.w+o.dX*deltaTime){
		          extraCollision(o, Math.PI*3/2);
		          o.extraCollision(this, Math.PI/2);
		      }
		      if (y+dY*deltaTime < o.y+o.h+o.dY*deltaTime && y >= o.y+o.h && 
		        x+w+dX*deltaTime > o.x+o.dX*deltaTime && x+dX*deltaTime < o.x+o.w+o.dX*deltaTime){
		          extraCollision(o,Math.PI/2);
		          o.extraCollision(this, Math.PI*3/2);
		      }
		      if (x+w+dX*deltaTime > o.x+o.dX*deltaTime && x+w <= o.x && 
		        y+h+dY*deltaTime > o.y+o.dY*deltaTime && y+dY*deltaTime < o.y+o.h+o.dY*deltaTime){
		          extraCollision(o, 0);
		          o.extraCollision(this, Math.PI);
		      }
		      if (x+dX*deltaTime < o.x+o.w+o.dX*deltaTime && x >= o.x+o.w && 
		        y+h+dY*deltaTime > o.y+o.dY*deltaTime && y+dY*deltaTime < o.y+o.h+o.dY*deltaTime){
		          extraCollision(o, Math.PI);
		          o.extraCollision(this, 0);
		      }
	}
	
	public void extraCollision(Instance o, double direction){}
}
