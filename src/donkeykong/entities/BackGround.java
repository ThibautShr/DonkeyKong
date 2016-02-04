package donkeykong.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

public class BackGround extends StaticEntity implements Cloneable{

	public BackGround(Canvas defaultCanvas, Point position) {
		super();
		this.setImage( new DrawableImage("images/background_jungle.png", defaultCanvas));
		this.setPosition(position);
	}
	
	
	public BackGround clone() throws CloneNotSupportedException{
		return (BackGround) super.clone();
	}

}
