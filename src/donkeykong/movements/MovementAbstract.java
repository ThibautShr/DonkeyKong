package donkeykong.movements;

import java.awt.Point;

import gameframework.base.SpeedVector;

public abstract class MovementAbstract implements Movement{
	boolean onGoing;
	SpeedVector v;

	public void init() {
		onGoing = true;
	}
	
	public SpeedVector getVector() {
		return v;
	}

	public void setVector(SpeedVector v) {
		this.v = v;
	}
	
	public boolean OnGoing(){
		return onGoing;
	}
	
	public void setOnGoing(boolean b){
		onGoing = b;
	}
}
