package donkeykong.rules;

import java.awt.Point;

import gameframework.base.ObservableValue;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;

public class DonkeyKongOverlapRules extends OverlapRulesApplierDefaultImpl {

	protected GameUniverse universe;
	
	static final int INVULNERABLE_DURATION = 60;
	protected Point donkeyKongStartPos;
	protected boolean manageDonkeyKongDeath;
	private final ObservableValue<Integer> score;
	private final ObservableValue<Integer> life;
	private final ObservableValue<Boolean> endOfGame;
	
	@Override
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}
	
	public DonkeyKongOverlapRules(Point donkeyKongStartPos,
			ObservableValue<Integer> life, ObservableValue<Integer> score,
			ObservableValue<Boolean> endOfGame) {
		donkeyKongStartPos = (Point) donkeyKongStartPos.clone();
		this.life = life;
		this.score = score;
		this.endOfGame = endOfGame;
	}

}
