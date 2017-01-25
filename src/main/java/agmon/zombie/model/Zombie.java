package agmon.zombie.model;

import java.util.Random;

import org.newdawn.slick.geom.Circle;

public class Zombie extends AbstractEntity {

	private int changeDirectionTimer;

	public Zombie(int x, int y) {
		super(new Circle(x, y, 3), 10);
		direction = Direction.values()[new Random().nextInt(Direction.values().length)];
	}

	@Override
	public void update(int delta) {
		changeDirectionTimer -= delta;
		if (changeDirectionTimer <= 0) {
			changeDirectionTimer = 5000;
			direction = Direction.values()[new Random().nextInt(Direction.values().length)];
		}

		move(1);
	}

	@Override
	public void collide(AbstractEntity other) {
		if (other instanceof Noise) {
			moveTo(other.getX(), other.getY());
		}
		if (other instanceof Person) {
			moveTo(other.getX(), other.getY());
		}
	}

}
