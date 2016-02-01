package donkeykong.movements;

import java.awt.Point;

public class Up extends MovementAbstract{

	@Override
	public Point nextStep() {
		onGoing = false;
		return new Point(0, -1);
	}
	
	public Up(){
		onGoing = true;
	}

}
