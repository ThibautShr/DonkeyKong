package donkeykong.rules;

import java.util.Random;

import donkeykong.entities.Barrel;
import donkeykong.entities.Mario;
import donkeykong.entities.Platform;
import donkeykong.entities.Wall;
import gameframework.game.GameMovableDriverDefaultImpl;
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
		//System.out.println("mvbRule : Barrel , wall");
		
		GameDriverReimplDefault g = b.getDriver();
		
		//System.out.println("B : " );//+ b.getSpeedVector().getDirection().getX() + "," + b.getSpeedVector().getDirection().getY());
		if(b.getSpeedVector().getDirection().getX() == - 1){
			//System.out.println("left");
			g.setStrategy(new StrategyPattern(b.getMoveRight(), b.getSpeedVector()));
		}
		else if(b.getSpeedVector().getDirection().getX() == 1){
			//System.out.println("right");
			g.setStrategy(new StrategyPattern(b.getMoveLeft(), b.getSpeedVector()));
		}
		
		
		/*setChanged();
		notifyObservers();*/
		
		//System.out.println("WALLLLLLLLLLLLLL");
		throw new IllegalMoveException();
	}
	
	public void moveBlockerRule(Barrel b, Platform p) throws IllegalMoveException {
		//System.out.println("mvbRule : Barrel , Platform");
		//GameMovableDriverDefaultImpl g = new GameMovableDriverDefaultImpl();
		
		/*System.out.println("1");
		if(b.getSpeedVector().getDirection().getY() > 0){
			Random randomGenerator = new Random();
			System.out.println("2");
			System.out.println(randomGenerator.nextInt(2));
			g.setStrategy(new StrategyPattern(b.getMoveRight(), b.getSpeedVector()));
		}
		else if(b.getSpeedVector().getDirection().getX() <= 0){
			System.out.println("3");
			g.setStrategy(new StrategyPattern(b.getMoveRight(), b.getSpeedVector()));
		}
		else if(b.getSpeedVector().getDirection().getX() > 0){
			System.out.println("4");
			g.setStrategy(new StrategyPattern(b.getMoveLeft(), b.getSpeedVector()));
		}*/
		
		//if(b.getSpeedVector().getDirection().getX() == 1)
		//	g.setStrategy(new StrategyPattern(b.getMoveRight(), b.getSpeedVector()));
		
		//b.setDriver(g);
		
		/*GameDriverReimplDefault g = b.getDriver();
		
		if(b.getSpeedVector().getDirection().getY() == 1){
			g.setStrategy(new StrategyPattern(b.getMoveRight(), b.getSpeedVector()));
		}*/
		
		GameDriverReimplDefault g = b.getDriver();
		
		if(b.getSpeedVector().getDirection().getY() == 1){
			//System.out.println("top");
			int random = (int) (Math.random() * 10);
			if(random%2 == 0)
				g.setStrategy(new StrategyPattern(b.getMoveRight(), b.getSpeedVector()));
			else
				g.setStrategy(new StrategyPattern(b.getMoveLeft(), b.getSpeedVector()));
		}
		
		/*setChanged();
		notifyObservers();*/
		
		throw new IllegalMoveException();
		
	}
	
	/*public void moveBlockerRule(Mario m, Barrel b) throws IllegalMoveException {
		//setChanged();
		//notifyObservers();		
		System.out.println("choc");
		throw new IllegalMoveException();
	}*/
}
