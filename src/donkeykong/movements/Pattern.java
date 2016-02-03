package donkeykong.movements;

import java.awt.Point;
import java.util.List;

import gameframework.base.SpeedVector;

public class Pattern implements Movement {

	private List<Movement> pattern;
	private PrototypeMovement protoMvt;
	
	public Pattern(PrototypeMovement pm){
	
		protoMvt = pm;
		
	}

	@Override
	public Point nextStep() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpeedVector getVector() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVector(SpeedVector v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean OnGoing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setOnGoing(boolean b) {
		// TODO Auto-generated method stub
		
	}
	
}
