package donkeykong.rules;

import java.util.Random;

import donkeykong.entities.Barrel;
import donkeykong.entities.Platform;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.IllegalMoveException;
import gameframework.game.MoveBlockerRulesApplierDefaultImpl;
import reimplementationFramework.StrategyPattern;

public class BarrelMoveBlockers extends MoveBlockerRulesApplierDefaultImpl {
	
	
	public void moveBlockerRule() throws IllegalMoveException {
	}
	
	public void moveBlockerRule(Barrel b, Platform w){
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
		
	}

}
