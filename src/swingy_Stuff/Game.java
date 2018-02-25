package swingy_Stuff;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import game.Controls;
import game.ObjectManager;

public class Game {
	private static boolean gameLoop;
	private static ObjectManager objMan;
	private static double currentTime, endTime;
	private static int h;
	private static BufferedImage b;
	private static ArrayList<Window> winds;
	private static Dimension scrSize;
	private static Controls contr;
	private static WindowChanger winChang;
	
	public static void main(String[] args){
		setup();
		update();
	}
	
	public static void setup(){
		gameLoop = true;
		scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		objMan = new ObjectManager(scrSize.width, scrSize.height);
		contr = new Controls();
		winChang = new WindowChanger();
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(contr);
		JFrame j1 = new JFrame("A FINAL GOODBYE TO JAVAX.SWING");
		j1.setSize(640, 480);
		j1.setLocationRelativeTo(null);
		j1.setResizable(false);
		j1.setBackground(new Color(234,234,234));
		j1.setVisible(true);
		try {
			Robot r = new Robot();
			b = r.createScreenCapture(new Rectangle(j1.getX(),j1.getY(),j1.getWidth(),480));
			for (int i = 0; i < 480; i++){
				if (r.getPixelColor(j1.getX()+j1.getWidth()/2, j1.getY()+i).equals(j1.getBackground())) break;
				h++;
			}
			b = r.createScreenCapture(new Rectangle(j1.getX(), j1.getY(), j1.getWidth(), h));
		} catch (AWTException e) {};
		j1.setVisible(false);
		winds = new ArrayList<Window>();
	}
	
	public static void update(){
		double deltaTime = 0;
		while (gameLoop){
			if (deltaTime != 0){
				ArrayList<DrawingInstructions> draw = objMan.update(contr.keyPressed, contr.keyHeld, deltaTime, scrSize.width, scrSize.height);
				winChang.passOnList(winds, draw, b);
				contr.resetKeyPresses(deltaTime);
			}
			endTime = currentTime;
			currentTime = System.nanoTime()/1000000000.0;
			deltaTime = currentTime-endTime;
		}
	}
	
	public static void endGame(){
		gameLoop = false;
	}
}
