package donkeykong.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

public class BackGround implements Drawable, GameEntity, Overlappable, Cloneable{
	private DrawableImage image = null;
	private Point position; 
	public static final int RENDERING_SIZE = 30;

	public BackGround(Canvas defaultCanvas, Point position) {
		image = new DrawableImage("images/background_jungle.png", defaultCanvas);
		this.position = position;
	}
	
	public void setImage(DrawableImage image){
		this.image = image;
	}
	
	public BackGround clone() throws CloneNotSupportedException{
		return (BackGround) super.clone();
	}

	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}

	public void draw(Graphics g) {
		g.drawImage(image.getImage(), (int) getPosition().getX(),
				(int) getPosition().getY(), RENDERING_SIZE, RENDERING_SIZE,
				null);

	}

	public Rectangle getBoundingBox() {
		return (new Rectangle((int) position.getX(), (int) position.getY(),
				RENDERING_SIZE, RENDERING_SIZE));
	}
}
