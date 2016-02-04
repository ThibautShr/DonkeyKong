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
	protected SpeedVector speedVectorSave = new SpeedVectorDefaultImpl(new Point(0,0));
	protected int init_speed = speedVector.getSpeed();
	
	//protected FactoryMovement factoryMovement = new SingletonMovementImpl(); // Pattern Singleton  
	protected FactoryMovement factoryMovement = new PrototypeMovementImpl(); // Pattern Protoype  
	//protected FactoryMovement factoryMovement = new AbstractFactoryMovementImpl(); // Pattern Factory
		
	protected Movement movement;
	protected Movement gravity = factoryMovement.getGravity();
	protected ArrayList<Integer> listKey = new ArrayList<Integer>();
	
	protected boolean onOverlappableArea = false;
	
	public static final int SPEED_UP = 2;
	public static final int THRESHOLD_SPEED_UP = 8;
	public static final int THRESHOLD_GRAVITY_SPEED_UP = 1;
	
	public SpeedVector getSpeedVector() {
		speedVector.setSpeed(init_speed);
		if(movement != null){
			if(movement.OnGoing()){
				if(listKey.size() > THRESHOLD_SPEED_UP)
					speedVector.setSpeed(speedVector.getSpeed() + SPEED_UP);
				speedVector.setDirection(movement.nextStep());
				speedVectorSave.setDirection(new Point((int)(speedVector.getDirection().getX()),0)); // we save the current speedVector (without gravity) used for a jump
			}
			else{
				if(movement.getVector() == null || listKey.isEmpty())
					speedVector.setDirection(new Point(0, 0));
				else
					speedVector.setDirection(new Point((int)movement.getVector().getDirection().getX(),0));
			}
		}
		
		//Strategy gravity
		if((movement == null || !movement.OnGoing()) && gravity.OnGoing()){
			Point direction = gravity.nextStep();
			speedVector.setSpeed(speedVector.getSpeed() + (((Gravity)gravity).getStep() / THRESHOLD_GRAVITY_SPEED_UP)); 
			speedVector.setDirection(new Point((int)speedVector.getDirection().getX(), (int)direction.getY())); // apply gravity
		}
		
		//Strategy when Mario is climbing on a ladder  
		if(!listKey.isEmpty() && listKey.get(listKey.size()-1) != KeyEvent.VK_UP){ //&& onOverlappableArea 
			gravity.setOnGoing(true);
			onOverlappableArea = false;
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
			listKey.clear();
		}
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(movement == null || !movement.OnGoing() || (!listKey.isEmpty() && listKey.get(listKey.size()-1) != KeyEvent.VK_SPACE)){ // Not allow the jump when Mario is falling ){			
			int keycode = event.getKeyCode();
			listKey.add(keycode);
			switch (keycode) {
				case KeyEvent.VK_RIGHT:
					if(!gravity.OnGoing())
						movement = factoryMovement.getRight();
					break;
				case KeyEvent.VK_LEFT:
					if(!gravity.OnGoing())
						movement = factoryMovement.getLeft();
					break;
				case KeyEvent.VK_UP:
					if(onOverlappableArea){
						movement = factoryMovement.getUp();
						onOverlappableArea = false;
					}
					break;
				case KeyEvent.VK_DOWN:
					if(!gravity.OnGoing())
						movement = factoryMovement.getDown();
					break;
				case KeyEvent.VK_SPACE:
					//if(movement == null || !movement.OnGoing() || listKey.get(0) != KeyEvent.VK_SPACE){ // Not allow the jump when Mario is falling 
					//if(((Gravity) gravity).getStep() == 0)
					//if(!onOverlappableArea){
						if(listKey.size() > 1)
							movement = factoryMovement.getJump(speedVectorSave);
						else
							movement = factoryMovement.getJump(speedVector);
					//}
					break;
			}
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
			//onBlock = false;
		}
		
		//System.out.println("Gravity off");
	}
}
