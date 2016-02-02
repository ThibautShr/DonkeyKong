package donkeykong.movements;

import java.awt.Point;

public class Left extends MovementAbstract{
	
	@Override
	public Point nextStep() {
		this.setOnGoing(false);
		return new Point(-1, 0);
	}
	
	public Left(){
		super();
	}
	
	
	public Movement clone() throws CloneNotSupportedException{
		
		try {
			Left clone = (Left) super.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CloneNotSupportedException();
		}
		
	}
}
