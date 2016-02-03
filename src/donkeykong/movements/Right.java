package donkeykong.movements;

import java.awt.Point;

public class Right extends MovementAbstract{

	@Override
	public Point nextStep() {
		this.setOnGoing(false);
		return new Point(1,0);
	}
	
	public Right(){
		super();
	}
	
	public Movement clone() throws CloneNotSupportedException{
	
		try {
			Right clone = (Right) super.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			throw new CloneNotSupportedException();
		}
		
	}
	
}
