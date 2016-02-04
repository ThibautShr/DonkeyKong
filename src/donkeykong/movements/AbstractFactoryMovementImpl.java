package donkeykong.movements;

import gameframework.base.SpeedVector;

public class AbstractFactoryMovementImpl implements AbstractFactoryMovement {
	
	public Movement getUp(){
		return new Up();
	}
	
	public Movement getDown(){
		Down d = new Down();
		d.init();
		return d;
	}
	
	public Movement getLeft(){
		Left l = new Left();
		l.init();
		return l;
	}
	
	public Movement getRight(){
		Right r = new Right();
		r.init();
		return r;
	}

	public Movement getJump(SpeedVector v){
		Jump j = new Jump();
		j.init();
		j.setVector(v);
		return j;
	}
	
	public Movement getGravity(){
		Gravity g = new Gravity();
		g.init();
		return g;
	}
}
