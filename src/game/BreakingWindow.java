package game;

import java.awt.Color;
import java.util.ArrayList;

import swingy_Stuff.DrawingInstructions;

public class BreakingWindow extends Instance{
	private float offsetX, offsetY;
	public boolean nextBreak, pressedAButton;
	public BreakingWindow(float X, float Y, float W, float H) {
		this(X,Y,W,H,0,0,true);
	}
	
	public BreakingWindow(float X, float Y, float W, float H, float offsetX, float offsetY, boolean nextBreak) {
		super(X, Y, W, H);
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.nextBreak = nextBreak;
	}
	
	public void oobCheck(int scrWidth, int scrHeight){
		if (x < 0){
			x = 0;
			dX = -dX;
		}
		if (x+w > scrWidth){
			x = scrWidth-w;
			dX = -dX;
		}
		if (y < 0){
			y = 0;
			dY = -dY;
		}
		if (y+h > scrHeight){
			y = scrHeight-h;
			dY = -dY;
		}
	}
	
	public void extraCollision(Instance o, double direction){
		if (o.getClass().getSimpleName().equals("Pellet") && o.remove == false){
			breakOff();
			o.remove = true;
		}
	}
	
	public void breakOff(){
		remove = true;
		BreakingWindow right, left;
		if (w > 64 && h > 64){
			if (nextBreak){
				right = new BreakingWindow(x+w/2, y, w/2, h, offsetX+w/2, offsetY, false);
				left = new BreakingWindow(x, y, w/2, h, offsetX, offsetY, false);
				right.dX += 100;
				left.dX -= 100;
			} else {
				right = new BreakingWindow(x, y+h/2, w, h/2, offsetX, offsetY+h/2, true);
				left = new BreakingWindow(x, y, w, h/2, offsetX, offsetY, true);
				right.dY += 100;
				left.dY -= 100;
			}
			right.pressedAButton = true;
			left.pressedAButton = true;
			addNext(right);
			addNext(left);
		}
	}
	
	public ArrayList<DrawingInstructions> draw(){
		ArrayList<DrawingInstructions> arr = new ArrayList<DrawingInstructions>();
		arr.add(new DrawingInstructions((int)x, (int)y, (int)w, (int)h, new Color(255,255,0), "Typ1", new float[]{offsetX, offsetY}));
		return arr;
	}
	
}
