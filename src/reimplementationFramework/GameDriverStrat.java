package reimplementationFramework;

import gameframework.base.MoveStrategy;
import gameframework.game.GameMovableDriver;

public interface GameDriverStrat extends GameMovableDriver {
	public void setStrategy(MoveStrategy s);
}
