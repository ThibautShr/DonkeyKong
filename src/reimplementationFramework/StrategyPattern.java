package reimplementationFramework;

import java.awt.Point;

import donkeykong.movements.Movement;
import donkeykong.movements.Pattern;
import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

public class StrategyPattern implements MoveStrategy{

	
	protected Pattern pattern;
	protected SpeedVector v;
	
	public StrategyPattern(Pattern p, SpeedVector sv) {
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
			v.setDirection(pattern.nextStep());
			return v;
		}
		v.setDirection(new Point(0,0));
		return v;
		
	}

}
