package donkeykong.rules;

import java.util.Random;

import donkeykong.entities.Barrel;
import donkeykong.entities.Mario;
import donkeykong.entities.Platform;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.IllegalMoveException;
import gameframework.game.MoveBlockerRulesApplierDefaultImpl;
import reimplementationFramework.StrategyPattern;

public class MarioMoveBlockers extends MoveBlockerRulesApplierDefaultImpl{

	public void moveBlockerRule() throws IllegalMoveException {
	}
	
	public void moveBlockerRule(Mario m, Platform p) throws IllegalMoveException {
		setChanged();
		notifyObservers();		
		throw new IllegalMoveException();
	}
	
	public void moveBlockerRule(Barrel b, Platform w) throws IllegalMoveException {
		System.out.println("mvbRule : Barrel , Platform");
		GameMovableDriverDefaultImpl g = new GameMovableDriverDefaultImpl();
		if(b.getSpeedVector().getDirection().getY() > 0){
			 Random randomGenerator = new Random();
			 
			 System.out.println(randomGenerator.nextInt(2));
			g.setStrategy(new StrategyPattern(b.getMoveRight(), b.getSpeedVector()));
		}
		else if(b.getSpeedVector().getDirection().getX() <= 0){
			g.setStrategy(new StrategyPattern(b.getMoveRight(), b.getSpeedVector()));
		}
		else if(b.getSpeedVector().getDirection().getX() > 0){
			g.setStrategy(new StrategyPattern(b.getMoveLeft(), b.getSpeedVector()));
		}
		
		b.setDriver(g);
		
		throw new IllegalMoveException();
		
	}
	
	/*public void moveBlockerRule(Mario m, Barrel b) throws IllegalMoveException {
		//setChanged();
		//notifyObservers();		
		System.out.println("choc");
		throw new IllegalMoveException();
	}*/
}
