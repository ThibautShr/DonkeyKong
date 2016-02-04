package donkeykong.movements;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gameframework.base.SpeedVector;

public class Pattern implements Movement {

	private List<Movement> pattern;
	private PrototypeMovement protoMvt;
	private boolean onGoing;
	Iterator<Movement> ite;
	private Movement current;
	
	public Pattern(PrototypeMovement pm){
		pattern = new ArrayList<Movement>();
		onGoing = false;
		protoMvt = pm;
		
	}

	public Movement getCurrent(){
		return current;
	}
	
	@Override
	public Point nextStep() {
		
		if(current != null){
			if(current.OnGoing()){
				Point res = current.nextStep();
				 if(!current.OnGoing())
					 onGoing =  ite.hasNext();
				 return res;
			}
			else{
				if(ite.hasNext()){
					current = ite.next();
					current.init();
					return this.nextStep();
				}
				else{
					onGoing = false;
					return new Point(0,0);
				}
			}
		}
		else{
			return new Point(0,0);
		}
	}

	
	public SpeedVector getVector() {
		return current.getVector();
	}

	public void setVector(SpeedVector v) {
		Iterator<Movement> iterator = pattern.iterator();
		while(iterator.hasNext()){
			iterator.next().setVector(v);
		}
		
	}

	public void init() {
		onGoing = true;
		ite = pattern.iterator();
		if(ite.hasNext())
			current = ite.next();
	}

	@Override
	public boolean OnGoing() {
		// TODO Auto-generated method stub
		return onGoing;
	}

	@Override
	public void setOnGoing(boolean b) {
		onGoing = b;
	}
	
	
	public void addMovementUp(){
		this.pattern.add(this.protoMvt.getUp());
	}
	
	public void addMovementDown(){
		this.pattern.add(this.protoMvt.getDown());
	}
	
	public void addMovementLeft(){
		this.pattern.add(this.protoMvt.getLeft());
	}
	
	public void addMovementRight(){
		this.pattern.add(this.protoMvt.getRight());
	}
	
	public Object clone() throws CloneNotSupportedException{
		
		try {
			Pattern clone =  (Pattern ) super.clone();
			clone.current =  (Movement) this.current.clone();
			Iterator<Movement> iterator = this.pattern.iterator();
			if(pattern != null){
				clone.pattern = new ArrayList<Movement>();
				while(ite.hasNext()){
					clone.pattern.add((Movement) iterator.next().clone());
				}
			}
			
			return clone;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CloneNotSupportedException();
		}
		
	}
}
