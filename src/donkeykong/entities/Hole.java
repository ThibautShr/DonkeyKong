package donkeykong.entities;

import java.awt.Canvas;
import java.awt.Point;
import gameframework.base.DrawableImage;

public class Hole extends StaticEntity implements Cloneable{
	

	public Hole(Canvas defaultCanvas, Point position) {
		super();
		this.setImage( new DrawableImage("images/background_jungle.png", defaultCanvas));
		this.setPosition(position);
	}
	

	public Hole clone() throws CloneNotSupportedException{
		return (Hole) super.clone();
	}
}
