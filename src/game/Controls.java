package game;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

public class Controls implements KeyEventDispatcher {
	public boolean[] keyPressed, keyHeld;
	private double[] keyPressedLength;
	public Controls(){
		keyPressed = new boolean[]{false, false, false, false};
		keyHeld = new boolean[]{false, false, false, false};
		keyPressedLength = new double[]{-1, -1, -1, -1};
	}
	
	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_PRESSED || e.getID() == KeyEvent.KEY_TYPED){
			if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
				if (Math.abs(keyPressedLength[0]+1) < 0.001 && !keyHeld[0]){
					keyPressedLength[0] = 0;
					keyPressed[0] = true;
					keyHeld[0] = true;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
				if (Math.abs(keyPressedLength[1]+1) < 0.001 && !keyHeld[1]){
					keyPressedLength[1] = 0;
					keyPressed[1] = true;
					keyHeld[1] = true;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
				if (Math.abs(keyPressedLength[2]+1) < 0.001 && !keyHeld[2]){
					keyPressedLength[2] = 0;
					keyPressed[2] = true;
					keyHeld[2] = true;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_X){
				if (Math.abs(keyPressedLength[3]+1) < 0.001 && !keyHeld[3]){
					keyPressedLength[3] = 0;
					keyPressed[3] = true;
					keyHeld[3] = true;
				}
			}
		} else if (e.getID() == KeyEvent.KEY_RELEASED){
			if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
				keyHeld[0] = false;
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
				keyHeld[1] = false;
			} else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
				keyHeld[2] = false;
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_X){
				keyHeld[3] = false;
			}
		}
		return true;
	}
	
	public void resetKeyPresses(double deltaTime){
	    for (int i = 0; i < keyPressedLength.length; i++){
	      if (keyPressed[i]){
	        if (keyPressedLength[i] > 0.05){
	          keyPressedLength[i] = -1;
	          keyPressed[i] = false;
	        } else keyPressedLength[i] += deltaTime;
	      }
	    }
	  }
}
