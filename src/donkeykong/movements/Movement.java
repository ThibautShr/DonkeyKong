package donkeykong.movements;

import java.awt.Point;

import gameframework.base.SpeedVector;

public interface Movement extends Cloneable{

	Point nextStep();
	SpeedVector getVector();
	void setVector(SpeedVector v);
	void init();
	boolean OnGoing();
	void setOnGoing(boolean b);
	Object clone() throws CloneNotSupportedException;
}
