package donkeykong.levels;

import gameframework.game.Game;

public class LevelOne extends LevelAbstract{
	
	public static final String BACKGROUND = "background.png";
	public static final String LADDER = "ladder.png";
	public static final String PLATFORM = "platform.png";

	public LevelOne(Game g) {
		super(g);
	}

	public void init(){
		initTab();
		addRules();
		drawGameBoard();
		addPlatforms(4);
		addLadders(7);
		addHoles(3);
		
		try {
			addEntities(BACKGROUND,LADDER,PLATFORM);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		addMario();
		addDonkeyKong();
		addPeach();
		setTimeInterval(6000);
		addBarrel(1);
	}

}
