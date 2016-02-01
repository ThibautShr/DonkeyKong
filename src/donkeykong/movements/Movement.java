package donkeykong.movements;

import java.awt.Point;

import gameframework.base.SpeedVector;

public interface Movement {

	Point nextStep();
	SpeedVector getVector();
	void setVector(SpeedVector v);
	void init();
	boolean OnGoing();
}
