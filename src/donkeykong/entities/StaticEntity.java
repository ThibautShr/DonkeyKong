package donkeykong.entities;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

public abstract class StaticEntity implements Drawable, GameEntity, Overlappable{

	
	private DrawableImage image = null;
	private Point position; 
	private static final int RENDERING_SIZE = 30;
	
	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle((int) position.getX(), (int) position.getY(),
				RENDERING_SIZE, RENDERING_SIZE));
	}

	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image.getImage(), (int) getPosition().getX(),
				(int) getPosition().getY(), RENDERING_SIZE, RENDERING_SIZE,
				null);
		
	}
	
	public int getRenderingSize(){
		return RENDERING_SIZE;
	}

	public DrawableImage getImage() {
		return image;
	}

	public void setImage(DrawableImage image) {
		this.image = image;
	}
	
	
	

}
