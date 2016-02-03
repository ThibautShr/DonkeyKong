package donkeykong.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.MoveBlocker;
import gameframework.game.SpriteManagerDefaultImpl;

public class Barrel implements Drawable, GameEntity, Overlappable {
	
	protected boolean movable = true;
	protected boolean active = true;
	//private Point position;
	private final SpriteManagerDefaultImpl spriteManager;
	public static final int RENDERING_SIZE = 16;
	public static final int NB_ELEMENT_BY_ROW = 4;
	int x, y;
	
	public Barrel(Canvas canvas , int x, int y) {
		
		spriteManager = new SpriteManagerDefaultImpl("images/barrels.png",
				canvas, RENDERING_SIZE, NB_ELEMENT_BY_ROW);
		//this.position = (Point) position.clone();
		this.x = x;
		this.y = y;
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(x, y, RENDERING_SIZE, RENDERING_SIZE));
	}

	@Override
	public Point getPosition() {
		return new Point(x,y);
	}
	
	/*public void setPosition(Point position) {
		this.position = position;
	}*/

	public void draw(Graphics g) {
		spriteManager.draw(g, getPosition());
		spriteManager.increment();
	}

}
