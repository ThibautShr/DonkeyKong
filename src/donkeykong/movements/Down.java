package donkeykong.movements;

import java.awt.Point;

public class Down extends MovementAbstract{
	
	@Override
	public Point nextStep() {
		onGoing = false;
		return new Point(0, 1);
	}
	
	public Down(){
		onGoing = true;
	}
}
