package reimplementationFramework;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import donkeykong.movements.*;
import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

public class StrategyKeyboard extends KeyAdapter implements MoveStrategy {
	
	protected SpeedVector speedVector = new SpeedVectorDefaultImpl(new Point(0,0));
	protected int init_speed = speedVector.getSpeed();
	protected Movement movement;
	protected PrototypeMovement prototype = new PrototypeMovement();
	protected int cptKey = 0;
	
	public static final int SPEED_UP= 6;
	public static final int THRESHOLD_SPEED_UP = 16;
	
	public SpeedVector getSpeedVector() {

		speedVector.setSpeed(init_speed);
		if(movement != null ){
			if(movement.OnGoing()){
				if(cptKey > THRESHOLD_SPEED_UP)
					speedVector.setSpeed(speedVector.getSpeed() + SPEED_UP);
				speedVector.setDirection(movement.nextStep());
			}
			else{
				if(movement.getVector() == null || cptKey == 0)
					speedVector.setDirection(new Point(0, 0));
				else
					speedVector = movement.getVector();
			}
		}
		
		return speedVector;
	}

	public Movement getMovement() {
		return movement;
	}
	
	public void keyReleased(KeyEvent event) {
		int keycode = event.getKeyCode();
		if(keycode == KeyEvent.VK_RIGHT || 
		   keycode == KeyEvent.VK_LEFT || 
		   keycode == KeyEvent.VK_DOWN || 
		   keycode == KeyEvent.VK_UP){
			speedVector.setDirection(new Point(0, 0));
			cptKey = 0;
		}
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int keycode = event.getKeyCode();
		cptKey++;
		switch (keycode) {
			case KeyEvent.VK_RIGHT:
				movement = prototype.getRight();
				break;
			case KeyEvent.VK_LEFT:
				movement = prototype.getLeft();
				break;
			case KeyEvent.VK_UP:
				movement = prototype.getUp();
				break;
			case KeyEvent.VK_DOWN:
				movement = prototype.getDown();
				break;
			case KeyEvent.VK_SPACE:
				movement = prototype.getJump(speedVector);
				break;
		}
	}
}
