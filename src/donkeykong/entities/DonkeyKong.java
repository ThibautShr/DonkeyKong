package donkeykong.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManagerDefaultImpl;

public class DonkeyKong extends GameMovable implements Drawable, GameEntity,Overlappable {
	
	protected boolean movable = false;
	protected boolean active = true;
	private Point position;
	private final SpriteManagerDefaultImpl spriteManager;
	public static final int RENDERING_SIZE = 70;
	public static final int NB_ELEMENT_BY_ROW = 10;
	
	public DonkeyKong(Canvas canvas) {
		
		spriteManager = new SpriteManagerDefaultImpl("images/DK2.png",
				canvas, RENDERING_SIZE, NB_ELEMENT_BY_ROW);
		// TODO Auto-generated constructor stub
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
	}

	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}

	public void draw(Graphics g) {
		spriteManager.draw(g, getPosition());
		spriteManager.increment();
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		
	}

}
