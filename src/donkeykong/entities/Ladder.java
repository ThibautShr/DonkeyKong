package donkeykong.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import gameframework.base.DrawableImage;

public class Ladder extends StaticEntity implements Cloneable {
	public static final int RENDERING_SIZE = 16;

	public Ladder(Canvas defaultCanvas, Point position) {
		super();
		this.setImage(new DrawableImage("images/ladder_jungle.png", defaultCanvas));
		this.setPosition(position);
	}
		
	public void draw(Graphics g) {
		g.drawImage(this.getImage().getImage(), (int) getPosition().getX(),
				(int) getPosition().getY(), RENDERING_SIZE, RENDERING_SIZE,
				null);

	}
	
	public Ladder clone() throws CloneNotSupportedException{
		return (Ladder) super.clone();
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle((int) this.getPosition().getX(), (int) this.getPosition().getY(),
				RENDERING_SIZE, RENDERING_SIZE));
	}
}
