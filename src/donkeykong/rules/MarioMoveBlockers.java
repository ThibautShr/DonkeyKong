package donkeykong.rules;

import donkeykong.entities.Barrel;
import donkeykong.entities.Mario;
import donkeykong.entities.Platform;
import donkeykong.entities.Wall;
import gameframework.game.IllegalMoveException;
import gameframework.game.MoveBlockerRulesApplierDefaultImpl;
import reimplementationFramework.GameDriverReimplDefault;
import reimplementationFramework.StrategyPattern;

public class MarioMoveBlockers extends MoveBlockerRulesApplierDefaultImpl{

	public void moveBlockerRule() throws IllegalMoveException {
	}
	
	public void moveBlockerRule(Mario m, Platform p) throws IllegalMoveException {
		setChanged();
		notifyObservers();		
		throw new IllegalMoveException();
	}
	
	public void moveBlockerRule(Barrel b, Wall w) throws IllegalMoveException {
		GameDriverReimplDefault g = b.getDriver();
		
		if(b.getSpeedVector().getDirection().getX() == - 1){
			g.setStrategy(new StrategyPattern(b.getMoveRight(), b.getSpeedVector()));
		}
		else if(b.getSpeedVector().getDirection().getX() == 1){
			g.setStrategy(new StrategyPattern(b.getMoveLeft(), b.getSpeedVector()));
		}else if(b.getSpeedVector().getDirection().getY() == 1){
			g.setStrategy(new StrategyPattern(b.getHorizontalRandomMove(), b.getSpeedVector()));
		}
		
		throw new IllegalMoveException();
	}
	
	public void moveBlockerRule(Barrel b, Platform p) throws IllegalMoveException {
		
		GameDriverReimplDefault g = b.getDriver();
		
		if(b.getSpeedVector().getDirection().getY() == 1)
			g.setStrategy(new StrategyPattern(b.getHorizontalRandomMove(), b.getSpeedVector()));
		
		throw new IllegalMoveException();
		
	}
	
}
