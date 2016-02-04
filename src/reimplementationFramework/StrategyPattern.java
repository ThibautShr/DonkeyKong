package reimplementationFramework;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import donkeykong.movements.Down;
import donkeykong.movements.Left;
import donkeykong.movements.Movement;
import donkeykong.movements.Pattern;
import donkeykong.movements.PrototypeMovement;
import donkeykong.movements.PrototypeMovementDefaultImpl;
import donkeykong.movements.Right;
import donkeykong.rules.MarioMoveBlockers;
import donkeykong.rules.MarioOverlapRules;
import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

public class StrategyPattern implements MoveStrategy, Observer{
	
	protected PrototypeMovement prototype = new PrototypeMovementDefaultImpl();
	protected Movement gravity = prototype.getGravity();
	protected boolean onOverlappableArea = false;
	
	/*public PrototypeMovement getPrototype() {
		return prototype;
	}

	public void setPrototype(PrototypeMovement prototype) {
		this.prototype = prototype;
	}*/

	protected int cptOverlappableArea = 0;
	
	protected Movement pattern;
	protected SpeedVector v = new SpeedVectorDefaultImpl(new Point(0,0));
	//protected boolean activeGravity = true; 
	
	public StrategyPattern(Movement p, SpeedVector sv) {
		this.pattern = p;
		if(sv == null)
			this.v = new SpeedVectorDefaultImpl(new Point(0,0));
		else
			this.v = sv;
	}
	
	@Override
	public SpeedVector getSpeedVector() {
		if(pattern != null){
			//System.out.println("move");
			gravity.setOnGoing(false);
			if(!pattern.OnGoing()){
				pattern.init();
			}
			//System.out.println("pattern != null : x -> " + v.getDirection().getX() + "   y -> " + v.getDirection().getY());
			
			//System.out.println("onOverlappableArea : " + onOverlappableArea);
			if(gravity.OnGoing()){
				//System.out.println("On going");
				Point gravity_direction = gravity.nextStep();
				v.setDirection(gravity_direction);
				gravity.setOnGoing(false);
				//if(onOverlappableArea)
				//	cptOverlappableArea++;
			}
			else{
				//System.out.println("Not on going");
				Point move_direction = pattern.nextStep();
				v.setDirection(move_direction);
				gravity.setOnGoing(true);
			}

			//System.out.println("v : " + v.getDirection().getX() + ", " + v.getDirection().getY());
			return v;
		}
		//System.out.println("pattern == null");

		v.setDirection(new Point(0,0));
		return v;
		
	}
	
	public void setPattern(Movement pattern){
		this.pattern = pattern;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof MarioMoveBlockers){
			//System.out.println("move blocker");
			//gravity.setOnGoing(false);
			//pattern = new Right();
			
			//System.out.println("v : " + v.getDirection().getX() + "," + v.getDirection().getY());
			
			//System.out.println(cptOverlappableArea);
			
			/*if(onOverlappableArea)
				onOverlappableArea = !onOverlappableArea;
			else	
				pattern = new Right();*/
			
			/*if(v.getDirection().getX() == 1){
				System.out.println("Go to left");
				pattern = new Left();
			}
				
			if(v.getDirection().getX() == -1){
				System.out.println("Go to right");
				pattern = new Right();
			}*/
		
		}
		
		
		if(o instanceof MarioOverlapRules){
			//System.out.println("overlap");
			//gravity.setOnGoing(true);
			//pattern = new Down();
			//onOverlappableArea = true;
		}
	}

}
