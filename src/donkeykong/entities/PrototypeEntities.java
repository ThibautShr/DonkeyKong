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

	public PrototypeEntities(Canvas canvas){
		background = new BackGround(canvas, new Point(0, 0));
		ladder = new Ladder(canvas, new Point(0, 0));
		platform = new Platform(canvas, 0, 0);
	}
}
