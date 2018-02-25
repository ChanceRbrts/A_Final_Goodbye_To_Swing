package swingy_Stuff;

import java.awt.Color;

public class DrawingInstructions {
	protected int x, y, w, h;
	protected float[] extra;
	protected Color col;
	protected String name;
	
	public DrawingInstructions(int X, int Y, int W, int H, Color c, String name){
		this(X, Y, W, H, c, name, new float[]{});
	}
	
	public DrawingInstructions(int X, int Y, int W, int H, Color c, String name, float[] extra){
		x = X;
		y = Y;
		w = W;
		h = H;
		col = c;
		this.name = name;
		this.extra = extra;
	}
}
