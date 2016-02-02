package reimplementationFramework;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import donkeykong.movements.*;
import donkeykong.rules.MarioMoveBlockers;
import donkeykong.rules.MarioOverlapRules;
import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

public class StrategyKeyboard extends KeyAdapter implements MoveStrategy, Observer{
	
	protected SpeedVector speedVector = new SpeedVectorDefaultImpl(new Point(0,0));
	protected int init_speed = speedVector.getSpeed();
	protected PrototypeMovement prototype = new PrototypeMovement();
	protected Movement movement;
	protected Movement gravity = prototype.getGravity();
	protected ArrayList<Integer> listKey = new ArrayList<Integer>();
	
	protected boolean onOverlappableArea = false;
	protected boolean onBlock = false;
	protected boolean upAllow = false;
	
	public static final int SPEED_UP= 6;
	public static final int THRESHOLD_SPEED_UP = 16;
	
	public SpeedVector getSpeedVector() {

		speedVector.setSpeed(init_speed);
		if(movement != null){
			if(movement.OnGoing()){
				if(listKey.size() > THRESHOLD_SPEED_UP)
					speedVector.setSpeed(speedVector.getSpeed() + SPEED_UP);
				speedVector.setDirection(movement.nextStep());
			}
			else{
				if(movement.getVector() == null || listKey.isEmpty())
					speedVector.setDirection(new Point(0, 0));
				else
					speedVector = movement.getVector();
			}
		}
		
		if((movement == null || !movement.OnGoing()) && gravity.OnGoing()){ // && !onBlock && !onOverlappableArea
			Point direction = gravity.nextStep();
			speedVector.setDirection(new Point((int)speedVector.getDirection().getX(), (int)direction.getY())); // apply gravity
		}
		
		if(listKey.isEmpty() || !listKey.isEmpty() && listKey.get(listKey.size()-1) != KeyEvent.VK_UP){
			gravity.setOnGoing(true);
			System.out.println("Gravity on");
		}
		
		//gravity.setOnGoing(true);
		//System.out.println("Gravity on");
		
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
			listKey.clear();
		}
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int keycode = event.getKeyCode();
		listKey.add(keycode);
		switch (keycode) {
			case KeyEvent.VK_RIGHT:
				movement = prototype.getRight();
				break;
			case KeyEvent.VK_LEFT:
				movement = prototype.getLeft();
				break;
			case KeyEvent.VK_UP:
				if(upAllow){
					System.out.println("up");
					movement = prototype.getUp();
				}
				break;
			case KeyEvent.VK_DOWN:
				movement = prototype.getDown();
				break;
			case KeyEvent.VK_SPACE:
				if(movement == null || !movement.OnGoing() || listKey.get(0) != KeyEvent.VK_SPACE) // Not allow the multi jump
					movement = prototype.getJump(speedVector);
				break;
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		gravity.setOnGoing(false);
		
		if(arg0 instanceof MarioMoveBlockers){
			onOverlappableArea = false;
			//onBlock = true;
		}
		
		if(arg0 instanceof MarioOverlapRules){
			onOverlappableArea = true;
			upAllow = true;
			//onBlock = false;
		}
		
		System.out.println("Gravity off");
	}
}
