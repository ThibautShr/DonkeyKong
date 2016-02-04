package donkeykong.levels;

import gameframework.game.Game;

public class LevelTwo extends LevelAbstract{


	public static final String BACKGROUND = "background_jungle.png";
	public static final String LADDER = "ladder_jungle.png";
	public static final String PLATFORM = "platform_jungle.png";
	
	public LevelTwo(Game g) {
		super(g);
	}

	@Override
	public void init() {
		initTab();
		addRules();
		drawGameBoard();
		addPlatforms(5);
		addLadders(9);
		addHoles(5);
		try {
			addEntities(BACKGROUND,LADDER,PLATFORM);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMario();
		addDonkeyKong();
		addPeach();
		addBarrel(7);
	}
}
