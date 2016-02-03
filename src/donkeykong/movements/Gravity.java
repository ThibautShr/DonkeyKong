package donkeykong.movements;

import java.awt.Point;

public class Gravity extends MovementAbstract{

	public Gravity(){
		super();
	}
	
	@Override
	public Point nextStep() {
		return new Point(0, 1);
	}
	
	public Movement clone() throws CloneNotSupportedException {
		try {
			Gravity clone = (Gravity) super.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CloneNotSupportedException();
		}
		
		
	}

}
