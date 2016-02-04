package donkeykong.movements;

import gameframework.base.SpeedVector;

public class SingletonMovement implements FactoryMovement {
	
	Up up;
	Down down;
	Left left;
	Right right;
	Jump jump;
	Gravity gravity;
	
	public Movement getUp(){
		if(up == null)
			up = new Up();
		up.init();
		return up;
			 
	}
	
	public Movement getDown(){
		if(down == null)
			down = new Down();
		down.init();
		return down;
	}
	
	public Movement getLeft(){
		if(left == null)
			left = new Left();
		left.init();
		return left;
	}
	
	public Movement getRight(){
		if(right == null)
			right = new Right();
		right.init();
		return right;
	}

	public Movement getJump(SpeedVector v){
		if(jump == null){
			jump = new Jump();
		}
		jump.init();
		jump.setVector(v);
		return jump;
	}
	
	public Movement getGravity(){
		if(gravity == null)
			gravity = new Gravity();
		gravity.init();
		return gravity;
	}
}
