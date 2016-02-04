package donkeykong.movements;

import gameframework.base.SpeedVector;

public abstract class MovementAbstract implements Movement{
	private boolean onGoing;
	private SpeedVector v;

	public MovementAbstract(){
		onGoing = true;
	}
	
	public void init() {
		onGoing = true;
	}
	
	public SpeedVector getVector() {
		return v;
	}

	public void setVector(SpeedVector v) {
		this.v = v;
	}
	
	public boolean OnGoing(){
		return onGoing;
	}
	
	public void setOnGoing(boolean b){
		onGoing = b;
	}
	
	public Object clone() throws CloneNotSupportedException{
		Movement m;
		try {
			m = (Movement) super.clone();
			m.setOnGoing(onGoing);
			if(v != null)
				m.setVector((SpeedVector)v.clone());
			else{
				m.setVector(null);
			}
			return m;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CloneNotSupportedException();
		}
		
	}
}
