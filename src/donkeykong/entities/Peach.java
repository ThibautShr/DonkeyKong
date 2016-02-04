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

public class Peach extends StaticEntity implements Drawable, GameEntity, Overlappable {


	
	public Peach(Canvas canvas) {
		
		super();
		this.setImage( new DrawableImage("images/peach_static.png", canvas));
		this.setPosition(new Point(0,0));
		// TODO Auto-generated constructor stub
	}



}
