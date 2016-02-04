package donkeykong.movements;

import gameframework.base.SpeedVector;

public interface FactoryMovement {
	
	public Movement getUp();
	
	public Movement getDown();
	
	public Movement getLeft();
	
	public Movement getRight();

	public Movement getJump(SpeedVector v);
	
	public Movement getGravity();

}
