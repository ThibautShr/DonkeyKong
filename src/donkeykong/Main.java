package donkeykong;

import java.util.ArrayList;

import donkeykong.levels.*;
import gameframework.game.GameDefaultImpl;
import gameframework.game.GameLevel;

public class Main {

	public static void main(String[] args) {
		GameDefaultImpl g = new GameDefaultImpl();
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();

		levels.add(new LevelOne(g));
		
		g.setLevels(levels);
		g.start();
	}

}
