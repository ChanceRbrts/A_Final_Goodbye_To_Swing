package swingy_Stuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Pane extends JPanel {
	private static final long serialVersionUID = -1016798440977054508L;
	private BufferedImage buff;
	private Window wind;
	public Pane(Window w){
		wind = w;
		setBackground(wind.getBackground());
	}
	
	public void paintComponent(Graphics g, ArrayList<DrawingInstructions> draw){
		super.paintComponent(g);
		int x = wind.getX();
		int y = wind.getY();
		BufferedImage b = new BufferedImage(wind.getWidth(), wind.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics bg = b.getGraphics();
		g.drawImage(buff, 0, 0, this);
	    g.setColor(new Color(0, 0, 0, 0));
	    g.clearRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < draw.size(); i++){
			DrawingInstructions d = draw.get(i);
			if (d.name.equals("Rectangle")){
				bg.setColor(d.col);
				bg.fillRect(d.x-x, d.y-y, d.w, d.h);
			}
			if (d.name.equals("Ship")){
				bg.setColor(d.col);
				bg.fillPolygon(new int[]{
						(int)(d.w/2+d.w/2*Math.cos(d.extra[0])),
						(int)(d.w/2+d.w/2*Math.cos(d.extra[0]+Math.PI*5/6)),
						(int)(d.w/2+d.w/2*Math.cos(d.extra[0]+Math.PI*7/6))
						}, new int[]{
						(int)(d.w/2+d.w/2*Math.sin(d.extra[0])),
						(int)(d.w/2+d.w/2*Math.sin(d.extra[0]+Math.PI*5/6)),
						(int)(d.w/2+d.w/2*Math.sin(d.extra[0]+Math.PI*7/6))
						}, 3);
			}
		}
		if (wind.getTransparentType() == 1){
			bg.drawImage(wind.getBuffered(), -wind.getOffsetX(), -wind.getOffsetY(), this);
			
		}
		buff = b;
		g.drawImage(buff, 0, 0, wind.getWidth(), wind.getHeight(), this);
	}
}
