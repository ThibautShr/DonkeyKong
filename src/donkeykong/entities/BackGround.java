package donkeykong.entities;

import java.awt.Canvas;
import java.awt.Point;
import gameframework.base.DrawableImage;

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
