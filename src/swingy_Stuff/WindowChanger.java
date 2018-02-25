package swingy_Stuff;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class WindowChanger {
	public void passOnList(ArrayList<Window> winds, ArrayList<DrawingInstructions> instr, BufferedImage b){
		for (int i = 0; i < winds.size(); i++){
			winds.get(i).used = false; 
		}
		for (int i = 0; i < instr.size(); i++){
			DrawingInstructions in = instr.get(i);
			if (in.name == "Ship"){
				Window wind = null;
				for (int j = 0; j < winds.size(); j++){
					if (winds.get(j).getTransparentType() == 2){
						wind = winds.get(j);
						break;
					}
				}
				if (wind == null){
					wind = new Window(instr.get(i).x, instr.get(i).y, instr.get(i).w, instr.get(i).h, 2, null);
					winds.add(wind);
				}
				wind.used = true;
				ArrayList<DrawingInstructions> instruc = new ArrayList<DrawingInstructions>();
				instruc.add(new DrawingInstructions(0, 0, 10000, 10000, new Color(255,255,255),"Rectangle"));
				instruc.add(in);
				wind.getPane().paintComponent(wind.getGraphics(), instruc);
				wind.setDirection(in.extra[0]);
				wind.setLocation(in.x, in.y);
			} else if (instr.get(i).name == "Typ1"){
				Window wind = null;
				double offX = 0;
				double offY = 0;
				if (in.extra[0] > 0) offX = in.w/4;
				if (in.extra[1] > 0) offY = in.h/4;
				for (int j = 0; j < winds.size(); j++){
					if ((winds.get(j).getTransparentType() == 1 
							&& Math.abs(winds.get(j).getOffsetXR()-in.extra[0]) < 0.01
							&& Math.abs(winds.get(j).getOffsetYR()-in.extra[1]) < 0.01
							&& Math.abs(winds.get(j).getW()-in.w) < 1
							&& Math.abs(winds.get(j).getH()-in.h) < 1) || 
							(winds.get(j).getTransparentType() == 0 && Math.abs(winds.get(j).getW()-in.w) < 1
							&& Math.abs(winds.get(j).getH()-in.h) < 1)){
						wind = winds.get(j);
						break;
					}
				}
				if (wind == null){
					boolean horizBounded = in.w+in.extra[0] >= 639.9;
					boolean vertiBounded = in.h+in.extra[1] >= 479.9;
					if (in.w < 640 || in.h < 320){
						wind = new Window(in.x, in.y, in.w, in.h, 1, b, 
							(int)in.extra[0], (int)in.extra[1], new boolean[]{horizBounded, vertiBounded});
					} else {
						wind = new Window(in.x, in.y, in.w, in.h, 0, b);
						wind.setTitle("A FINAL GOODBYE TO JAVAX.SWING");
					}
					wind.setVisible(true);
					winds.add(wind);
				}
				wind.used = true;
				if (wind.getX() != in.x || wind.getY() != in.y){
					wind.getPane().paintComponent(wind.getGraphics(), new ArrayList<DrawingInstructions>());
					wind.setLocation(in.x, in.y);
				}
			} else if (instr.get(i).name == "Pellet"){
				Window wind = null;
				for (int j = 0; j < winds.size(); j++){
					if (winds.get(j).getTransparentType() == 3 && ((int)(in.extra[0])) == winds.get(j).winType){
						wind = winds.get(j);
						break;
					}
				}
				if (wind == null){
					wind = new Window(in.x, in.y, in.w, in.h, 3, b);
					wind.winType = (int)(in.extra[0]);
					winds.add(wind);
				}
				if (wind.getX() != in.x || wind.getY() != in.y){
					ArrayList<DrawingInstructions> instruc = new ArrayList<DrawingInstructions>();
					instruc.add(new DrawingInstructions(0, 0, 10000, 10000, new Color(255,255,0),"Rectangle"));
					wind.getPane().paintComponent(wind.getGraphics(), instruc);
					wind.setLocation(in.x, in.y);
				}
				wind.used = true;
			}
		}
		int i = 0;
		while (i < winds.size()){
			if (!winds.get(i).used){
				winds.get(i).setVisible(false);
				winds.remove(i);
				i -= 1;
			}
			i += 1;
		}
	}
}
