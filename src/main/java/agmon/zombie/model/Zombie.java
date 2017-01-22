package agmon.zombie.model;

import java.util.Random;

import org.newdawn.slick.geom.Circle;

public class Zombie extends AbstractEntity {

	public Zombie(int x, int y) {
		super(new Circle(x, y, 3), 5);
		direction = Direction.values()[new Random().nextInt(Direction.values().length)];
	}

	@Override
	public void update(int delta) {
		move(1);
	}

	@Override
	public void collide(AbstractEntity other) {
		if (other instanceof Noise) {
			moveTo(other.getX(), other.getY());
		}
	}

}
