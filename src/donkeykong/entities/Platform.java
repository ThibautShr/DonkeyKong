package donkeykong.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.game.GameEntity;
import gameframework.game.MoveBlocker;

public class Platform implements Drawable, MoveBlocker, GameEntity, Cloneable {
	private DrawableImage image = null;
	int x, y;
	public static final int RENDERING_SIZE = 16;

	public Platform(Canvas defaultCanvas, int x, int y) {
		image = new DrawableImage("images/platform2.png", defaultCanvas);
		this.x = x;
		this.y = y;
	}
	
	public void setImage(DrawableImage image){
		this.image = image;
	}

	public void draw(Graphics g) {
		g.drawImage(image.getImage(), x, y, RENDERING_SIZE, RENDERING_SIZE,
				null);
	}
	
	public Platform clone() throws CloneNotSupportedException{
		return (Platform) super.clone();
	}

	public Point getPos() {
		return (new Point(x, y));
	}
	
	public void setPosition(Point position) {
		this.x = (int) position.getX();
		this.y = (int) position.getY();
	}

	public Rectangle getBoundingBox() {
		//return (new Rectangle(x, y - RENDERING_SIZE, RENDERING_SIZE / 2, RENDERING_SIZE * 2));
		return (new Rectangle(x, y, RENDERING_SIZE, RENDERING_SIZE));
	}
}
