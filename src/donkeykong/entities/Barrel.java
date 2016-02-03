package donkeykong.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import donkeykong.movements.Movement;
import donkeykong.movements.PrototypeMovement;
import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.base.SpeedVector;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.GameMovableDriver;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.MoveBlocker;
import gameframework.game.SpriteManagerDefaultImpl;

public class Barrel extends GameMovable implements Drawable, GameEntity, Overlappable {
	
	protected boolean movable = true;
	protected boolean active = true;
	//private Point position;
	private final SpriteManagerDefaultImpl spriteManager;
	public static final int RENDERING_SIZE = 20;
	public static final int NB_ELEMENT_BY_ROW = 4;
	private Point position;
	private Movement moveCurrent;
	private Movement moveRight;
	private Movement moveDown;
	private Movement moveLeft;
	
	public Barrel(Canvas canvas , PrototypeMovement pm) {
		
		spriteManager = new SpriteManagerDefaultImpl("images/barrels.png",
				canvas, RENDERING_SIZE, NB_ELEMENT_BY_ROW);
		//this.position = (Point) position.clone();
		position = new Point(0, 0);
		moveRight = pm.getRight();
		moveLeft = pm.getLeft();
		moveDown = pm.getDown();
	
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
	}

	@Override
	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point position){
		this.position = position;
	}
	
	public Point move(){
		if(!moveCurrent.OnGoing())
			moveCurrent.init();
		return moveCurrent.nextStep();
	}

	@Override
	public void setDriver(GameMovableDriver driver){
		
		super.setDriver(driver);
	}
	public void draw(Graphics g) {
		movable = true;

		SpeedVector s = getSpeedVector();
		
		Point direction = s.getDirection();
		int speed = s.getSpeed();
		
		position.setLocation(position.getX() + direction.getX() * speed , position.getY() + direction.getY() * speed);
		
		spriteManager.draw(g, getPosition());
	}

	public boolean isMovable() {
		return movable;
	}

	public boolean isActive() {
		return active;
	}

	public SpriteManagerDefaultImpl getSpriteManager() {
		return spriteManager;
	}

	public static int getRenderingSize() {
		return RENDERING_SIZE;
	}

	public static int getNbElementByRow() {
		return NB_ELEMENT_BY_ROW;
	}

	public int getX() {
		return position.x;
	}

	public int getY() {
		return position.y;
	}

	public Movement getMoveCurrent() {
		return moveCurrent;
	}

	public Movement getMoveRight() {
		return moveRight;
	}

	public Movement getMoveDown() {
		return moveDown;
	}

	public Movement getMoveLeft() {
		return moveLeft;
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		if (movable) {
			spriteManager.increment();
		}
		
	}


}
