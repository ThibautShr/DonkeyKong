package donkeykong.movements;

import java.awt.Point;

public class Gravity extends MovementAbstract{

	public Gravity(){
		onGoing = true;
	}
	
	@Override
	public Point nextStep() {
		return new Point(0, 1);
	}

}
