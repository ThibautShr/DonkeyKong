package donkeykong.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.game.GameEntity;
import gameframework.game.MoveBlocker;

public class Platform implements Drawable, MoveBlocker, GameEntity {
	protected static DrawableImage image = null;
	int x, y;
	public static final int RENDERING_SIZE = 16;

	public Platform(Canvas defaultCanvas, int x, int y) {
		image = new DrawableImage("images/platform.png", defaultCanvas);
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		g.drawImage(image.getImage(), x, y, RENDERING_SIZE, RENDERING_SIZE,
				null);
	}

	public Point getPos() {
		return (new Point(x, y));
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(x, y - RENDERING_SIZE, RENDERING_SIZE / 2, RENDERING_SIZE * 2));
	}
}
