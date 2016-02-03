package donkeykong.levels;

import java.awt.Canvas;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observer;

import donkeykong.entities.BackGround;
import donkeykong.entities.Barrel;
import donkeykong.entities.DonkeyKong;
import donkeykong.entities.Ladder;
import donkeykong.entities.Mario;
import donkeykong.entities.Peach;
import donkeykong.entities.Platform;
import donkeykong.entities.PrototypeEntities;
import donkeykong.rules.MarioMoveBlockers;
import donkeykong.rules.MarioOverlapRules;
import gameframework.base.DrawableImage;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.GameUniverseViewPortDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
import gameframework.game.OverlapProcessor;
import gameframework.game.OverlapProcessorDefaultImpl;
import reimplementationFramework.StrategyKeyboard;

public abstract class LevelAbstract extends GameLevelDefaultImpl implements Level{
	
	Canvas canvas;

	private static final int SPRITE_SIZE = 16;
	private static final int NB_ROWS = 31;
	private static final int NB_COLUMNS = 28;
	private static final int MIN_COLUMN_HEIGHT = 3;
	
	private static final int HOLE = 0;
	private static final int PLATFORM = 1;
	private static final int LADDER = 2;
	
	private ArrayList<Integer> indexPlatforms;
	private MarioOverlapRules marioOverlapRules;
	private MoveBlockerChecker moveBlockerChecker;
	private MarioMoveBlockers marioMoveBlockers;
	private PrototypeEntities prototypeEntities;

	/*private static int[][] tab = { 
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };*/
	
	private static int[][] tab;
	
	public LevelAbstract(Game g) {
		super(g);
		canvas = g.getCanvas();
		initTab();
	}
	
	public void initTab(){
		tab = new int[NB_ROWS][NB_COLUMNS];
		for(int i=0; i<NB_ROWS; ++i){
			for(int j=0; j<NB_COLUMNS; ++j){
				tab[i][j] = HOLE;
			}
		}
	}

	public void makeHorizontalLine(int val, int line){
		for(int i=0; i<NB_COLUMNS; ++i){
			tab[line][i] = val;
		}
	}

	public void makeVerticalLine(int val, int column){
		for(int i=0; i<NB_ROWS; ++i){
			tab[i][column] = val;
		}
	}
	
	public void addPlatforms(int nbPlatform){
		assert(nbPlatform > 2);
		indexPlatforms = new ArrayList<Integer>();
		
		int currentPlatformLine = NB_ROWS - 1;
		int numberLinesRemaining = currentPlatformLine - 1;
		makeHorizontalLine(PLATFORM,currentPlatformLine);
		indexPlatforms.add(currentPlatformLine);
		
		int nbPlatformRemaining, lineWithoutPlatform, minInterval, maxInterval, randomInterval;
		float averageInterval;
		for(int i=1; i<nbPlatform; ++i){
			nbPlatformRemaining =  nbPlatform - i;
			lineWithoutPlatform = numberLinesRemaining - nbPlatformRemaining;
			averageInterval = lineWithoutPlatform / (nbPlatformRemaining + 1);
			minInterval = (int) Math.ceil(averageInterval);
			assert(minInterval < MIN_COLUMN_HEIGHT);
			maxInterval = numberLinesRemaining - nbPlatformRemaining - MIN_COLUMN_HEIGHT * nbPlatformRemaining;
			//System.out.println("min : " + minInterval + " , max : " + maxInterval);
			randomInterval = (int) (Math.random() * (maxInterval - minInterval) + minInterval);
			currentPlatformLine -= randomInterval + 1;
			makeHorizontalLine(PLATFORM,currentPlatformLine);
			numberLinesRemaining -= randomInterval + 1;
			indexPlatforms.add(currentPlatformLine);
		}
	}

	public void makeLadder(int i, int j){
		int randomColumn = (int) (Math.random() * NB_COLUMNS);
		/*int secondColumn = randomColumn;
		if(randomColumn == NB_COLUMNS - 1)
			secondColumn--;
		else
			secondColumn++;*/
		while(i > j){
			tab[i][randomColumn] = LADDER;
			//tab[i][secondColumn] = LADDER;
			i--;
		}
	}
	
	public int getUnderLimit(int i){
		if(i == indexPlatforms.size() - 1)
			return indexPlatforms.get(i-1) - 2;
		else
			return indexPlatforms.get(i+1) - 2;
	}
	
	public void addLadders(int nbLadders){
		assert(nbLadders >= indexPlatforms.size());
		//System.out.println(indexPlatforms.toString());
		int laddersRemaining = nbLadders;
		int end, platformIndex;
		int i=0;
		
		while(laddersRemaining > 0){
			platformIndex = i;
			if(i >= indexPlatforms.size())
				platformIndex = (int) (Math.random() * (indexPlatforms.size() - 1));
			
			end = getUnderLimit(platformIndex);
			
			makeLadder(indexPlatforms.get(platformIndex) - 1, end);
			i++;
			laddersRemaining--;
		}
	}
	
	public void addHoles(int nb){
		
	}
	
	public void addRules(){
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();

		moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		marioMoveBlockers = new MarioMoveBlockers();
		moveBlockerChecker.setMoveBlockerRules(marioMoveBlockers);

		marioOverlapRules = new MarioOverlapRules(new Point(16 * SPRITE_SIZE, 17 * SPRITE_SIZE), life[0], score[0], endOfGame);
		overlapProcessor.setOverlapRules(marioOverlapRules);

		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		marioOverlapRules.setUniverse(universe);
	}
	
	public void drawGameBoard(){
		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
	}

	public void addEntities(String backgroundImage, String ladderImage, String platformImage) throws CloneNotSupportedException{
		prototypeEntities = new PrototypeEntities(canvas);
		
		DrawableImage backgroundDrawableImage = new DrawableImage("images/" + backgroundImage, canvas);
		DrawableImage ladderDrawableImage = new DrawableImage("images/" + ladderImage, canvas);
		DrawableImage platformDrawableImage = new DrawableImage("images/" + platformImage, canvas);
		
		BackGround background;
		Platform platform;
		Ladder ladder;
		for (int i = 0; i < NB_ROWS; ++i) {
			for (int j = 0; j < NB_COLUMNS; ++j) {
				switch(tab[i][j]){
					case HOLE : 
						//System.out.println("Hole");
						//universe.addGameEntity(new BackGround(canvas, new Point(j * SPRITE_SIZE, i * SPRITE_SIZE)));
						background = prototypeEntities.getBackground();
						background.setPosition(new Point(j * SPRITE_SIZE, i * SPRITE_SIZE));
						background.setImage(backgroundDrawableImage);
						universe.addGameEntity(background);
						break;
					case PLATFORM :
						//System.out.println("Platform");
						//universe.addGameEntity(new Platform(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
						platform = prototypeEntities.getPlatform();
						platform.setPosition(new Point(j * SPRITE_SIZE, i * SPRITE_SIZE));
						platform.setImage(platformDrawableImage);
						universe.addGameEntity(platform);
						break;
					case LADDER :
						//universe.addGameEntity(new Ladder(canvas, new Point(j * SPRITE_SIZE, i * SPRITE_SIZE)));
						ladder = prototypeEntities.getLadder();
						ladder.setPosition(new Point(j * SPRITE_SIZE, i * SPRITE_SIZE));
						ladder.setImage(ladderDrawableImage);
						universe.addGameEntity(ladder);
						break;
				}
			}
		}	
		
		//universe.addGameEntity(new Barrel(canvas, 10 * SPRITE_SIZE ,29 * SPRITE_SIZE));
		
	}
	
	public void addMario(){
		Mario mario = new Mario(canvas);
		GameMovableDriverDefaultImpl marioDriver = new GameMovableDriverDefaultImpl();
		StrategyKeyboard keyStr = new StrategyKeyboard();
		
		marioMoveBlockers.addObserver((Observer)keyStr);
		marioOverlapRules.addObserver((Observer)keyStr);
		
		marioDriver.setStrategy(keyStr);
		marioDriver.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		mario.setDriver(marioDriver);
		mario.setPosition(new Point(5 * SPRITE_SIZE, 29 * SPRITE_SIZE));
		universe.addGameEntity(mario);
	}
	
	public void addDonkeyKong(){
		DonkeyKong dk = new DonkeyKong(canvas);
		int dkHeight = (int) dk.getBoundingBox().getHeight();
		dk.setPosition(new Point(1 * SPRITE_SIZE, (indexPlatforms.get(indexPlatforms.size()-1)) * SPRITE_SIZE - dkHeight));
		universe.addGameEntity(dk);
	}

	public void addPeach(){
		Peach peach = new Peach(canvas);
		int peachHeight = (int) peach.getBoundingBox().getHeight();
		peach.setPosition(new Point(10 * SPRITE_SIZE, (indexPlatforms.get(indexPlatforms.size()-1)) * SPRITE_SIZE - peachHeight));
		universe.addGameEntity(peach);
	}
}
