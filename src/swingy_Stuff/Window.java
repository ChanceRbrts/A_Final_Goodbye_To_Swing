package swingy_Stuff;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Path2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = 8562475804102617029L;
	public BufferStrategy s;
	private int offsetX, offsetY;
	private BufferedImage buffered;
	private boolean transparent;
	private int w, h, x, y, type;
	private int trueX, trueY, trueWid, trueHei;
	public int winType;
	private double direction;
	private Pane p;
	public boolean used;
	public Window(int x, int y, int w, int h, int type, BufferedImage b){
		this(x, y, w, h, type, b, 0, 0, new boolean[]{true, true});
	}
	
	private void addToArrayList(ArrayList<Integer> list, int[] newList){
		for (int i = 0; i < newList.length; i++){
			list.add(newList[i]);
		}
	}
	
	public Window(int x, int y, int w, int h, int type, BufferedImage b, 
			int offsetX, int offsetY, boolean[] bounded){
		this.x = x;
		this.y = y;
		trueX = 0;
		trueY = 0;
		this.w = w;
		this.h = h;
		trueWid = w;
		trueHei = h;
		buffered = b;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		p = new Pane(this);
		this.type = type;
		if (type == 1){
			if (offsetX != 0 && !bounded[0]) trueWid = w*3/2;
			else if (offsetX == 0 && bounded[0]) trueWid = w;
			else trueWid = w*5/4;
			if (offsetY != 0 && !bounded[1]) trueHei = h*3/2;
			else if (offsetY == 0 && bounded[1]) trueHei = h;
			else trueHei = h*5/4;
		}
		setSize(trueWid, trueHei);
		if (type == 0){
			transparent = false;
			setBackground(new Color(130, 130, 130));
		} else if (type == 1){
			transparent = true;
			setUndecorated(true);
			setBackground(new Color(130, 130, 130));
			addComponentListener(new ComponentAdapter(){
				public void componentResized(ComponentEvent e) {
					ArrayList<Integer> xPoints = new ArrayList<Integer>();
					ArrayList<Integer> yPoints = new ArrayList<Integer>();
					if (offsetX == 0){
						addToArrayList(xPoints, new int[]{0, 0});
						addToArrayList(yPoints, new int[]{0, h});
					} else {
						trueX = -w/4;
						addToArrayList(xPoints, new int[]{0, w/4, 0, -w/4, 0});
						addToArrayList(yPoints, new int[]{0, h/4, h/2, 3*h/4, h});
					}
					if (bounded[1]){
						xPoints.add(w);
						yPoints.add(h);
					} else {
						addToArrayList(xPoints, new int[]{w/4, w/2, 3*w/4, w});
						addToArrayList(yPoints, new int[]{3*h/4, h, 5*h/4, h});
					}
					if (bounded[0]){
						xPoints.add(w);
						yPoints.add(0);
					} else {
						addToArrayList(xPoints, new int[]{3*w/4, w, 5*w/4, w});
						addToArrayList(yPoints, new int[]{3*h/4, h/2, h/4, 0});
					}
					if (offsetY != 0){
						trueY -= h/4;
						addToArrayList(xPoints, new int[]{3*w/4, w/2, w/4});
						addToArrayList(yPoints, new int[]{-h/4, 0, h/4});
					}
					Path2D.Double path = new Path2D.Double();
					path.moveTo(xPoints.get(0)-trueX, yPoints.get(0)-trueY);
					for (int i = 1; i < xPoints.size(); i++){
						path.lineTo(xPoints.get(i)-trueX, yPoints.get(i)-trueY);
					}
					path.closePath();
					setShape(path);
				}
			});
		} else if (type == 2){
			setUndecorated(true);
			transparent = true;
		} else if (type == 3){
			transparent = true;
			setUndecorated(true);
		} else if (type == -1){
			transparent = true;
			setUndecorated(true);
			setBackground(new Color(0, 0, 0, 0));
		}
		setVisible(true);
	}
	
	public void update(){
		setLocation(x+trueX, y+trueY);
	}
	
	public Pane getPane(){
		return p;
	}
	
	public BufferedImage getBuffered(){
		return buffered;
	}
	
	public int getOffsetX(){
		return offsetX+trueX;
	}
	
	public int getOffsetY(){
		return offsetY+trueY;
	}
	
	public int getOffsetXR(){
		return offsetX;
	}
	public int getOffsetYR(){
		return offsetY;
	}
	
	public int getTransparentType(){
		return type;
	}
	
	public void setDirection(double direction){
		this.direction = direction;
	}
	
	public int getW(){
		return w;
	}
	
	public int getH(){
		return h;
	}
}
