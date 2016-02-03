package donkeykong.rules;

import java.awt.Point;
import java.util.Random;
import java.util.Vector;

import donkeykong.entities.Barrel;
import donkeykong.entities.Ladder;
import donkeykong.entities.Mario;
import donkeykong.entities.Peach;
import donkeykong.entities.Platform;
import gameframework.base.ObservableValue;
import gameframework.base.Overlap;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.IllegalMoveException;
import gameframework.game.OverlapRulesApplierDefaultImpl;
import reimplementationFramework.StrategyPattern;

public class MarioOverlapRules extends OverlapRulesApplierDefaultImpl {

	protected GameUniverse universe;
	
	protected Point  marioStartPos;
	protected boolean manageMarioDeath;
	private final ObservableValue<Integer> score;
	private final ObservableValue<Integer> life;
	private final ObservableValue<Boolean> endOfGame;
	
	@Override
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}
	
	public MarioOverlapRules(Point marioStartPos,
			ObservableValue<Integer> life, ObservableValue<Integer> score,
			ObservableValue<Boolean> endOfGame) {
		marioStartPos = (Point) marioStartPos.clone();
		this.life = life;
		this.score = score;
		this.endOfGame = endOfGame;
	}
	
	public void overlapRule(Mario m, Ladder l) {
		//System.out.println("ladder");
		setChanged();
		notifyObservers();
	}
	
	public void overlapRule(Mario m, Barrel b){		
		System.out.println("choc");
		life.setValue(0);
		endOfGame.setValue(false);
	}
	
	public void overlapRule(Mario m, Peach p){		
		System.out.println("fuck peach");
		life.setValue(1);
		endOfGame.setValue(true);
	}
	
	public void overlapRule(Barrel b, Ladder l) {
		System.out.println("OverlapRule : Barrel , Ladder");
		GameMovableDriverDefaultImpl g = new GameMovableDriverDefaultImpl();
		g.setStrategy(new StrategyPattern(b.getMoveDown(), b.getSpeedVector()));
		b.setDriver(g);
	}
	
	
}