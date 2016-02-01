package donkeykong.movements;

import gameframework.base.SpeedVector;

public class PrototypeMovement {

	Movement up, down, left, right, jump;
	
	public PrototypeMovement(){
		up = new Up();
		down = new Down();
		left = new Left();
		right = new Right();
		jump = new Jump();
	}
	
	public Movement getUp(){
		up.init();
		return up;
	}
	
	public Movement getDown(){
		down.init();
		return down;
	}
	
	public Movement getLeft(){
		left.init();
		return left;
	}
	
	public Movement getRight(){
		right.init();
		return right;
	}

	public Movement getJump(SpeedVector v){
		jump.init();
		jump.setVector(v);
		return jump;
	}
}
