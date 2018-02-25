package game;

import java.util.ArrayList;

import swingy_Stuff.DrawingInstructions;
import swingy_Stuff.Game;

public class ObjectManager {
	public Instance head;
	private float endGame = 0;
	private boolean end = false;
	public ObjectManager(int width, int height){
		head = new Head();
		head.addNext(new BreakingWindow(width/2-320, height/2-240, 640, 480));
		head.addNext(new Ship(width/2, height*7/8, 64, 64));
	}
	public ArrayList<DrawingInstructions> update(boolean[] keyPressed, boolean[] keyHeld, double deltaTime, int width, int height){
		for (Instance i = head; i != null; i = i.next){
			i.update(keyPressed, keyHeld, deltaTime);
		}
		for (Instance i = head; i != null; i = i.next){
			for (Instance j = i.next; j != null; j = j.next){
				i.collision(j, deltaTime);
			}
		}
		for (Instance i = head; i != null; i = i.next){
			i.finishUpdate(deltaTime, width, height);
			if (i.next != null && i.next.remove){
				if (i.next.getClass().getSimpleName().equals("Ship")){
					System.out.println("GAME OVER.");
					end = true;
				}
				i.deleteNext();
			}
		}
		ArrayList<DrawingInstructions> arr = new ArrayList<DrawingInstructions>();
		for (Instance i = head.next; i != null; i = i.next){
			arr.addAll(i.draw());
		}
		boolean thereIsAWindow = false;
		for (Instance i = head; i != null; i = i.next){
			if (i.getClass().getSimpleName().equals("BreakingWindow")){
				thereIsAWindow = true;
			}
		}
		if (!thereIsAWindow && !end){
			System.out.println("YOU WIN!");
			end = true;
		}
		if (end == true){
			endGame += deltaTime;
			if (endGame > 5){
				Game.endGame();
			}
		}
		return arr;
	}
}
