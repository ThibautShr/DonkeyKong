package donkeykong.movements;

import java.awt.Point;

public class Up extends MovementAbstract{

	@Override
	public Point nextStep() {
		this.setOnGoing(false);
		return new Point(0, -1);
	}
	
	public Up(){
		super();
	}
	
	public Movement clone() throws CloneNotSupportedException{
		try {
			Up clone = (Up) super.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			throw new CloneNotSupportedException();
		}
		
	}

}
