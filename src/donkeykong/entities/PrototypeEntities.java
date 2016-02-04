package donkeykong.entities;

import java.awt.Canvas;
import java.awt.Point;

import gameframework.base.DrawableImage;

public class PrototypeEntities {

	Canvas canvas;
	
	BackGround background;
	Barrel barrel;
	DonkeyKong dk;
	Ladder ladder;
	Mario mario;
	Platform platform;
	Wall wall;
	Hole hole;
	
	public Wall getWall(DrawableImage img, int x, int y) throws CloneNotSupportedException{
		Wall w = wall.clone();
		w.setPosition(new Point(x,y));
		w.setImage(img);
		return w;
	}

	public BackGround getBackground(DrawableImage img, int x, int y) throws CloneNotSupportedException {
		BackGround b = background.clone();
		b.setPosition(new Point(x,y));
		b.setImage(img);
		return b;
	}

	public Ladder getLadder(DrawableImage img, int x, int y) throws CloneNotSupportedException{
		Ladder l = ladder.clone();
		l.setPosition(new Point(x,y));
		l.setImage(img);
		return l;
	}

	public Platform getPlatform(DrawableImage img, int x, int y) throws CloneNotSupportedException{
		Platform p = platform.clone();
		p.setPosition(new Point(x,y));
		p.setImage(img);
		return p;
	}

	public Hole getHole(DrawableImage img, int x, int y) throws CloneNotSupportedException{
		Hole h = hole.clone();
		h.setPosition(new Point(x,y));
		h.setImage(img);
		return h;
	}
	
	public PrototypeEntities(Canvas canvas){
		this.canvas = canvas;
		background = new BackGround(canvas, new Point(0, 0));
		hole = new Hole(canvas, new Point(0, 0));
		ladder = new Ladder(canvas, new Point(0, 0));
		platform = new Platform(canvas, 0, 0);
		wall = new Wall(canvas, 0, 0);
	}
}
