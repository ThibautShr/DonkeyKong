package donkeykong.rules;

import donkeykong.entities.Barrel;
import donkeykong.entities.Mario;
import donkeykong.entities.Platform;
import gameframework.game.IllegalMoveException;
import gameframework.game.MoveBlockerRulesApplierDefaultImpl;

public class MarioMoveBlockers extends MoveBlockerRulesApplierDefaultImpl{

	public void moveBlockerRule() throws IllegalMoveException {
	}
	
	public void moveBlockerRule(Mario m, Platform p) throws IllegalMoveException {
		setChanged();
		notifyObservers();		
		throw new IllegalMoveException();
	}
	
	/*public void moveBlockerRule(Mario m, Barrel b) throws IllegalMoveException {
		//setChanged();
		//notifyObservers();		
		System.out.println("choc");
		throw new IllegalMoveException();
	}*/
}
