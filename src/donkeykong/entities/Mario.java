package donkeykong.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManagerDefaultImpl;

public class Mario extends GameMovable implements Drawable, GameEntity,Overlappable {
	
	protected static DrawableImage image = null;
	protected boolean movable = true;
	protected boolean active = true;
	private final SpriteManagerDefaultImpl spriteManager;
	public static final int RENDERING_SIZE = 30;
	public static final int NB_ELEMENT_BY_ROW = 5;
	private Point position;

	public Mario(Canvas defaultCanvas) {
		spriteManager = new SpriteManagerDefaultImpl("images/mario.png",
				defaultCanvas, RENDERING_SIZE, NB_ELEMENT_BY_ROW);
		spriteManager.setTypes(
				"right",
				"left",
				"motion2",
				"motion3");
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
		Point tmp = getSpeedVector().getDirection();
		movable = true;

		String spriteType = "right";

		//System.out.println("x : " + tmp.getX() + ", y : " + tmp.getY());
		
		if (tmp.getX() == 1) { // right
			spriteType = "right"; 
		} else if (tmp.getX() == -1) { // left
			spriteType = "left";
		} else if (tmp.getY() == 1) { // bottom
			spriteType = "left";
		} else if (tmp.getY() == -1) {
			spriteType = "left"; // top
		}else{
			spriteManager.reset();
			movable = false;
		}

		spriteManager.setType(spriteType);
		spriteManager.draw(g, getPosition());
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		// TODO Auto-generated method stub
		
	}

}
