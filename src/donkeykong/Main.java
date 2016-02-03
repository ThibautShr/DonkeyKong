package donkeykong;

import java.util.ArrayList;

import donkeykong.levels.*;
import gameframework.game.GameDefaultImpl;
import gameframework.game.GameLevel;

public class Main {

	public static void main(String[] args) {
		GameDefaultImpl g = new GameDefaultImpl();
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();

		LevelOne l1 = new LevelOne(g);
		LevelTwo l2 = new LevelTwo(g);
		LevelThree l3 = new LevelThree(g);
		
		levels.add(l3);
		levels.add(l2);
		levels.add(l3);
		
		g.setLevels(levels);

		g.start();
	}

}
