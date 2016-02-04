package donkeykong.movements;

import java.awt.Point;

public class Gravity extends MovementAbstract{
	
	int step;
	
	public Gravity(){
		super();
		step = 0;
	}
	
	@Override
	public Point nextStep() {
		System.out.println("gravity");
		step++;
		return new Point(0, 1);
	}
	
	public void init() {
		super.init();
		step = 0;
	}
	
	public void setOnGoing(boolean b){
		super.setOnGoing(b);
		step = 0;
	}
	
	public int getStep(){
		return step;
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
