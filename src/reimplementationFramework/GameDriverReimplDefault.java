package reimplementationFramework;

import gameframework.base.Movable;
import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.MoveBlockerChecker;

public class GameDriverReimplDefault  implements GameDriverStrat {

	private GameMovableDriverDefaultImpl driver;
	
	public GameDriverReimplDefault() {
		driver = new GameMovableDriverDefaultImpl();
	}
	
	public String toString(){
		return "GameDriverRimpDefault";
	}
	
	@Override
	public void setStrategy(MoveStrategy s) {
		driver.setStrategy(s);
	}

	@Override
	public SpeedVector getSpeedVector(Movable m) {
		return driver.getSpeedVector(m);
	}
	
	public void setmoveBlockerChecker(MoveBlockerChecker obst) {
		driver.setmoveBlockerChecker(obst);
	}

	
	

	
	
}
