package donkeykong.levels;

import java.awt.Canvas;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import donkeykong.entities.*;
import donkeykong.movements.PrototypeMovementDefaultImpl;
import donkeykong.rules.MarioMoveBlockers;
import donkeykong.rules.MarioOverlapRules;
import gameframework.base.DrawableImage;
import gameframework.base.SpeedVectorDefaultImpl;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
import gameframework.game.GameEntity;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.GameUniverseViewPortDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
import gameframework.game.OverlapProcessor;
import gameframework.game.OverlapProcessorDefaultImpl;
import reimplementationFramework.GameDriverReimplDefault;
import reimplementationFramework.StrategyKeyboard;
import reimplementationFramework.StrategyPattern;

public abstract class LevelAbstract extends GameLevelDefaultImpl implements Level{
	
	Canvas canvas;

	private static final int SPRITE_SIZE = 16;
	private static final int NB_ROWS = 31;
	private static final int NB_COLUMNS = 28;
	private static final int MIN_COLUMN_HEIGHT = 3;
	
	private static final int BACKGRAND = 0;
	private static final int PLATFORM = 1;
	private static final int LADDER = 2;
	private static final int WALL = 3;
	private static final int HOLE = 4;
	
	private static final int HOLE_SIZE = 2;
	private static final int WEIGHT_LADDER = 2;
	
	private ArrayList<Integer> indexPlatforms;
	private MarioOverlapRules marioOverlapRules;
	private MoveBlockerChecker moveBlockerChecker;
	private MarioMoveBlockers marioMoveBlockers;
	private PrototypeEntities prototypeEntities;
	
	private long time;

	
	private static int[][] tab;
	
	public LevelAbstract(Game g) {
		super(g);
		canvas = g.getCanvas();
		time = 3000;
	}
	
	public void run(){
		TimerTask timerBarrel = new TimerTask() {
            @Override
            public void run() {
                addBarrel(1);
            }
        };
		
		Timer timer = new Timer("Barrel Timer");
		timer.scheduleAtFixedRate(timerBarrel, 30, time);
		
		super.run();
	}
	
	public void setTimeInterval(long time){
		this.time = time; 
	}
	
	public void initUniverse(){
		if(universe != null){
			Iterator<GameEntity> entity = universe.gameEntities();
			while(entity.hasNext()){
				GameEntity gameEntity = entity.next();
				universe.removeGameEntity(gameEntity);
			}
		}
	}
	
	public void initTab(){
		tab = new int[NB_ROWS][NB_COLUMNS];
		for(int i=0; i<NB_ROWS; ++i){
			for(int j=0; j<NB_COLUMNS; ++j){
				if((j==0 || j==NB_COLUMNS - 1))
					tab[i][j] = WALL;
				else
					tab[i][j] = BACKGRAND;
			}
		}
	}
	
	public int[][] getTab(){
		return tab.clone();
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
	
	public void makeBlock(int line){
		int column;
		do{
			column = (int) (Math.random() * (NB_COLUMNS - 3)) + 1;
		}while(tab[line][column] == LADDER || tab[line][column] == HOLE);
		
		tab[line-1][column] = WALL;
	}
	
	public void addBlock(int nb){
		int platformIndex;
		while(nb > 0){
			int max = indexPlatforms.size() - 2;
			int min = 1;
			platformIndex = (int) (Math.random() * (max - min) + min); //We can't make a block on the Donkey and Mario  platform
			makeBlock(indexPlatforms.get(platformIndex));
			nb--;
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
			randomInterval = (int) (Math.random() * (maxInterval - minInterval) + minInterval);
			currentPlatformLine -= randomInterval + 1;
			makeHorizontalLine(PLATFORM,currentPlatformLine);
			numberLinesRemaining -= randomInterval + 1;
			indexPlatforms.add(currentPlatformLine);
		}
	}

	public void makeLadder(int i, int j){
		int randomColumn = (int) (Math.random() * (NB_COLUMNS - 3 - WEIGHT_LADDER) + 1);
		while(i > j){
			tab[i][randomColumn] = LADDER;
			for(int k = 1;k < WEIGHT_LADDER;++k)
				tab[i][randomColumn + k] = LADDER;
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
	
	public void makeHole(int row,int column,int size){
		for(int i=0; i<size; ++i){
			tab[row-1][column + i] = HOLE;
			tab[row][column + i] = HOLE;
		}
	}
	
	public void addHoles(int nb){
		makeHole(indexPlatforms.get(0),1,HOLE_SIZE);
		makeHole(indexPlatforms.get(0),NB_COLUMNS -1 - HOLE_SIZE,HOLE_SIZE);
		
		int platformIndex, holePosition;
		while(nb > 0){
			platformIndex = (int) (Math.random() * (indexPlatforms.size() - 1) + 1);
			holePosition = (int) (Math.random() * (NB_COLUMNS - 1));
			makeHole(indexPlatforms.get(platformIndex),holePosition, HOLE_SIZE);
			nb--;
		}
		
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
		DrawableImage wallDrawableImage = new DrawableImage("images/" + platformImage, canvas);
		DrawableImage holeDrawableImage = new DrawableImage("images/" + backgroundImage, canvas);
		
		BackGround background;
		Platform platform;
		Ladder ladder;
		Wall wall;
		Hole hole;
		for (int i = 0; i < NB_ROWS; ++i) {
			for (int j = 0; j < NB_COLUMNS; ++j) {
				switch(tab[i][j]){
					case BACKGRAND : 
						background = prototypeEntities.getBackground();
						background.setPosition(new Point(j * SPRITE_SIZE, i * SPRITE_SIZE));
						background.setImage(backgroundDrawableImage);
						universe.addGameEntity(background);
						break;
					case PLATFORM :
						platform = prototypeEntities.getPlatform();
						platform.setPosition(new Point(j * SPRITE_SIZE, i * SPRITE_SIZE));
						platform.setImage(platformDrawableImage);
						universe.addGameEntity(platform);
						break;
					case WALL :
						wall = prototypeEntities.getWall();
						wall.setPosition(new Point(j * SPRITE_SIZE, i * SPRITE_SIZE));
						wall.setImage(wallDrawableImage);
						universe.addGameEntity(wall);
						break;
					case LADDER :
						ladder = prototypeEntities.getLadder();
						ladder.setPosition(new Point(j * SPRITE_SIZE, i * SPRITE_SIZE));
						ladder.setImage(ladderDrawableImage);
						universe.addGameEntity(ladder);
						break;
					case HOLE :
						hole = prototypeEntities.getHole();
						hole.setPosition(new Point(j * SPRITE_SIZE, i * SPRITE_SIZE));
						hole.setImage(holeDrawableImage);
						universe.addGameEntity(hole);
						break;
				}
			}
		}
		
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
		mario.initPosition();
		universe.addGameEntity(mario);
	}
	
	public void addDonkeyKong(){
		DonkeyKong dk = new DonkeyKong(canvas);
		int dkHeight = (int) dk.getBoundingBox().getHeight();
		dk.setPosition(new Point(1 * SPRITE_SIZE, (indexPlatforms.get(indexPlatforms.size()-1)) * SPRITE_SIZE - dkHeight));
		universe.addGameEntity(dk);
	}
	
	public void addBarrel(int nb){
		for(int i=0; i<nb; ++i){
			GameDriverReimplDefault barrelDriver = new GameDriverReimplDefault();
			Barrel b = new Barrel(canvas,new PrototypeMovementDefaultImpl());
			
			StrategyPattern sp = new StrategyPattern(b.getMoveRight(), new SpeedVectorDefaultImpl(new Point(0,0)));
			
			barrelDriver.setStrategy(sp);
			barrelDriver.setmoveBlockerChecker(moveBlockerChecker);
			b.setDriver(barrelDriver);
			
			int barrelHeight = (int) b.getBoundingBox().getHeight();
			b.setPosition(new Point(20 * i + 2 * SPRITE_SIZE , (indexPlatforms.get(indexPlatforms.size()-1)) * SPRITE_SIZE  - barrelHeight));
			universe.addGameEntity(b);
		}
	}

	public void addPeach(){
		Peach peach = new Peach(canvas);
		int peachHeight = (int) peach.getBoundingBox().getHeight();
		peach.setPosition((new Point(10 * SPRITE_SIZE, (indexPlatforms.get(indexPlatforms.size()-1)) * SPRITE_SIZE - peachHeight)));
		universe.addGameEntity(peach);
	}
}
