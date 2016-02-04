package donkeykong.levels;

import gameframework.game.Game;

public class LevelThree extends LevelAbstract{

	public static final String BACKGROUND = "background_stone.png";
	public static final String LADDER = "ladder_stone.png";
	public static final String PLATFORM = "platform_stone.png";
	
	public LevelThree(Game g) {
		super(g);
	}

	public void init() {
		initTab();
		addRules();
		drawGameBoard();
		addPlatforms(5);
		addLadders(6);
		addHoles(7);
		addBlock(4);
		try {
			addEntities(BACKGROUND,LADDER,PLATFORM);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		addMario();
		addDonkeyKong();
		addPeach();
		setTimeInterval(3000);
	}
}
