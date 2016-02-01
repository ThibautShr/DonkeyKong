package donkeykong.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.sound.midi.Synthesizer;

import donkeykong.movements.Jump;
import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.base.SpeedVector;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManagerDefaultImpl;

public class Mario extends GameMovable implements Drawable, GameEntity, Overlappable {
	
	protected static DrawableImage image = null;
	protected boolean movable = true;
	protected boolean active = true;
	private final SpriteManagerDefaultImpl spriteManager;
	public static final int RENDERING_SIZE = 30;
	public static final int NB_ELEMENT_BY_ROW = 5;
	private Point position;
	private SpeedVector lastSpeedVector;
	private String lastSpriteType;
	private Jump jump;

	public Mario(Canvas defaultCanvas) {
		spriteManager = new SpriteManagerDefaultImpl("images/mario.png",
				defaultCanvas, RENDERING_SIZE, NB_ELEMENT_BY_ROW);
		spriteManager.setTypes(
				"right",
				"left",
				"motion2",
				"motion3");
		
		//jump = new Jump();
		lastSpriteType = "right";
	}
	
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE, NB_ELEMENT_BY_ROW));
	}

	@Override
	public void draw(Graphics g) {
		movable = true;

		String spriteType = lastSpriteType;

		Point direction = getSpeedVector().getDirection();
		int speed = getSpeedVector().getSpeed();
		
		position.setLocation(position.getX() + direction.getX() * speed , position.getY() + direction.getY() * speed);
		
		if (direction.getX() == 1) { // right
			spriteType = "right";
		} else if (direction.getX() == -1) { // left
			spriteType = "left";
		} else if (direction.getY() == 1) { // top
			spriteType = lastSpriteType;
		} else if (direction.getY() == -1) { // bottom
			spriteType = lastSpriteType;
		} else if (direction.getY() == -2) { // jump
			spriteType = lastSpriteType; // bottom
		}else{
			spriteManager.reset();
			movable = false;
		}
		
		lastSpriteType = spriteType;
		
		spriteManager.setType(spriteType);
		spriteManager.draw(g, getPosition());
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		if (movable) {
			spriteManager.increment();
		}
	}
}
