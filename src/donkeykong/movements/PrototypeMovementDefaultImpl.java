package donkeykong.movements;

import gameframework.base.SpeedVector;

public class PrototypeMovementDefaultImpl implements PrototypeMovement {

	Movement up, down, left, right, jump, gravity;
	
	public PrototypeMovementDefaultImpl(){
		up = new Up();
		up.init();
		down = new Down();
		down.init();
		left = new Left();
		left.init();
		right = new Right();
		right.init();
		jump = new Jump();
		jump.init();
		gravity = new Gravity();
		gravity.init();
	}
	
	public Movement getUp(){
		try {
			return (Up) up.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch(NullPointerException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Movement getDown(){
		try {
			return (Down) down.clone();
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
			return null;
		}catch(NullPointerException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Movement getLeft(){
		
		try {
			return (Left) left.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}catch(NullPointerException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Movement getRight(){
		
		try {
			return (Right) right.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}catch(NullPointerException e){
			e.printStackTrace();
			return null;
		}
	}

	public Movement getJump(SpeedVector v){
		Jump clone;
		try {
			clone = (Jump) jump.clone();
			clone.setVector((SpeedVector) v.clone());
			return clone;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	catch(NullPointerException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Movement getGravity(){
		try {
			return (Gravity) gravity.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}catch(NullPointerException e){
			e.printStackTrace();
			return null;
		}
	}
}
