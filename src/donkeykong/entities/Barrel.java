package donkeykong.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.game.GameEntity;
import gameframework.game.MoveBlocker;
import gameframework.game.SpriteManagerDefaultImpl;

public class Barrel implements Drawable, GameEntity, MoveBlocker {
	
	protected boolean movable = false;
	protected boolean active = true;
	private Point position;
	private final SpriteManagerDefaultImpl spriteManager;
	public static final int RENDERING_SIZE = 18;
	public static final int NB_ELEMENT_BY_ROW = 4;
	
	public Barrel(Canvas canvas , Point position) {
		
		spriteManager = new SpriteManagerDefaultImpl("images/barrels.png",
				canvas, RENDERING_SIZE, NB_ELEMENT_BY_ROW);
		this.position = (Point) position.clone();
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

	
}
