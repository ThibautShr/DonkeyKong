package donkeykong.movements;

import java.awt.Point;

public class Right extends MovementAbstract{

	@Override
	public Point nextStep() {
		onGoing = false;
		return new Point(1,0);
	}
	
	public Right(){
		onGoing = true;
	}
}
