package donkeykong.movements;

import java.awt.Point;

public class Jump extends MovementAbstract{

	int step;
	Point direction; 
	public static final int MAX_STEP = 6;


	public Jump(){
		step = 0;
		this.direction = new Point(0,0);
	}
	
	public void init() {
		super.init();
		step = 0;
	}
	
	public Point nextStep(){
		Point last_direction = this.getVector().getDirection();
		
		//System.out.println("x : " + last_direction.getX());
		if(step < MAX_STEP / 2) // increase phase
			direction.setLocation(last_direction.getX(), -1);
		else if(step < MAX_STEP)// decrease phase
			direction.setLocation(last_direction.getX(), 1);
		
		if(step == MAX_STEP - 1)
			this.setOnGoing(false);
		else
			step++;
		
		//System.out.println("x : " + direction.getX() + ", y : " + direction.getY());
		return direction;
	}
	
	public Movement clone() throws CloneNotSupportedException{
	
		try {
			Jump clone = (Jump) super.clone();
			clone.direction = (Point) this.direction.clone();
			clone.step = this.step;
			return clone;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CloneNotSupportedException();
		}
		
	}
	

}
