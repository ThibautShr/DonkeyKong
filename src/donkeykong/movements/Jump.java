package donkeykong.movements;

import java.awt.Point;

public class Jump extends MovementAbstract{

	int step;
	Point direction; 
	public static final int MAX_STEP = 6;


	public Jump(){
		step = 0;
		onGoing = true;
		this.direction = new Point(0,0);
	}
	
	public void init() {
		super.init();
		step = 0;
	}
	
	public Point nextStep(){
		Point last_direction = v.getDirection();
		
		if(step < MAX_STEP / 2) // increase phase
			direction.setLocation(last_direction.getX(), -1);
		else if(step < MAX_STEP)// decrease phase
			direction.setLocation(last_direction.getX(), 1);
		
		if(step == MAX_STEP - 1)
			onGoing = false;
		else
			step++;
		
		return direction;
	}

}
