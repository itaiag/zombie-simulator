package agmon.zombie.model;

import java.util.Random;

import org.newdawn.slick.geom.Circle;

public class Zombie extends AbstractEntity {

	private int changeDirectionTimer;

	public Zombie(float x, float y) {
		super(new Circle(x, y, 3), 30);
		direction = Direction.values()[new Random().nextInt(Direction.values().length)];
	}

	@Override
	public void update(int delta) {
		changeDirectionTimer -= delta;
		if (changeDirectionTimer <= 0) {
			changeDirectionTimer = 4500 + rand.nextInt(1000);
			direction = Direction.values()[new Random().nextInt(Direction.values().length)];
		}

		move(1);
	}

	@Override
	public void collide(AbstractEntity other) {
		if (other instanceof Person) {
			setDirectionTo(other.getX(), other.getY());
			changeDirectionTimer = 800 + rand.nextInt(300);
		} else if (changeDirectionTimer >= 0 && other instanceof Noise) {
			setDirectionTo(other.getX(), other.getY());
		}
	}

}
