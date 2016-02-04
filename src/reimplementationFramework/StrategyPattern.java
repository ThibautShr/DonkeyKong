package reimplementationFramework;

import java.awt.Point;

import donkeykong.movements.FactoryMovement;
import donkeykong.movements.AbstractFactoryMovement;
import donkeykong.movements.Movement;
import donkeykong.movements.PrototypeMovement;
import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

public class StrategyPattern implements MoveStrategy{
	
	protected FactoryMovement factoryMovement = new PrototypeMovement(); // Pattern Protoype  
	//protected FactoryMovement factoryMovement = new AbstractFactoryMovement(); // Pattern Factory

	
	protected Movement gravity = factoryMovement.getGravity();
	protected boolean onOverlappableArea = false;

	protected int cptOverlappableArea = 0;
	
	protected Movement pattern;
	protected SpeedVector v = new SpeedVectorDefaultImpl(new Point(0,0));
	
	public StrategyPattern(Movement p, SpeedVector sv) {
		this.pattern = p;
		if(sv == null)
			this.v = new SpeedVectorDefaultImpl(new Point(0,0));
		else
			this.v = sv;
	}
	
	@Override
	public SpeedVector getSpeedVector() {
		if(pattern != null){
			if(!pattern.OnGoing()){
				pattern.init();
			}
			
			Point move_direction = pattern.nextStep();
			v.setDirection(move_direction);

			return v;
		}

		v.setDirection(new Point(0,0));
		return v;
		
	}
	
	public void setPattern(Movement pattern){
		this.pattern = pattern;
	}

}
