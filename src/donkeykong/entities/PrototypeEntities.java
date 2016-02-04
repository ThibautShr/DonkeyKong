package donkeykong.entities;

import java.awt.Canvas;
import java.awt.Point;

public class PrototypeEntities {

	BackGround background;
	Barrel barrel;
	DonkeyKong dk;
	Ladder ladder;
	Mario mario;
	Platform platform;
	Wall wall;
	Hole hole;
	
	public Wall getWall() throws CloneNotSupportedException {
		return wall.clone();
	}

	public void setWall(Wall wall) {
		this.wall = wall;
	}

	public BackGround getBackground() throws CloneNotSupportedException {
		return background.clone();
	}

	public void setBackground(BackGround background) {
		this.background = background;
	}

	public Barrel getBarrel() {
		return barrel;
	}

	public void setBarrel(Barrel barrel) {
		this.barrel = barrel;
	}

	public DonkeyKong getDk() {
		return dk;
	}

	public void setDk(DonkeyKong dk) {
		this.dk = dk;
	}

	public Ladder getLadder() throws CloneNotSupportedException {
		return ladder.clone();
	}

	public void setLadder(Ladder ladder) {
		this.ladder = ladder;
	}

	public Mario getMario() {
		return mario;
	}

	public void setMario(Mario mario) {
		this.mario = mario;
	}

	public Platform getPlatform() throws CloneNotSupportedException {
		return platform.clone();
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public Hole getHole() throws CloneNotSupportedException {
		return hole.clone();
	}

	public void setHole(Hole hole) {
		this.hole = hole;
	}
	
	public PrototypeEntities(Canvas canvas){
		background = new BackGround(canvas, new Point(0, 0));
		hole = new Hole(canvas, new Point(0, 0));
		ladder = new Ladder(canvas, new Point(0, 0));
		platform = new Platform(canvas, 0, 0);
		wall = new Wall(canvas, 0, 0);
	}
}
