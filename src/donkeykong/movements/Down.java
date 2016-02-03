package donkeykong.movements;

import java.awt.Point;

public class Down extends MovementAbstract{
	
	@Override
	public Point nextStep() {
		this.setOnGoing(false);
		return new Point(0, 1);
	}
	
	public Down(){
		super();
	}
	
	public Object clone() throws CloneNotSupportedException{
		try {
			Down clone = (Down) super.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			throw new CloneNotSupportedException();
		}
		
		
	}
}
